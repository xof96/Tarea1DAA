package test.serializable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPersonTest {
    public static void main(String[] args) {
        String path = "src/test/serializable/person.txt";
        Person p1 = new Person("Matías", 22);
        Person p2 = new Person("Ana", 23);
        Person p3 = new Person("Eduardo", 22);
        Person p4 = new Person("Elizabeth", 23);
        Person p5 = new Person("Jack", 5);


        List<Person> lp = new ArrayList<>();
        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(lp);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            List<Person> lpr = (List<Person>) ois.readObject();
            ois.close();
            for (Person p : lpr)
                System.out.println(p);
            lpr.add(p4);
            lpr.add(p5);
            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(path));
            oos2.writeObject(lpr);
            oos2.close();
            System.out.println("------------------------------");
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(path));
            List<Person> lpr2 = (List<Person>) ois2.readObject();
            ois2.close();
            for (Person p : lpr2)
                System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

//        try {
//            for (Person p : lp)
//                Files.write(path, (String.format("%s%n", p)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
