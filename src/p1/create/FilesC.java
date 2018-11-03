package p1.create;

import p1.test.FileCreatorTest;

public class FilesC {
    public static void main(String[] args) {
        String tPath = "times3.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 3, 6);
    }
}
