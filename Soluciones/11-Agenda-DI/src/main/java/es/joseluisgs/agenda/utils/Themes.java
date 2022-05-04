package es.joseluisgs.agenda.utils;

public enum Themes {
    METRO("styles/metrodark.css"),
    DAM("styles/dam.css"),
    BOOTSTRAPT3("styles/bootstrapt3.css"),
    MODENA("styles/modena.css");

    private final String view;

    Themes(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
