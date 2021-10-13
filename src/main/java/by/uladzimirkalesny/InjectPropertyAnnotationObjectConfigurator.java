package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();

        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        this.propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object object) {
        Class<?> implClass = object.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty injectPropertyAnnotation = field.getAnnotation(InjectProperty.class);
            if (injectPropertyAnnotation != null) {
                String value = injectPropertyAnnotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(injectPropertyAnnotation.value());
                field.setAccessible(true);
                field.set(object, value);
            }
        }
    }
}
