package by.uladzimirkalesny;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        for (Class<? extends ObjectConfigurator> clazz : applicationContext.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            this.objectConfiguratorList.add(clazz.getDeclaredConstructor().newInstance());
        }

        for (Class<? extends ProxyConfigurator> clazz : applicationContext.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            this.proxyConfigurators.add(clazz.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = replaceWithProxy(implClass, t);

        return t;
    }

    private <T> T replaceWithProxy(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxy(t, implClass);
        }
        return t;
    }

    private <T> T create(Class<T> implClass) throws Exception {
        return implClass.getDeclaredConstructor().newInstance();
    }

    private <T> void configure(T t) {
        objectConfiguratorList.forEach(objectConfigurator -> objectConfigurator.configure(t, applicationContext));
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method declaredMethod : implClass.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(PostConstruct.class)) {
                declaredMethod.invoke(t);
            }
        }
    }
}
