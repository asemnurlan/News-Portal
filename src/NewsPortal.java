import java.util.Scanner;

public class NewsPortal {

    private final Editor editor=new Editor();
    private final NewsAgency agency=editor.events;
    private final Scanner sc =new Scanner(System.in);
    private UserSubscriber user;

    public void run(){
        System.out.println("News portal. Only relevant and fresh news");
        setupUser();
    }

    public void setupUser(UserSubscriber user) {
        System.out.println("enter your nickname: ");
        String name=sc.nextLine();

        user = new UserSubscriber(name, strategy);
        agency.register(user);

        System.out.println("you are subscr")

    }
}
