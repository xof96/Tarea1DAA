package p1.create;

import p1.FileCreatorTest;

public class FilesD {
    public static void main(String[] args) {
        String tPath = "times4.txt";
        FileCreatorTest test = new FileCreatorTest();
        test.createFilesProducto(tPath, 4, 5);
    }
}
