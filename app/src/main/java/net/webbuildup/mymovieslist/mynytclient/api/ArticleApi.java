package net.webbuildup.mymovieslist.mynytclient.api;

import net.webbuildup.mymovieslist.mynytclient.model.SearchRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Duc Nguyen on 6/25/2017.
 */

public interface ArticleApi {
    @GET("articlesearch.json")
    Call<SearchRequest> search(@QueryMap(encoded = true) Map<String, String> options);
}
