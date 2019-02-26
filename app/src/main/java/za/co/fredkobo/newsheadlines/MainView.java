package za.co.fredkobo.newsheadlines;

import java.util.ArrayList;
import java.util.List;

import za.co.fredkobo.newsheadlines.model.Article;

/**
 * Created by F5094712 on 2019/02/26.
 */

public interface MainView {
    void refreshArticles(List<Article> articles);
    void showProgressDialog(boolean show);
    void showFailureMessage(String msg);
    void articleClicked(Article article);
}
