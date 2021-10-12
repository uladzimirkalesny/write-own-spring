package by.uladzimirkalesny;

public class ConsoleAnnouncer implements Announcer {

    private Recommender recommender = ObjectFactory.getObjectFactoryInstance().createObject(Recommender.class);

    @Override
    public void announceMessage(String message) {
        System.out.println(message);

        recommender.recommend();
    }
}
