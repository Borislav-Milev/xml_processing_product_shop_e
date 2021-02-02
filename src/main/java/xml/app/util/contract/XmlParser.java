package xml.app.util.contract;

public interface XmlParser {

    <T> T parse(Class<T> objectClass, String filePath);

    <T> void export(T object, Class<T> objectClass, String filePath);
}
