package p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class FileCreatorTest {

    public void createFilesProductor() {
        Path file = Paths.get("./times.txt");
        Database[] datas = new Database[7];
        Random r = new Random();

        for (int i = 1; i <= 7; i++) {
            System.out.println(i);
            double inputs = Math.pow(10, i);
            long startTime = System.currentTimeMillis();
            Database dataInUse = datas[i - 1];
            dataInUse = new Database("./producto-" + Integer.toString(i) + ".txt");
            for (double j = 1; j <= inputs; j++) {
                dataInUse.insertar(new Producto((int)j, r.nextInt(9991) + 10, r.nextInt(9001) + 1000, r.nextInt(991) + 10));

            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            String nameTest = "Test 10 a la " + Integer.toString(i) + " with time: ";
            try {
                Files.write(file, (String.format(nameTest) + Long.toString(elapsedTime) + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
