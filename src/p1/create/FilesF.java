package p1.create;

import p1.test.FileTester;

public class FilesF {
    public static void main(String[] args) {
        String tPath = "times6.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 6, 4);
    }
}
