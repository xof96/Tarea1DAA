package p1;

import javax.xml.crypto.Data;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Testeo {
    public static void main(String[] args) {
        Producto prod=new Producto(1,100, 200, 300);
        Consumidor c=new Consumidor(23,"19514213-0",0);
        Path p= Paths.get("C:\\Users\\javie\\Desktop\\dict.txt");
        Database d=new Database(p);
        d.insertar(c);
        d.insertar(prod);
        d.insertar(c);
        System.out.println("Hello World!"); // Display the string.
    }
}
