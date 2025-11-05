public class UserSubscriber implements Subscriber{
    private final String recipient;
    private NotificationStrategy strategy;

    public UserSubscriber(String recipient, NotificationStrategy strategy){
        this.recipient=recipient;
        this.strategy=strategy;
    }

    public String getRecipient(){
        return recipient;
    }
    public void setStrategy(NotificationStrategy strategy){
        this.strategy=strategy;
    }

    @Override
    public void update(Article article) {
        strategy.send(recipient,article);
        System.out.println("subscriber: "+this.recipient+" recived new article: "+article.title());
    }
}
