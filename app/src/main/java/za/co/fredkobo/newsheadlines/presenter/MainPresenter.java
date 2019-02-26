package za.co.fredkobo.newsheadlines.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.fredkobo.newsheadlines.ApiClient;
import za.co.fredkobo.newsheadlines.ApiInterface;
import za.co.fredkobo.newsheadlines.MainView;
import za.co.fredkobo.newsheadlines.model.ArticleResponse;

/**
 * Created by F5094712 on 2019/02/26.
 */

public class MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void retrieveHeadlines(String country, String apiKey){
        mainView.showProgressDialog(true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticleResponse> call = apiService.getTopHeadLines(country, apiKey);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                mainView.refreshArticles(response.body().getArticles());
                mainView.showProgressDialog(false);
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d(TAG, "Call failed");
                mainView.showProgressDialog(false);
            }
        });
    }
}
