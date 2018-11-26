package com.zhpan.library.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhpan on 2018/4/25.
 */

public class MapUtils {

    private static Set<String> BASE_TYPE = new HashSet<>();

    static {
        BASE_TYPE.add("byte");
        BASE_TYPE.add("short");
        BASE_TYPE.add("int");
        BASE_TYPE.add("long");
        BASE_TYPE.add("float");
        BASE_TYPE.add("double");
        BASE_TYPE.add("boolean");
        BASE_TYPE.add("char");
        BASE_TYPE.add("java.lang.Byte");
        BASE_TYPE.add("java.lang.Short");
        BASE_TYPE.add("java.lang.Integer");
        BASE_TYPE.add("java.lang.Long");
        BASE_TYPE.add("java.lang.Float");
        BASE_TYPE.add("java.lang.Double");
        BASE_TYPE.add("java.lang.Boolean");
        BASE_TYPE.add("java.lang.Character");
        BASE_TYPE.add("java.lang.String");
    }

    /**
     * 将实体对象转换为Map<String, Object>对象
     *
     * @param obj 实体对象，不支持Map和数组
     * @return Map<String, Object>对象
     */
    public static Map<String, Object> entityToMap(Object obj) {
        return entityToMap(obj, null);
    }


    /**
     * 将对象列表转换为Map<String,Object>列表
     *
     * @param list 对象列表
     * @return List<Map<String, Object>>对象
     */

    public static List<Map<String, Object>> entityListToMapList(List<?> list) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Object obj : list) {
            data.add(entityToMap(obj));
        }
        return data;
    }

    /**
     * 将对象列表对象转换为Map<String,Object>列表
     *
     * @param list   对象列表
     * @param config 实体对象转化Map<String,Object>配置
     * @return List<Map<String, Object>>对象
     */

    public static List<Map<String, Object>> entityListToMapList(List<?> list, ExcMapConfig config) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Object obj : list) {
            data.add(entityToMap(obj, config));
        }
        return data;
    }

    /**
     * xml字符串转化为Map<String,String>对象
     *
     * @param xmlStr xml字符串
     * @return Map<String,String>对象
     */
    public static Map<String, Object> xmlToMap(String xmlStr) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Pattern pattern = Pattern.compile("<([^/]\\S*?)>(.*?)</(\\S*?)>");
        Matcher m = pattern.matcher(xmlStr.replace("<xml>", "").replace("</xml>", ""));
        while (m.find()) {
            retMap.put(m.group(1), m.group(2).replace("<![CDATA[", "").replace("]]>", ""));
        }
        return retMap;
    }

    /**
     * 将Map<String, Object>对象转化为xml字符串
     *
     * @param map Map<String,String>对象
     * @return xml字符串
     */
    public static String mapToXml(Map<String, String> map) {
        StringBuilder xmlStr = new StringBuilder("<xml>");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (null != value && !"".equals(value)) {
                xmlStr.append(String.format("<%s><![CDATA[%s]]></%s>", key, value, key));
            }
        }
        xmlStr.append("</xml>");
        return xmlStr.toString();
    }


    /**
     * 将实体对象转换为Map<String, Object>对象
     *
     * @param obj    实体对象，不支持Map和数组
     * @param config 实体对象转化Map<String,Object>配置
     * @return Map<String, Object>对象
     */
    public static Map<String, Object> entityToMap(Object obj, ExcMapConfig config) {
        if (obj == null) {
            return null;
        }

        Set<String> includeFields = null;
        Set<String> excludeFields = null;
        Map<String, String> aliases = null;
        Map<String, String> dateFormats = null;
        Map<String, ExcMapRenderer> renderers = null;
        Map<String, ExcMapConfig> subConfigs = null;
        if (config != null) {
            includeFields = config.getIncludeFields();
            excludeFields = config.getExcludeFields();
            aliases = config.getAliases();
            dateFormats = config.getDateFormats();
            renderers = config.getRenderers();
            subConfigs = config.getSubConfigs();
        }

        Map<String, Object> data = new HashMap<>();
        //Field[] fields = obj.getClass().getDeclaredFields();
        Field[] fields = getDeclaredField(obj);
        int count = 0;
        int max = fields.length;
        if (includeFields != null) {
            max = includeFields.size();
        }

        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if (includeFields != null && !includeFields.contains(name)) {
                continue;
            }
            if (excludeFields != null && excludeFields.contains(name)) {
                continue;
            }
            String alias = name;
            if (aliases != null) {
                String a = (String) aliases.get(name);
                if (a != null) {
                    alias = a;
                }
            }
            String typeName = field.getType().getName();
            Object val;
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e1) {
                throw new RuntimeException();
            }
            if (BASE_TYPE.contains(typeName)) {// 不需要转换的类型

            } else if ("java.util.Date".equals(typeName)) {// 日期类型
                Object date = null;
                String df = null;
                if (dateFormats != null) {
                    df = (String) dateFormats.get(name);
                }
                if (df != null) {
                    if ("time".equals(df.toLowerCase())) {
                        date = ((Date) val).getTime();
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat(df);
                            date = sdf.format((Date) val);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    try {
                        date = new SimpleDateFormat(ExcMapConfig.DEFAULT_DATE_FORMAT).format((Date) val);
                    } catch (Exception e) {
                    }
                }
                val = date;
            } else if (val instanceof Collection<?>) {// 集合
                List<Map<String, Object>> list = new ArrayList<>();
                Collection<?> c = (Collection<?>) val;
                ExcMapConfig subConfig = null;
                if (subConfigs != null) {
                    subConfig = subConfigs.get(name);
                }
                for (Object object : c) {
                    list.add(entityToMap(object, subConfig));
                }
                val = list;
            } else {// 其他自定义对象类型
                ExcMapConfig subConfig = null;
                if (subConfigs != null) {
                    subConfig = subConfigs.get(name);
                }
                val = entityToMap(val, subConfig);
            }

            if (renderers != null) {// 渲染
                ExcMapRenderer renderer = (ExcMapRenderer) renderers.get(name);
                if (renderer != null) {
                    val = renderer.function(val, obj);
                }
            }
            if (val == null)
                data.put(alias, "");
            else
                data.put(alias, val);

            if (!(++count < max)) {// 达到需要转换字段数量
                break;
            }
        }
        return data;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object : 子类对象
     * @return 父类中的属性对象
     */
    private static Field[] getDeclaredField(Object object) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = new Field[0];
        Class<?> clazz = object.getClass();
        while (clazz != null && clazz != Object.class) {
            try {
                Field[] declaredFields = clazz.getDeclaredFields();
                fieldList.addAll(new ArrayList<>(Arrays.asList(declaredFields)));
                fields = new Field[fieldList.size()];
                clazz = clazz.getSuperclass();

            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return fieldList.toArray(fields);
    }


    /**
     * 实体对象转化Map<String,Object>配置
     */
    public static class ExcMapConfig {
        /**
         * 默认日期格式
         */
        private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        /**
         * 日期格式
         */
        private Map<String, String> dateFormats;
        /**
         * 包含字段
         */
        private Set<String> includeFields;
        /**
         * 排除字段（优先）
         */
        private Set<String> excludeFields;
        /**
         * 别名
         */
        private Map<String, String> aliases;
        /**
         * 渲染器
         */
        private Map<String, ExcMapRenderer> renderers;
        /**
         * 子元素配置
         */
        private Map<String, ExcMapConfig> subConfigs;

        public Map<String, String> getDateFormats() {
            return dateFormats;
        }

        public void setDateFormats(Map<String, String> dateFormats) {
            this.dateFormats = dateFormats;
        }

        public Set<String> getIncludeFields() {
            return includeFields;
        }

        public void setIncludeFields(Set<String> includeFields) {
            this.includeFields = includeFields;
        }

        public Set<String> getExcludeFields() {
            return excludeFields;
        }

        public void setExcludeFields(Set<String> excludeFields) {
            this.excludeFields = excludeFields;
        }

        public Map<String, String> getAliases() {
            return aliases;
        }

        public void setAliases(Map<String, String> aliases) {
            this.aliases = aliases;
        }

        public Map<String, ExcMapRenderer> getRenderers() {
            return renderers;
        }

        public void setRenderers(Map<String, ExcMapRenderer> renderers) {
            this.renderers = renderers;
        }

        public Map<String, ExcMapConfig> getSubConfigs() {
            return subConfigs;
        }

        public void setSubConfigs(Map<String, ExcMapConfig> subConfigs) {
            this.subConfigs = subConfigs;
        }

    }

    /**
     * Map<String,Object>对象操作类
     */
    public static class ExcMapData implements Serializable {
        private static final long serialVersionUID = 3335147153448922870L;

        public ExcMapData(Object obj) {
            this.data = (Map<String, Object>) obj;
        }

        public ExcMapData() {
        }

        /**
         * 数据
         */
        private Map<String, Object> data;

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        private ExcMapData(Map<String, Object> data) {
            this.data = data;
        }

        /**
         * 获的data的String形式
         */
        public String dataString() {
            return String.valueOf(data);
        }

        /**
         * 获得String类型值
         */
        public String getString(String key) {
            try {
                Object value = data.get(key);
                if (value == null) {
                    return null;
                }
                return String.valueOf(value);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Boolean类型值
         */
        public Boolean getBoolean(String key) {
            try {
                return Boolean.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Integer类型值
         */
        public Integer getInteger(String key) {
            try {
                return Integer.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Double类型值
         */
        public Double getDouble(String key) {
            try {
                return Double.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Long类型值
         */
        public Long getLong(String key) {
            try {
                return Long.valueOf(getString(key));
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Date类型值
         */
        public Date getDate(String key) {
            try {
                return (Date) data.get(key);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获得Object类型值
         */
        public Object getObject(String key) {
            return data.get(key);
        }

        /**
         * 获得Map<String, Object>类型值
         */
        public Map<String, Object> getMap(String key) {
            return (Map<String, Object>) data.get(key);
        }

        /**
         * 获得List<Map<String,Object>>类型值
         */
        public List<Map<String, Object>> getMapList(String key) {
            return (List<Map<String, Object>>) data.get(key);
        }

        /**
         * 获得MapData类型值
         */
        public ExcMapData getMapData(String key) {
            return new ExcMapData(data.get(key));
        }

        /**
         * 获得List<MapData>类型值
         */
        public List<ExcMapData> getMapDataList(String key) {
            List<ExcMapData> mapDataList = new ArrayList<>();
            for (Map<String, Object> map : (List<Map<String, Object>>) data.get(key)) {
                mapDataList.add(new ExcMapData(map));
            }
            return mapDataList;
        }

        /**
         * 添加值
         */
        public void put(String key, Object value) {
            data.put(key, value);
        }
    }

    /**
     * 渲染器
     */
    public interface ExcMapRenderer {
        /**
         * 渲染函数
         *
         * @param value  值
         * @param entity 实体
         * @return 渲染过的值
         */
        Object function(Object value, Object entity);
    }
}
