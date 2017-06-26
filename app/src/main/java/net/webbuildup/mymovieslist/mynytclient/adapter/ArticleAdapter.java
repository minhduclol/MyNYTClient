package net.webbuildup.mymovieslist.mynytclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.webbuildup.mymovieslist.mynytclient.R;
import net.webbuildup.mymovieslist.mynytclient.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by Duc Nguyen on 6/23/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int NO_IMG = 0;
    private static final int NORMAL = 1;
    private final List<Article> articles;
    private final Context context;

    public ArticleAdapter(Context context) {
        this.articles = new ArrayList<Article>();
        this.context = context;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Context getContext() {
        return context;
    }
    private boolean articleHasMultimedia(Article article) {
        return (article.getMultimedia() != null && article.getMultimedia().size() > 0);
    }
    @Override
    public int getItemViewType(int position) {
        Article article = articles.get(position);
        if (articleHasMultimedia(article)) {
            return NORMAL;
        }
        return NO_IMG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NO_IMG) {
            return new NoImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article_no_image, parent, false));
        } else {
            return new NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Article article = articles.get(position);
        if (holder instanceof NoImageViewHolder) {
            bindNoImage((NoImageViewHolder) holder, article);
        } else if (holder instanceof NormalViewHolder) {
            bindNormal((NormalViewHolder) holder, article);
        }
    }
    private void bindNoImage(NoImageViewHolder holder, Article article) {

        holder.tvSnippet.setText(article.getSnippet());
    }
    private void bindNormal(NormalViewHolder holder, Article article) {
        holder.tvSnippet.setText(article.getSnippet());
        Article.Media media = article.getMultimedia().get(0);
        Picasso.with(context).load(media.getUrl()).placeholder(R.drawable.ic_crop_original_black_24dp).into(holder.ivCover);
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> _articles) {
        this.articles.clear();
        this.articles.addAll(_articles);
        //reset the adapter
        notifyDataSetChanged();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;
        @BindView(R.id.ivCover)
        ImageView ivCover;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NoImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;
        public NoImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
