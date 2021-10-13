package by.uladzimirkalesny;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Like Autowired handler into Spring Framework.
 */
public class InjectByTypeObjectConfigurator implements ObjectConfigurator {
    @SneakyThrows
    @Override
    public void configure(Object object, ApplicationContext context) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object createdObject = context.getObject(field.getType());
                field.set(object, createdObject);
            }
        }
    }
}
