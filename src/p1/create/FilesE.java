package p1.create;

import p1.test.FileTester;

public class FilesE {
    public static void main(String[] args) {
        String tPath = "times5.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 5, 5);
    }
}
