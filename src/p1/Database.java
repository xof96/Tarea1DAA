package p1;
import java.nio.file.Files;
import java.nio.file.Path;

public class Database {
    Path file;

    public Database(Path p){
        file=p;
    }

    public void insertar(Nodo n){
        Files.write(file,n.toString().getBytes());
    }
}
