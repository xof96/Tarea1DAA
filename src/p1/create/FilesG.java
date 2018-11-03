package p1.create;

import p1.test.FileTester;

public class FilesG {
    public static void main(String[] args) {
        String tPath = "times7.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 7, 4);
    }
}
