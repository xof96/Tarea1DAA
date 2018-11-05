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


public class MergeTester {

    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            String nameData = String.format("./producto-%d.txt", i);
            String file = String.format("./timeMerge-%d.txt", i);
            Path fileP = Paths.get(file);
            long startTime = System.currentTimeMillis();
            Database d = new Database(nameData);
            d.ordenar("precio");
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            String nameTest = String.format("timeMerge%d: ", i);
            try {
                Files.write(fileP, String.format("%s%sms%n", nameTest, Long.toString(elapsedTime)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

