package test;

import java.util.ArrayList;
import java.util.List;

public class ArrayWorkingTest {

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        List<Integer> r = new ArrayList<>();
        r.add(1);
        r.add(2);
        r.add(3);
        r.add(4);
        r.add(5);

        System.out.println("largo: " + l.size());

        l.addAll(r);

        System.out.println("largo: " + l.size());

        for (int i = 0; i < l.size(); i++) {
            System.out.println("elem " + i + " es: " + l.get(i));
        }

    }
}
