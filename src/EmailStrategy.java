public class EmailStrategy implements NotificationStrategy{
    private String email;
    private String password;

    @Override
    public void send(String recipient, Article article) {
        System.out.println("[Email] Hello Dear, "+recipient+"there are some articles you may like: " +article.title()+"***"+article.category()+"***");
    }
}
