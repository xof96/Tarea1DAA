package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

class FileCreatorTest {

    void createFilesProductor() {
        Path file = Paths.get("./times.txt");
        Random r = new Random();

        for (int i = 1; i <= 7; i++) {
            System.out.println(i);
            double inputs = Math.pow(10, i);
            Database dataInUse = new Database("./producto-" + Integer.toString(i) + ".txt");
            long startTime = System.currentTimeMillis();
            for (double j = 1; j <= inputs; j++) {
                dataInUse.insertar(new Producto((int) j, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));

            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            String nameTest = "Test 10 a la " + Integer.toString(i) + " with time: \n";
            try {
                Files.write(file, (nameTest + Long.toString(elapsedTime) + "\n" + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
