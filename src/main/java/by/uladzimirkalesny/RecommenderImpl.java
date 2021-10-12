package by.uladzimirkalesny;

public class RecommenderImpl implements Recommender {

    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("Drink " + alcohol + ". Protect yourself");
    }
}
