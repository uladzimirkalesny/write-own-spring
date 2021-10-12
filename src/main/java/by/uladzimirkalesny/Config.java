package by.uladzimirkalesny;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> type);
}
