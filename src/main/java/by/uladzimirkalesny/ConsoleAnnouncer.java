package by.uladzimirkalesny;

public class ConsoleAnnouncer implements Announcer {
    @Override
    public void announceMessage(String message) {
        System.out.println(message);
    }
}
