public class WhatsappStrategy implements NotificationStrategy {
    private String phoneNumber;
    @Override
    public void send(String recipient, Article article) {
        System.out.println("[Whatsapp] Hello Dear, "+recipient+"there are some articles you may like: " +article.title()+"***"+article.category()+"***");
    }
}