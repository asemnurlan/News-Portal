import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
    private final List<Subscriber> subscribers=new ArrayList<>();

    public void register(Subscriber s){
        if (!subscribers.contains(s)) subscribers.add(s);
    }
    public void unregister(Subscriber s){
        subscribers.remove(s);
    }

    public void notifyNewArticle(Article article){
        for (Subscriber s : List.copyOf(subscribers)){
            s.update(article);
        }
    }


}
