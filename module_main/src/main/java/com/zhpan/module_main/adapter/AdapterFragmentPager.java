package com.zhpan.module_main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhpan.library.fragment.BaseFragment;
import com.zhpan.library.router.RoutingTable;

import java.util.List;

/**
 * 主页面Fragment切换数据源 <br>
 * 日期：2014年10月26日 <br>
 *
 * @author：davintan
 */
public class AdapterFragmentPager extends FragmentPagerAdapter {

    /**
     * 首页
     */
    public static final int PAGE_HOME = 0;

    /**
     * 个人中心
     */
    public static final int PAGE_ME = 4;

    /**
     * 消息界面
     */
    public static final int PAGE_MESSAGE = 3;

    /**
     * 发布
     */
    public static final int PAGE_PUBLISH = 2;

    /**
     * 界面
     */
    public static final int PAGE_FIND = 1;


    /**
     * 切换Fragment页面集合
     */
    private SparseArray<BaseFragment> fragmentList;


    public AdapterFragmentPager(FragmentManager fm) {
        super(fm);
        fragmentList = getFragments();
    }

    private SparseArray<BaseFragment> getFragments() {
        SparseArray<BaseFragment> fragmentList = new SparseArray<>();
        BaseFragment findFragment = (BaseFragment) ARouter.getInstance().build(RoutingTable.FRAGMENT_FIND).navigation();
        BaseFragment homeFragment = (BaseFragment) ARouter.getInstance().build(RoutingTable.FRAGMENT_HOME).navigation();
        BaseFragment publishFragment = (BaseFragment) ARouter.getInstance().build(RoutingTable.FRAGMENT_PUBLISH).navigation();
        BaseFragment meFragment = (BaseFragment) ARouter.getInstance().build(RoutingTable.FRAGMENT_ME).navigation();
        fragmentList.put(PAGE_HOME, homeFragment);
        fragmentList.put(PAGE_FIND, findFragment);
        fragmentList.put(PAGE_PUBLISH, publishFragment);
        fragmentList.put(PAGE_MESSAGE, meFragment);
        return fragmentList;
    }

    public AdapterFragmentPager(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentList.put(position, (BaseFragment) fragment);
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        } else {
            return 0;
        }
    }

}
