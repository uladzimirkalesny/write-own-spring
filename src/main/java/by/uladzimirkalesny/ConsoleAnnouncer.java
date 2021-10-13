package by.uladzimirkalesny;

public class ConsoleAnnouncer implements Announcer {

    @InjectByType
    private Recommender recommender;

    @Override
    public void announceMessage(String message) {
        System.out.println(message);

        recommender.recommend();
    }
}
