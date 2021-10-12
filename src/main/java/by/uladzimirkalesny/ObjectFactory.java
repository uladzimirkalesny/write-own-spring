package by.uladzimirkalesny;

import lombok.SneakyThrows;

public class ObjectFactory {

    private static ObjectFactory objectFactory = new ObjectFactory();

    private Config config = new JavaConfig("by.uladzimirkalesny");

    public static ObjectFactory getObjectFactoryInstance() {
        return objectFactory;
    }

    private ObjectFactory() {
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
