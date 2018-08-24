package top.oahnus.common.utils;

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

    public static String get(String encode, String charset) throws IOException {
        Response<ResponseBody> response = httpService.get(encode, charset).execute();
        if (response.code() == 200) {
            return response.body() != null ? response.body().string() : null;
        } else {
            return null;
        }
    }

    public static void main(String... args) throws IOException {
        String json = HttpUtil.get("json", "utf-8");
        System.out.println(json);
    }
}
