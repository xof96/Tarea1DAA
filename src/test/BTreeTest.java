package test;

import p1.Nodo;
import p1.Producto;
import p2.BTree;

public class BTreeTest {
    public static void main(String[] args) {
        Nodo n1 = new Producto(0, 5990, 7000, 150);
        Nodo n2 = new Producto(1, 1990, 3000, 150);
        Nodo n3 = new Producto(2, 2990, 3000, 150);
        Nodo n4 = new Producto(3, 3990, 7000, 200);
        Nodo n5 = new Producto(4, 4990, 8000, 150);
        Nodo n6 = new Producto(5, 5990, 10000, 500);
        Nodo n7 = new Producto(6, 3990, 4000, 150);

        BTree b = new BTree(5, "precio");
        b.insert(n1);
        b.insert(n2);
        b.insert(n3);
        b.insert(n4);
        b.insert(n5);
        b.insert(n6);
        b.insert(n7);
        b.printBT();

    }
}
