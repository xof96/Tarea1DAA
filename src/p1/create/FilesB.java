package p1.create;

import p1.test.FileTester;

public class FilesB {
    public static void main(String[] args) {
        String tPath = "times2.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 2, 8);
    }
}
