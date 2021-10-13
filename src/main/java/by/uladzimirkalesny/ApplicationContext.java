package by.uladzimirkalesny;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory objectFactory;
    private Map<Class, Object> singletonCache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        if (singletonCache.containsKey(type)) {
            return (T) singletonCache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T createdObject = objectFactory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            singletonCache.put(type, createdObject);
        }

        return createdObject;
    }
}
