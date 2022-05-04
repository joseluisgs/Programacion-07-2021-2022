package es.joseluisgs.keepingnotes.Utils;

public class Resources {
    public static String get(Class<?> clazz, String resource) {
        return clazz.getResource(resource).toString();
    }
}
