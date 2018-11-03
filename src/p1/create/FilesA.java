package p1.create;

import p1.test.FileTester;

public class FilesA {

    public static void main(String[] args) {
        String tPath = "times1.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 1, 10);
    }
}
