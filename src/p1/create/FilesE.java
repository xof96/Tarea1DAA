package p1.create;

import p1.FileCreatorTest;

public class FilesE {
    public static void main(String[] args) {
        String tPath = "times5.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 5, 5);
    }
}
