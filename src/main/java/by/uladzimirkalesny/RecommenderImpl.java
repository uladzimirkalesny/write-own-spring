package by.uladzimirkalesny;

@Singleton
@Deprecated
public class RecommenderImpl implements Recommender {

    @InjectProperty
    private String alcohol;

    public RecommenderImpl() {
        System.out.println("RecommenderImpl was created.");
    }

    @Override
    public void recommend() {
        System.out.println("Drink " + alcohol + ". Protect yourself");
    }
}
