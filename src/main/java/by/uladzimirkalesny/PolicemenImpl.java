package by.uladzimirkalesny;

import javax.annotation.PostConstruct;

public class PolicemenImpl implements Policemen {

    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init() {
        System.out.println(recommender.getClass().getSimpleName());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("GO AWAY");
    }
}
