package za.co.fredkobo.newsheadlines;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.fredkobo.newsheadlines.model.Article;
import za.co.fredkobo.newsheadlines.model.ArticleResponse;
import za.co.fredkobo.newsheadlines.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView{

    private static final String TAG = MainActivity.class.getSimpleName();
    private String apiKey;

    private RecyclerView recyclerView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        apiKey = getString(R.string.api_key);

        MainPresenter presenter = new MainPresenter(this);
        presenter.retrieveHeadlines("za", apiKey);
    }

    @Override
    public void refreshArticles(List<Article> articles) {
        ArticleAdapter adapter = new ArticleAdapter(articles, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressDialog(boolean show) {
        if(show)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void articleClicked(Article article) {
        Intent detailActivityIntent = new Intent(this, ArticleDetailActivity.class);
        detailActivityIntent.putExtra(Article.class.getSimpleName(), article);
        startActivity(detailActivityIntent);
    }
}
