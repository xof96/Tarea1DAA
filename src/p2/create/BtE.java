package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtE {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times5.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/e";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 5, 5, criteria, btPath);
    }
}
