package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtB {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times2.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/b";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 2, 8, criteria, btPath);
    }
}
