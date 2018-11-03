package p2.test;

import p1.nodo.Producto;
import p2.btree.BTree;

import java.io.IOException;
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
