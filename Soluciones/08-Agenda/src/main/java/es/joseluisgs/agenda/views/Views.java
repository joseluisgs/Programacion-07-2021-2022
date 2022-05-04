package es.joseluisgs.agenda.views;

public enum Views {
    SPLASH("views/splash-view.fxml"),
    MAIN("views/agenda-view.fxml"),
    ACERCADE("views/acercade-view.fxml"),
    PERSONAEDITAR("views/personaeditar-view.fxml");

    private String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
