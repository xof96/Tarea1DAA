package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Database {
    private Path file;

    Database(String path) {

        this.file = Paths.get(path);
    }

    void insertar(Nodo n) {
        try {
            Files.write(this.file, (n.toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
