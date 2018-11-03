package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtC {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times3.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/c";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 3, 6, criteria, btPath);
    }
}
