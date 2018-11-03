package p2.test;

import p1.nodo.Producto;
import p2.btree.BTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class BTreeTester {

    public void insertionTestProducto(String timePath, int pow, int nExp, String criteria, String btPath)
            throws IOException, ClassNotFoundException {

        Path file = Paths.get(timePath);
        Random r = new Random();

        for (int i = 1; i <= nExp; i++) {
            System.out.println(i);
            int inputs = (int) Math.pow(10, pow);
            int B = 1000;
            BTree bt = new BTree(B, criteria, String.format("%s%d", btPath, i));
            long startTime = System.currentTimeMillis();
            for (int j = 1; j <= inputs; j++)
                bt.insert(new Producto(j, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            String nameTest = String.format("time%d: ", i);
            try {
                Files.write(file, String.format("%s%sms%n%n", nameTest, Long.toString(elapsedTime)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
