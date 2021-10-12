package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {

    private static ObjectFactory objectFactory = new ObjectFactory();

    private Config config;

    public static ObjectFactory getObjectFactoryInstance() {
        return objectFactory;
    }

    private ObjectFactory() {
        this.config = new JavaConfig("by.uladzimirkalesny", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
//        this.config = new JavaConfig("by.uladzimirkalesny", new HashMap<>(Map.of(Policemen.class, PolicemenImpl.class)));
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        // todo

        return t;
    }
}
