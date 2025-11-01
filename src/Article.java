import java.time.Instant;

public record Article(String title, String body, String category, Instant publishedAt) {
}
