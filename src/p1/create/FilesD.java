package p1.create;

import p1.test.FileTester;

public class FilesD {
    public static void main(String[] args) {
        String tPath = "times4.txt";
        FileTester test = new FileTester();
        test.insertionTestProducto(tPath, 4, 5);
    }
}
