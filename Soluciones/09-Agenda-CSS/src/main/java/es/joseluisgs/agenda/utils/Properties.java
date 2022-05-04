package es.joseluisgs.agenda.utils;

import java.io.File;

public class Properties {

    // Main
    public static final String APP_TITLE = "Agenda DAM";
    public static final int APP_HEIGHT = 450;
    public static final int APP_WIDTH = 850;
    public static final String APP_ICON = "icons/app-icon.png";

    // Splash
    public static final int SPLASH_HEIGHT = 300;
    public static final int SPLASH_WIDTH = 480;

    // Acerca de
    public static final String APP_VERSION = "0.0.1";
    public static final String APP_AUTHOR = "Jose Luis González Sánchez";
    public static final String APP_AUTHOR_GITHUB = "https://github.com/joseluisgs";
    public static final String APP_AUTHOR_TWITTER = "https://twitter.com/joseluisgonsan";
    public static final int ACERCADE_HEIGHT = 260;
    public static final int ACERCADE_WIDTH = 480;

    // Edit Data
    public static final int PERSONAEDITAR_HEIGHT = 427;
    public static final int PERSONAEDIT_WIDTH = 505;

    // Estadisticas
    public static final int ESTADISTICAS_HEIGHT = 450;
    public static final int ESTADISTICAS_WIDTH = 620;

    // Data
    private static final String APP_PATH = System.getProperty("user.dir");
    public static final String DATA_DIR = APP_PATH + File.separator + "data";
    public static final String BACKUP_DIR = DATA_DIR + File.separator + "backup";
    public static final String BACKUP_FILE = BACKUP_DIR + File.separator + "agenda.bak";
    public static final String IMAGES_DIR = DATA_DIR + File.separator + "images";


}
