package p1.create;

import p1.FileCreatorTest;

public class FilesG {
    public static void main(String[] args) {
        String tPath = "times7.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 7, 4);
    }
}
