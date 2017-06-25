package net.webbuildup.mymovieslist.mynytclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import net.webbuildup.mymovieslist.mynytclient.R;
import net.webbuildup.mymovieslist.mynytclient.api.ArticleApi;
import net.webbuildup.mymovieslist.mynytclient.model.SearchRequest;
import net.webbuildup.mymovieslist.mynytclient.model.SearchResult;
import net.webbuildup.mymovieslist.mynytclient.util.RetrofitUtil;

import butterknife.ButterKnife;
import okhttp3.internal.framed.FramedConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesSearch extends AppCompatActivity {
    private SearchRequest searchRequest;
    private ArticleApi articleApi;
    private ProgressBar pbLoading;
    private ProgressBar pbLoadmore;
    private SearchResult searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        ButterKnife.bind(this);
        setupApi();
    }

    private void setupApi() {
        searchRequest = new SearchRequest();
        articleApi = RetrofitUtil.get().create(ArticleApi.class);
    }

    private void search() {
        searchRequest.resetPage();
        pbLoading.setVisibility(View.VISIBLE);

    }
    private void fetchArticles() {
        articleApi.search(searchRequest.toQueryMap()).enqueue(new Callback<SearchRequest>() {
            @Override
            public void onResponse(Call<SearchRequest> call, Response<SearchRequest> response) {
                if (response.body() != null) {

                    handleComplete();
                }
            }

            @Override
            public void onFailure(Call<SearchRequest> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
    private void handleComplete() {
        pbLoading.setVisibility(View.GONE);
        pbLoadmore.setVisibility(View.GONE);
    }
}
