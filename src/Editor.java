import java.io.File;
import java.time.Instant;

public class Editor {
    public final NewsAgency events=new NewsAgency();

    public void publish(String title,String body, String category){
        Article a = new Article(title,body,category, Instant.now());
        events.notifyNewArticle(a);
    }
}
