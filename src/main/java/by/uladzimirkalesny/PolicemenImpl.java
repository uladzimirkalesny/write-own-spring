package by.uladzimirkalesny;

public class PolicemenImpl implements Policemen {

    @InjectByType
    private Recommender recommender;

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("GO AWAY");
    }
}
