package es.joseluisgs.dam.loginjavafx;

public class Utils {
    public static String getResource(String resource) {
        return Utils.class.getResource(resource).toString();
    }
}
