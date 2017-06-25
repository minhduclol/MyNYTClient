package net.webbuildup.mymovieslist.mynytclient.util;

import com.google.gson.Gson;

import net.webbuildup.mymovieslist.mynytclient.BuildConfig;
import net.webbuildup.mymovieslist.mynytclient.model.ApiResponse;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Duc Nguyen on 6/23/2017.
 */

public class RetrofitUtil {
    private static final String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Gson GSON = new Gson();
    public static Retrofit get() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .baseUrl(BASE_URL)
                .build();
    }
    public static OkHttpClient client() {
        return new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor())
                .addInterceptor(apiResponseInterceptor())
                .build();
    }
    private static Interceptor apiResponseInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                ApiResponse apiResponse = GSON.fromJson(response.body().string(), ApiResponse.class);
                return response.newBuilder()
                        .body(ResponseBody.create(JSON, GSON.toJson(apiResponse.getReponse())))
                        .build();
            }
        };
    }
    private static Interceptor apiKeyInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //construct the url from the provided request url
                HttpUrl url = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build();

                request = request.newBuilder()
                        .url(url)
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
