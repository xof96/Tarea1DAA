package p2.create;


import p2.test.BTreeTester;

import java.io.IOException;

public class BtA {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times1.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/a";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 1, 10, criteria, btPath);
    }
}
