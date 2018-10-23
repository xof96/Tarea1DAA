package p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Database {
    private Path file;

    Database(String path) {

        this.file = Paths.get(path);
    }

    void insertar(Nodo n) {
        try {
            Files.write(this.file, (n.toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ordenar(String attr) {
        /*Servira para separar en caso si el archivo es grande o no
        long size=file.toFile().length();
        System.out.println(size);*/
        Nodo[] l = new Nodo[1000];

        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(this.file, charset)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i != 1000) {
                List<String> nodostr = Arrays.asList(line.split(","));
                String id = nodostr.get(0);
                String precio = nodostr.get(1);
                String ptsNec = nodostr.get(2);
                String ptsRec = nodostr.get(3);
                Producto p = new Producto(id, precio, ptsNec, ptsRec);
                l[i] = p;
                i++;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        mergeSort(l, l.length, attr);
        System.out.println(l[0].attr.get(attr));

    }

    public static void mergeSort(Nodo[] a, int n, String attr) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Nodo[] l = new Nodo[mid];
        Nodo[] r = new Nodo[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid, attr);
        mergeSort(r, n - mid, attr);

        merge(a, l, r, mid, n - mid, attr);
    }

    public static void merge(Nodo[] a, Nodo[] l, Nodo[] r, int left, int right, String attr) {

        int i = 0, j = 0, k = 0;
        System.out.println(l[i].attr.get(attr));
        System.out.println(r[j].attr.get(attr));

/*        while (i < left && j < right) {
            System.out.println(l[i].attr.get(attr));
            System.out.println(r[j].attr.get(attr));
            if (l[i].attr.get(attr).compareTo(r[j].attr.get(attr)) < 0) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }*/
    }
}
