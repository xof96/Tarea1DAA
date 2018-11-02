package p1.create;

import p1.FileCreatorTest;

public class FilesA {

    public static void main(String[] args) {
        String tPath = "times1.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 1, 10);
    }
}
