package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Database {
    Path file;

    public Database(Path p) {
        file = p;
    }

    public void insertar(Nodo n) {
        try {
            Files.write(file, (n.toString() + String.format("%n")).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException x) {
            System.err.println(x);
        }

    }
}
