package com.dahuatech.test.utils;

/**
 * @author : harara
 * @version : 2.0
 * @date : 2019/9/27 9:39
 */
public class HttpConstant {

    /**httpClient连接超时时间,单位毫秒 */
    public static final int CONNECT_TIMEOUT = 30*1000;

    /**httpClient请求获取数据的超时时间(即响应时间) 单位毫秒*/
    public static final int SOCKET_TIMEOUT = 60*1000;

    /**http连接池大小*/
    public static final int MAX_TOTAL = 10;

    /**分配给同一个route(路由)最大的并发连接数*/
    public static final int MAX_CONN_PER_ROUTE = 2;

    /**http连接是否是长连接*/
    public static final boolean IS_KEEP_ALIVE = false;

    /**调用接口失败默认重新调用次数*/
    public static final int REQ_TIMES = 3;

    /**utf-8编码*/
    public static final String UTF8_ENCODE = "UTF-8";

    /** application/json */
    public static final String APPLICATION_JSON = "application/json";

    /** okhttp media type **/
    public static final String MEDIA_TYPE = "application/json; charset=utf-8";

    /** text/xml **/
    public static final String TEXT_XML = "text/xml";

    /** okhttp 连接池最大连接个数**/
    public static final int MAX_IDLE_CONNECTIONS = 20;

    /** okhttp 连接最长时间 单位：毫秒 **/
    public static final long KEEP_ALIVE_DURATION = 600000L;

    /** okhttp 读取数据超时时间 单位：毫秒 **/
    public static final int READ_TIMEOUT = 30*1000;

    /** okhttp 写数据超时时间 单位：毫秒 **/
    public static final int WRITE_TIMEOUT = 30*1000;


}
