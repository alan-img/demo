package com.dahuatech.test.utils;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author linzhiqiang 307178
 * OkHttpClientFactory 工厂类
 */
public class OkHttpClientFactory {
    public static OkHttpClientFactory getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * 获取 OkHttpClient 对象
     * @return OkHttpClient
     */
    public OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = null;
        if (HttpConstant.IS_KEEP_ALIVE) {
            okHttpClient = new OkhttpAndSSLClientBuilder().getOkHttpsClient();
        } else {
            okHttpClient =  new OkhttpClientBuilder().getClient();
        }
        return okHttpClient;
    }

    /**
     * 枚举单例:
     * 构建 OkhttpClientFactory 对象
     */
    private enum Singleton {
        INSTANCE;
        private final OkHttpClientFactory instance;
        Singleton() {instance = new OkHttpClientFactory();}
        private OkHttpClientFactory getInstance() {
            return instance;
        }

    }

    /**
     * 封装一个内部静态类，构建 okhttpclient 对象
     */
    private static class OkhttpClientBuilder {
        private static OkHttpClient client;

        private OkHttpClient getClient() {
            if (client == null) {
                client = new OkHttpClient();
                client.setConnectTimeout(HttpConstant.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                client.setReadTimeout(HttpConstant.READ_TIMEOUT,TimeUnit.MILLISECONDS);
                client.setWriteTimeout(HttpConstant.WRITE_TIMEOUT,TimeUnit.MILLISECONDS);
                client.setRetryOnConnectionFailure(true);
                client.setConnectionPool(getConnectionPool());
            }
            return client;
        }

        /**
         * 自定义连接池
         * @return 返回一个最大 20 个连接数，最长 5 分钟的连接池
         */
        private ConnectionPool getConnectionPool() {
            return new ConnectionPool(HttpConstant.MAX_IDLE_CONNECTIONS,HttpConstant.KEEP_ALIVE_DURATION);
        }

    }

    /**
     * 构建一个内部静态类，构建 okhttpclient 对象，使用 https 请求
     */
    private static class OkhttpAndSSLClientBuilder {
        private OkHttpClient client = null;

        private OkHttpClient getOkHttpsClient() {
            if (client == null) {
                client = new OkHttpClient();
                client.setConnectTimeout(HttpConstant.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                client.setReadTimeout(HttpConstant.READ_TIMEOUT,TimeUnit.MILLISECONDS);
                client.setWriteTimeout(HttpConstant.WRITE_TIMEOUT,TimeUnit.MILLISECONDS);
                client.setSslSocketFactory(getTrustedSSLSocketFactory());
                client.setHostnameVerifier(DO_NOT_VERIFY);
                client.setConnectionPool(getHttpsConnectionPool());
                client.setRetryOnConnectionFailure(true);
            }
            return client;
        }

        private X509TrustManager x509TrustManager() {
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

        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };

        private SSLSocketFactory getTrustedSSLSocketFactory() {
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[]{x509TrustManager()}, new java.security.SecureRandom());
                return sc.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 自定义连接池
         * @return 返回一个最大 20 个连接数，最长 10 分钟的连接池
         */
        private ConnectionPool getHttpsConnectionPool() {
            return new ConnectionPool(HttpConstant.MAX_IDLE_CONNECTIONS,HttpConstant.KEEP_ALIVE_DURATION);
        }

    }
}
