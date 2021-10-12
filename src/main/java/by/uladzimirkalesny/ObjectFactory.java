package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

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

        // decorate object
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty injectPropertyAnnotation = field.getAnnotation(InjectProperty.class);
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            try {
                Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
                Map<String, String> propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));

                if (injectPropertyAnnotation != null) {
                    String value = injectPropertyAnnotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(injectPropertyAnnotation.value());
                    field.setAccessible(true);
                    field.set(t, value);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return t;
    }
}
