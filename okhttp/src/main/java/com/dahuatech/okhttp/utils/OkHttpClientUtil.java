package com.dahuatech.okhttp.utils;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

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
 * <p>packageName: com.dahuatech.utils</p>
 * <p>className: OkHttpClientUtil</p>
 * <p>date: 2023/4/1</p>
 *
 * @author qinjiawei(336105)
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
                okHttpClient = new OkHttpClient();
                // 设置连接超时时间
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                // 从连接成功到响应的总时间
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                // 写入超时时间
                okHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
                // 跳过ssl认证(https)
                okHttpClient.setSslSocketFactory(createSSLSocketFactory(trustManagers));
                okHttpClient.setHostnameVerifier((hostName, session) -> true);
                okHttpClient.setRetryOnConnectionFailure(true);
                // 设置连接池  最大连接数量  , 持续存活的连接
                okHttpClient.setConnectionPool(new ConnectionPool(50, 10, TimeUnit.MINUTES));
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