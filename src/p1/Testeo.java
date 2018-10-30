package p1;

import java.util.Random;

public class Testeo {
    public static void main(String[] args) {

        Database d = new Database("./producto.txt");
        Database d1 = new Database("./consumidor.txt");
        Random r = new Random();
        d.ordenar("ptsNec");

//        for (int i = 1; i <= 10000; i++) {
//            d.insertar(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
//        }
//
//        for (int i = 1; i <= 10000; i++) {
//            d1.insertar(new Consumidor(i,
//                    String.valueOf(r.nextInt(16000001) + 8000000) + "-" +
//                            String.valueOf(r.nextInt(10)),
//                    r.nextInt(9001) + 1000));
//        }

    }
}
