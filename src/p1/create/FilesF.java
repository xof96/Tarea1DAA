package p1.create;

import p1.test.FileCreatorTest;

public class FilesF {
    public static void main(String[] args) {
        String tPath = "times6.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 6, 4);
    }
}
