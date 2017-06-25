package net.webbuildup.mymovieslist.mynytclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc Nguyen on 6/25/2017.
 */

public class SearchResult {
    @SerializedName("docs")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
