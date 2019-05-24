package com.github.oahnus.scaffold.common.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by oahnus on 2018/8/24
 * 14:59.
 */
public interface HttpService {
    @POST("http://www.bing.com")
//    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("field1") String field1);

    @GET("https://v1.hitokoto.cn")
    Call<ResponseBody> get(@Query("encode") String encode,
                           @Query("charset") String charset);

}
