package p1.create;

import p1.FileCreatorTest;

public class FilesB {
    public static void main(String[] args) {
        String tPath = "times2.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 2, 8);
    }
}
