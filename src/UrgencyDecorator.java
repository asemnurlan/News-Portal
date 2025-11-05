public class UrgencyDecorator extends NotificationDecorator{
    public UrgencyDecorator(NotificationDecorator wrapped){
        super(wrapped);
    }

    @Override
    public void send(String recipient, Article article) {
        super.send(recipient, article);
        System.out.println("TTENTION URGENT NEWS!!!");
    }
}
