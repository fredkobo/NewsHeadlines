package za.co.fredkobo.newsheadlines;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.http.Url;
import za.co.fredkobo.newsheadlines.model.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView titleTextView = findViewById(R.id.title_textview);
        TextView detailTextView = findViewById(R.id.detail_textview);
        ImageView imageView = findViewById(R.id.article_image_view);

        Intent intent = getIntent();
        article = (Article) intent.getParcelableExtra(Article.class.getSimpleName());
        titleTextView.setText(article.getTitle());
        detailTextView.setText(article.getContent());

        Picasso.with(this)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }

    public void goToLink(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        startActivity(intent);
    }
}
