package test;

import p1.Nodo;
import p1.Producto;
import p2.BTree;

import java.util.List;
import java.util.Random;

public class BTreeSearchingTest {

    public static void main(String[] args) {
        Random r = new Random();

        BTree b = new BTree(100, "id");

        for (int i = 1; i <= 1000; i++) {
            b.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
        }

        b.printBT();

        System.out.println("----------------------------------------------------------------------------");

        List<Nodo> l = b.search(21);

        for (Nodo n : l)
            System.out.println(n);
    }
}
