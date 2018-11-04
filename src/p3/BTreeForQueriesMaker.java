package p3;

import p1.nodo.Consumidor;
import p1.nodo.Producto;
import p2.btree.BTree;

import java.io.IOException;
import java.util.Random;

public class BTreeForQueriesMaker {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Random r = new Random();
        String path = "src/p3/files/";

        for (int t = 1; t <= 4; t++) {
            BTree bProducto = new BTree(100, "ptsNec", String.format("%sproducto%d", path, t));
            int inputs = (int) Math.pow(10, t);

            for (int i = 1; i <= inputs; i++) {
                bProducto.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
            }

            System.out.println(bProducto.getRootPath());

            BTree bConsumidor = new BTree(100, "ptsAc", String.format("%sconsumidor%d", path, t));

            for (int i = 1; i <= inputs; i++) {
                bConsumidor.insert(new Consumidor(i, r.nextInt(17000001) + 8000000, r.nextInt(10001)));
            }

            System.out.println(bConsumidor.getRootPath());
        }

    }
}
