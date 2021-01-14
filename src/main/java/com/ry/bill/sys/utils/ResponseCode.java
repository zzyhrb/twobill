package com.ry.bill.sys.utils;

/**
 * @author CKFuture
 * @since 2020-12-24 20:45
 * @directions 请求代码
 *
 */

public class ResponseCode {
    public static final String SUCCESS = "0";   //成功
    public static final String SUCCESS_HAVE = "1";   //存在相同
    public static final String ERROR_999 = "999";// 系统异常

    public static final String ERROR_PARAMS_VALIDATOR="200"; //参数验证错误

    public static final String ERROR_SERVICE_VALIDATOR="300"; //业务验证错误

    public static final String ERROR_DATA_VALIDATOR="400";  //系统数据错误
}
