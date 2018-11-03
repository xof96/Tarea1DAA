package test;

import p1.Nodo;
import p1.Producto;
import p2.BNode;
import p2.BTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

public class BTreeInsertionTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Random r = new Random();

        BTree b = new BTree(5, "precio", "src/p2/files/b");

        for (int i = 1; i <= 100; i++) {
            b.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
        }
        b.printBT();
        System.out.println(b.getRootPath());

//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/p2/files/bf"));
//        BNode n = (BNode) ois.readObject();
//        ois.close();
//        n.printBT();
    }
}
