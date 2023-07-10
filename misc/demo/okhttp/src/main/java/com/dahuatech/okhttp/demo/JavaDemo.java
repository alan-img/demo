package com.dahuatech.okhttp.demo;

import com.dahuatech.okhttp.utils.OkHttpClientUtil;
import okhttp3.*;

import java.io.IOException;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/3/26</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    public static void main(String[] args) throws IOException {

        String url = "https://localhost:8443/upload";
        OkHttpClient okHttpClient = OkHttpClientUtil.getOkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "alan,12\n" +
                "jack,22\n" +
                "adam,28");

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.isSuccessful());
        System.out.println(response.code());
        System.out.println(response.body().string());
        System.out.println(response.message());
        System.out.println(response.isRedirect());
    }
}
