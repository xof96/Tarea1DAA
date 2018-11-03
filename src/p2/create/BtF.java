package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtF {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times6.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/f";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 6, 4, criteria, btPath);
    }
}
