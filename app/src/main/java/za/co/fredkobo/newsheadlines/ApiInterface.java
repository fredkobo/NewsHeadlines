package za.co.fredkobo.newsheadlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.co.fredkobo.newsheadlines.model.ArticleResponse;


public interface ApiInterface {

    @GET("top-headlines")
    Call<ArticleResponse> getTopHeadLines(@Query("country") String country, @Query("apiKey") String apiKey);
}