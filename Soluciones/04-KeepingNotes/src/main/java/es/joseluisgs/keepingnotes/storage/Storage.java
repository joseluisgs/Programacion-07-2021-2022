package es.joseluisgs.keepingnotes.storage;

import es.joseluisgs.keepingnotes.repositories.NotasRepository;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private static Storage instance;
    private final String APP_PATH = System.getProperty("user.dir");
    private final String DIR = "data";
    private final String FILE = "KeepingNotes.bak";

    private Storage() {
        makeDirectory();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private void makeDirectory() {
        if (!Files.exists(Paths.get(APP_PATH + File.separator + DIR))) {
            try {
                Files.createDirectory(Paths.get(APP_PATH + File.separator + DIR));
            } catch (IOException e) {
                System.err.println("Error al crear directorio de backup");
            }
        }
    }

    public void save(NotasRepository notasRepository) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(Paths.get(APP_PATH + File.separator + DIR + File.separator + FILE))
        );
        oos.writeObject(notasRepository);
        oos.close();
    }

    public NotasRepository load() throws IOException, ClassNotFoundException {
        NotasRepository notasRepository = null;
        ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(Paths.get(APP_PATH + File.separator + DIR + File.separator + FILE))
        );
        notasRepository = (NotasRepository) ois.readObject();
        ois.close();
        return notasRepository;
    }
}
