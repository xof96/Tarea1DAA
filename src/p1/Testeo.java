package p1;

import java.util.Random;

public class Testeo {
    public static void main(String[] args) {
        Database d = new Database("./producto.txt");
        Random r = new Random();

        for (int i = 1; i <= 10000000; i++) {
            d.insertar(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
        }
    }
}
