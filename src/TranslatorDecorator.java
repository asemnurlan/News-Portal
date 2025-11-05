public class TranslatorDecorator extends NotificationDecorator{
    public TranslatorDecorator(NotificationDecorator wrapped){
        super(wrapped);
    }


    @Override
    public void send(String recipient, Article article) {
        super.send(recipient, article);
        System.out.println("translating the article...");
    }
}
