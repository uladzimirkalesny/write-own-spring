package by.uladzimirkalesny;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> interfaceToImplClass) {
        JavaConfig javaConfig = new JavaConfig(packageToScan, interfaceToImplClass);
        ApplicationContext applicationContext = new ApplicationContext(javaConfig);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setObjectFactory(objectFactory);

        return applicationContext;
    }
}
