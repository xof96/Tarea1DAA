package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtG {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times7.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/g";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 7, 4, criteria, btPath);
    }
}
