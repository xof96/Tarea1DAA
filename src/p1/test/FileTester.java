package p1.test;

import p1.nodo.Database;
import p1.nodo.Producto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class FileTester {

    public void insertionTestProducto(String timePath, int pow, int nExp) {
        Path file = Paths.get(timePath);
        Random r = new Random();

        for (int i = 1; i <= nExp; i++) {
            System.out.println(String.format("\nExperimento n°%d", i));
            int inputs = (int) Math.pow(10, pow);
            String tmp = String.format("./producto-%d.txt", i);
            Database dataInUse = new Database(tmp);
            long startTime = System.currentTimeMillis();
            for (int j = 1; j <= inputs; j++)
                dataInUse.insertar(new Producto(j, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            String nameTest = String.format("time%d: ", i);
            try {
                Files.write(file, String.format("%s%sms%n", nameTest, Long.toString(elapsedTime)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                File f = new File(tmp);
                if(f.delete())
                    System.out.printf("%s borrado%n", tmp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
