public class NotificationDecorator implements NotificationStrategy{
    public NotificationStrategy wrapped;
    public NotificationDecorator(NotificationStrategy wrapped){
        this.wrapped=wrapped;
    }
    @Override
    public void send(String recipient, Article article) {
        wrapped.send(recipient, article);
    }
}
