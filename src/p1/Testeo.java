package p1;

import javax.xml.crypto.Data;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Testeo {
    public static void main(String[] args) {
        Producto prod = new Producto(1, 100, 200, 300);
        Consumidor c = new Consumidor(23, "19514213-0", 0);
        Database d = new Database("./dict.txt");
        d.insertar(c);
        d.insertar(prod);
    }
}