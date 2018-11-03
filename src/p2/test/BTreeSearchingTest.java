package p2.test;

import p1.nodo.Nodo;
import p2.btree.BNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class BTreeSearchingTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Random r = new Random();
//
//        BTree b = new BTree(100, "precio", "src/p2/files/a");
//
//        for (int i = 1; i <= 1000; i++) {
//            b.insert(new Producto(i, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
//        }
//
//        b.printBT();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/p2/files/af"));
        BNode b = (BNode) ois.readObject();
        ois.close();

        System.out.println("----------------------------------------------------------------------------");
//        System.out.println(b.getRootPath());

        List<Nodo> l = b.search(4731);

        for (Nodo n : l)
            System.out.println(n);

        System.out.println("----------------------------------------------------------------------------");

        List<Nodo> l1 = b.search(5159);

        for (Nodo n : l1)
            System.out.println(n);
    }
}
