package by.uladzimirkalesny;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    @Getter
    private Reflections scanner;

    private Map<Class, Class> interfaceToImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> interfaceToImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.interfaceToImplClass = interfaceToImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> type) {
        return interfaceToImplClass.computeIfAbsent(type, clazz -> {
            Set<Class<? extends T>> types = scanner.getSubTypesOf(type);
            if (types.size() != 1) {
                throw new RuntimeException(type + " has 0 or more than one impl please update your config");
            }
            return types.iterator().next();
        });

    }
}
