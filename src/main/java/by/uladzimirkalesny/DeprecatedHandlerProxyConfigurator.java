package by.uladzimirkalesny;

import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxy(Object candidateToProxy, Class classToProxy) {
        if (classToProxy.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(classToProxy.getClassLoader(), classToProxy.getInterfaces(), (proxy, method, args) -> {
                System.out.println("PROXY");
                return method.invoke(candidateToProxy, args);
            });
        } else {
            return candidateToProxy;
        }
    }
}
