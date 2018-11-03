package p1.create;

import p1.test.FileTester;

public class FilesC {
    public static void main(String[] args) {
        String tPath = "times3.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 3, 6);
    }
}
