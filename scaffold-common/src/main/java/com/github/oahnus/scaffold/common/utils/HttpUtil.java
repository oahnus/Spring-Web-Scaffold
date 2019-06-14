package com.github.oahnus.scaffold.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by oahnus on 2018/8/24
 * 14:58.
 */
@Slf4j
public class HttpUtil {
    private static HttpService httpService;

    static {
        // 使用一言api做演示
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v1.hitokoto.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    public static String hitokoto() {
        try {
            Response<ResponseBody> response = httpService.get("json", "utf-8").execute();
            if (response.code() == 200) {
                String json = response.body().string();
                log.info("[HttpUtil].hitokoto - json = {}", json);
                return response.body() != null ? json : null;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String... args) throws IOException {
        String json = HttpUtil.hitokoto();
        System.out.println(json);
    }
}
