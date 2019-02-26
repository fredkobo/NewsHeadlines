package za.co.fredkobo.newsheadlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import za.co.fredkobo.newsheadlines.model.Article;

/**
 * Created by F5094712 on 2019/02/26.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{

    private List<Article> articleList;
    private Context context;
    private MainView mainView;

    public ArticleAdapter(List<Article> articleList, Context context, MainView mainView) {
        this.articleList = articleList;
        this.context = context;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        final Article article = articleList.get(position);
        holder.titleTextView.setText(article.getTitle());
        Picasso.with(context)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_thumbnail)
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.articleClicked(articleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView thumbnail;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail_img);
        }
    }
}
