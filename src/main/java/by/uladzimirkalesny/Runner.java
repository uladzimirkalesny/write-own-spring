package by.uladzimirkalesny;

import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                Application.run("by.uladzimirkalesny", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));

        CoronaDisinfectant coronaDisinfectant = applicationContext.getObject(CoronaDisinfectant.class);

        coronaDisinfectant.startDisinfectionRoom(new Room());
    }
}
