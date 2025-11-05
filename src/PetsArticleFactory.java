import java.time.Instant;

public class PetsArticleFactory extends ArticleCreate{
    @Override
    public Article createArticle(String title, String body, String category) {
        return new Article(title,body,"pets", Instant.now());
    }
}
