package net.webbuildup.mymovieslist.mynytclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import net.webbuildup.mymovieslist.mynytclient.R;
import net.webbuildup.mymovieslist.mynytclient.adapter.ArticleAdapter;
import net.webbuildup.mymovieslist.mynytclient.api.ArticleApi;
import net.webbuildup.mymovieslist.mynytclient.model.SearchRequest;
import net.webbuildup.mymovieslist.mynytclient.model.SearchResult;
import net.webbuildup.mymovieslist.mynytclient.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesSearch extends AppCompatActivity {
    private SearchRequest searchRequest;
    private ArticleApi articleApi;
    private ArticleAdapter articleAdapter;
    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;
    @BindView(R.id.pbLoadMore)
    ProgressBar pbLoadMore;
    @BindView(R.id.pdLoading)
    ProgressBar pbLoading;


    private interface Listener {
        void onResult(SearchResult searchResult);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_search);
        ButterKnife.bind(this);
        setupView();
        setupApi();
        search();
    }

    private void setupView() {
        articleAdapter = new ArticleAdapter(this);
        rvArticle.setAdapter(articleAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvArticle.setLayoutManager(layoutManager);
    }

    private void setupApi() {
        searchRequest = new SearchRequest();
        articleApi = RetrofitUtil.get().create(ArticleApi.class);
    }

    private void search() {
        searchRequest.resetPage();
        pbLoading.setVisibility(View.VISIBLE);
        fetchArticles(new Listener() {
            @Override
            public void onResult(SearchResult searchResult) {
                articleAdapter.setArticles(searchResult.getArticles());
            }
        });
    }
    private void fetchArticles(final Listener listener) {
        articleApi.search(searchRequest.toQueryMap()).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.body() != null) {
                    listener.onResult(response.body());
                    handleComplete();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                handleComplete();
            }
        });
    }
    private void handleComplete() {
        pbLoading.setVisibility(View.GONE);
        pbLoadMore.setVisibility(View.GONE);
    }
}
