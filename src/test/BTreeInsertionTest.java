package test;

import p1.Producto;
import p2.BTree;

import java.util.Random;

public class BTreeInsertionTest {
    public static void main(String[] args) {

        Random r = new Random();

        BTree b = new BTree(5, "ptsNec");

        for (int i = 1; i <= 1000; i++) {
            b.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
        }
        b.printBT();


    }
}
