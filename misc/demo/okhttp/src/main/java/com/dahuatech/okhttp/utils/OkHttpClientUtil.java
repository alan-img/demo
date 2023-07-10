package com.dahuatech.okhttp.utils;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: OkHttpClientUtil</p>
 * <p>date: 2023/3/26</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class OkHttpClientUtil {
    private static volatile OkHttpClient okHttpClient;

    // 私有化okHttpClient客户端
    private OkHttpClientUtil() {
    }

    /**
     * 懒汉单例模式获取okHttpClient
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClientUtil.class) {
                TrustManager[] trustManagers = buildTrustManagers();
                okHttpClient = new OkHttpClient.Builder()
                        // 设置连接超时时间
                        .connectTimeout(20, TimeUnit.SECONDS)
                        // 写入超时时间
                        .writeTimeout(20, TimeUnit.SECONDS)
                        // 从连接成功到响应的总时间
                        .readTimeout(20, TimeUnit.SECONDS)
                        // 跳过ssl认证(https)
                        .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                        .hostnameVerifier((hostName, session) -> true)
                        .retryOnConnectionFailure(true)
                        // 设置连接池  最大连接数量  , 持续存活的连接
                        .connectionPool(new ConnectionPool(50, 10, TimeUnit.MINUTES))
                        .build();
            }
        }

        return okHttpClient;
    }

    /**
     * 获取SSLSocketFactory实例
     *
     * @param trustAllCerts
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return sslSocketFactory;
    }

    /**
     * 获取信息管理器TrustManager数组
     *
     * @return
     */
    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
    }
}
