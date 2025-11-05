import java.util.Scanner;

public class NewsPortal {
    private final NewsAgency agency=new NewsAgency();
    private final Scanner sc =new Scanner(System.in);
    private UserSubscriber user;
    private boolean subscribed=false;

    private final ArticleCreate fashionArticle=new FashionArticleFactory();
    private final ArticleCreate petArticle=new PetsArticleFactory();
    private final ArticleCreate politics=new PoliticArticleFactory();

    public void run(){
        System.out.println("News portal. Only relevant and fresh news");
        setupUser();
        menu();
        commandLoop();
        sc.close();
    }

    private void setupUser() {
        System.out.print("enter your nickname: ");
        String name = sc.nextLine().trim();

        boolean alreadySubscribed = Database.isSubscribed(name);
        String lastStrategy = Database.getStrategy(name);

        NotificationStrategy strategy = switch (lastStrategy.toLowerCase()) {
            case "sms" -> new SMSStrategy();
            case "whatsapp" -> new WhatsappStrategy();
            default -> new EmailStrategy();
        };

        user = new UserSubscriber(name, strategy);

        if (alreadySubscribed) {
            agency.register(user);
            subscribed = true;
            System.out.println("Welcome back, " + name + "! You've already subscribed" );
        } else {
            System.out.print("Subscribe now? (y/n): ");
            String yn = sc.nextLine().trim().toLowerCase();
            if (yn.startsWith("y")) {
                agency.register(user);
                subscribed = true;
                Database.addUser(name, lastStrategy, true);
                System.out.println("Subscribed to the News Portal!");
            } else {
                Database.addUser(name, lastStrategy, false);
                System.out.println("You are not subscribed (you can subscribe later).");
            }
        }
    }

    public void menu(){
        System.out.println("""
                chose the action: 
                1)publish-create a new article
                2)switch-change delivery type(sms,email,whatsapp)
                3)exit-quit
                """);
    }

    private void commandLoop(){
        while (true){
            System.out.println("enter command: ");
            String cmd=sc.nextLine().trim().toLowerCase();

            switch(cmd){
                case "publish" -> selectArticleCategory();
                case "switch" -> switchSubscribe();
                case "exit" -> {
                    System.out.println("closing app...");
                    sc.close();
                    return;
                }
                default -> System.out.println("unknown command. try again");
            }
        }
    }
    private void selectArticleCategory() {
        System.out.println("choose the category (fashion,pets,polotics)");
        String category = sc.nextLine().trim().toLowerCase();
        ArticleCreate creator;

        switch (category) {
            case "pets" -> creator = petArticle;
            case "politics" -> creator = politics;
            case "fashion" -> creator =fashionArticle;
            default -> {
                System.out.println("unknown category");
                return;
            }
        }

        System.out.print("title: ");
        String title = sc.nextLine();

        System.out.print("body: ");
        String body = sc.nextLine();

        Article newArticle = creator.createArticle(title, body,category);
        agency.publishArticle(newArticle);
    }
    private void publishArticle(String cmd) {
        ArticleCreate creator;
        String category;
        if (cmd.contains("fashion")) {
            creator = fashionArticle;
            category = "fashion";
        } else if (cmd.contains("politics")) {
            creator = politics;
            category = "Politics";
        }
        else if (cmd.contains("pets")) {
            creator = petArticle;
            category = "Pets";
        }
        else {
            System.out.println("Unknown category. Use 'publish sports' or 'publish politics'.");
            return;
        }
        System.out.print(category + " Article title: ");
        String title = sc.nextLine();

        System.out.print(category + " Article body: ");
        String body = sc.nextLine();

        Article newArticle = creator.createArticle(title,body,category);

        agency.publishArticle(newArticle);

    }

    private void switchSubscribe() {
        if (!subscribed){
            System.out.println("You are not subscribed. Choose delivery type to subscribe: ");
            NotificationStrategy newStrategy = chooseStrategy();
            NotificationStrategy decorated = new UrgencyDecorator(new TranslatorDecorator((NotificationDecorator) newStrategy));

            user.setStrategy(decorated);
            agency.register(user);
            subscribed = true;
            System.out.println("Subscribed successfully!");
            return;
        }
        System.out.println("choose method youwant to get notification(email, sms, whatsapp");
        NotificationStrategy newStr = chooseStrategy();
        NotificationStrategy decorated=new UrgencyDecorator(new TranslatorDecorator((NotificationDecorator) newStr));
        user.setStrategy(decorated);
        System.out.println("Strategy changed successfully!");


    }
    private NotificationStrategy chooseStrategy() {
        System.out.print("Choose notification type (email / sms / whatsapp): ");
        String choice = sc.nextLine().trim().toLowerCase();
        return switch (choice){
            case "sms" -> new SMSStrategy();
            case "whatsapp" -> new WhatsappStrategy();
            default -> new EmailStrategy();
        };
    }
}
