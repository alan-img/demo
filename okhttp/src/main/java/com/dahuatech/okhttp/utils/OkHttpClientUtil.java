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

    // 私有化okHttpClient构造器
    private OkHttpClientUtil() {
    }

    /**
     * 懒汉单例模式获取okHttpClient
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClientUtil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                    // 跳过ssl认证(https)
                    okHttpClient.setSslSocketFactory(getSSLSocketFactory());
                    okHttpClient.setHostnameVerifier((s, sslSession) -> true);
                    // 设置连接超时时间
                    okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                    // 设置读数据超时时间
                    okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                    // 设置写数据超时时间
                    okHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
                    okHttpClient.setRetryOnConnectionFailure(true);
                    // 设置连接池  最大连接数量  , 持续存活的连接
                    okHttpClient.setConnectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES));
                }
            }
        }

        return okHttpClient;
    }

    /**
     * 获取SSLSocketFactory实例
     *
     * @return
     */
    private static SSLSocketFactory getSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{getX509TrustManager()}, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return sslSocketFactory;
    }

    /**
     * @return
     */
    private static X509TrustManager getX509TrustManager() {
        return new X509TrustManager() {
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
        };
    }
}