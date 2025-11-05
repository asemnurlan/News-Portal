import java.time.Instant;

public class PoliticArticleFactory extends ArticleCreate{
    @Override
    public Article createArticle(String title, String body, String category) {
        return new Article(title,body,"potic", Instant.now());
    }
}
