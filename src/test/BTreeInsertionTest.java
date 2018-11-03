package test;

import p2.BNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BTreeInsertionTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Random r = new Random();
//
//        BTree b = new BTree(1000, "id", "src/p2/files/b");
//
//        for (int i = 1; i <= 10000; i++) {
//            System.out.println(i);
//            b.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
//        }
////        b.insert(new Producto(10, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
//        b.printBT();
//        System.out.println(b.getRootPath());

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/p2/files/b"));
        BNode n = (BNode) ois.readObject();
        ois.close();
        n.printBT();
    }
}
