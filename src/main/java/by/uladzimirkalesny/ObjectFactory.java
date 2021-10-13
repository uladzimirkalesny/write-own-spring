package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Centralized place where we can create all objects.
 * If we need to change implementation we should not go to the code.
 * Before we're getting fully created object we can decorate it using ObjectConfigurator class.
 */
public class ObjectFactory {

    private final ApplicationContext applicationContext;
    private List<ObjectConfigurator> objectConfiguratorList = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        for (Class<? extends ObjectConfigurator> clazz : applicationContext.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            this.objectConfiguratorList.add(clazz.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = implClass.getDeclaredConstructor().newInstance();

        objectConfiguratorList.forEach(objectConfigurator -> objectConfigurator.configure(t, applicationContext));

        return t;
    }
}
