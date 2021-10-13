package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Centralized place where we can create all objects.
 * If we need to change implementation we should not go to the code.
 * Before we're getting fully created object we can decorate it using ObjectConfigurator class.
 */
public class ObjectFactory {

    private static ObjectFactory objectFactory = new ObjectFactory();
    private List<ObjectConfigurator> objectConfiguratorList = new ArrayList<>();
    private Config config;

    public static ObjectFactory getObjectFactoryInstance() {
        return objectFactory;
    }

    @SneakyThrows
    private ObjectFactory() {
        this.config = new JavaConfig("by.uladzimirkalesny", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
//        this.config = new JavaConfig("by.uladzimirkalesny", new HashMap<>(Map.of(Policemen.class, PolicemenImpl.class)));
        for (Class<? extends ObjectConfigurator> clazz : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            this.objectConfiguratorList.add(clazz.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        objectConfiguratorList.forEach(objectConfigurator -> objectConfigurator.configure(t));

        return t;
    }
}
