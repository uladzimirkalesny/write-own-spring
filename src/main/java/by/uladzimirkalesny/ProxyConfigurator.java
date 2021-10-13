package by.uladzimirkalesny;

public interface ProxyConfigurator {

    Object replaceWithProxy(Object candidateToProxy, Class classToProxy);

}
