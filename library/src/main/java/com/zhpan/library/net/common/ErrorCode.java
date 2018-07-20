
package com.zhpan.library.net.common;

public class ErrorCode {

    /**
     * 请求成功
     */
    public final static int REQUEST_SUCCESS = 200;
    /**
     * token错误
     */
    public final static int TOKEN_INCORRECT = 201;
    /**
     * token过期
     */
    public final static int TOKEN_EXPIRED = 202;

    /**
     * refresh_token过期
     */
    public final static int REFRESH_TOKEN_EXPIRED = 203;

    public static final int TOKEN_NOT_EXIST = 1000;
    public static final int TOKEN_INVALID = 1001;
}
