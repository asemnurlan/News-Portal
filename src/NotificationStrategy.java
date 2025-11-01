public interface NotificationStrategy {
    void send(String recipient, Article article);
}
