import java.time.Instant;

public class FashionArticleFactory extends ArticleCreate{
    @Override
    public Article createArticle(String title, String body, String category) {
        return new Article(title,body,"New Fashion", Instant.now());
    }
}
