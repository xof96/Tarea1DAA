package p2.create;

import p2.test.BTreeTester;

import java.io.IOException;

public class BtD {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String tPath = "src/p2/times4.txt";
        String criteria = "precio";
        String btPath = "src/p2/files/d";
        BTreeTester test = new BTreeTester();
        test.insertionTestProducto(tPath, 4, 5, criteria, btPath);
    }
}
