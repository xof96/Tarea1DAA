package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Database {
    Path file;

    public Database(String path) {
        this.file = Paths.get(path);
    }

    public void insertar(Nodo n) {
        try {
            Files.write(this.file, (n.toString() + String.format("%n")).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException x) {
            System.err.println(x);
        }

    }
}
