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
import java.util.Scanner;

class Database {
    private Path file;
    private static int MEMOSIZE = 100;
    private static int MAX_INT = 2147483647;

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
        Nodo[] l = new Nodo[MEMOSIZE];
        Charset charset = Charset.forName("US-ASCII");
        Path[] temps = new Path[1000];
        int tempsn = 0;
        try (BufferedReader reader = Files.newBufferedReader(this.file, charset)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == MEMOSIZE) {
                    mergeSort(l, i, attr);
                    temps[tempsn] = Paths.get("./temp" + String.valueOf(tempsn) + ".txt");
                    for (int w = 0; w < i; w++) {
                        try {
                            Files.write(temps[tempsn], (l[w].toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    i = 0;
                    tempsn++;
                }
                List<String> nodostr = Arrays.asList(line.split(","));
                int id = Integer.parseInt(nodostr.get(0));
                int precio = Integer.parseInt(nodostr.get(1));
                int ptsNec = Integer.parseInt(nodostr.get(2));
                int ptsRec = Integer.parseInt(nodostr.get(3));
                Producto p = new Producto(id, precio, ptsNec, ptsRec);
                l[i] = p;
                i++;
            }
            mergeSort(l, i, attr);
            temps[tempsn] = Paths.get("./temp" + String.valueOf(tempsn) + ".txt");
            for (int w = 0; w < i; w++) {
                try {
                    Files.write(temps[tempsn], (l[w].toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tempsn++;

            Nodo[] nodosmin = new Nodo[tempsn];
            BufferedReader[] readers = new BufferedReader[tempsn];
            for (int k = 0; k < tempsn; k++) {
                readers[k] = Files.newBufferedReader(temps[k], charset);
                String rawnode = readers[k].readLine();
                List<String> nodostr = Arrays.asList(rawnode.split(","));
                int id = Integer.parseInt(nodostr.get(0));
                int precio = Integer.parseInt(nodostr.get(1));
                int ptsNec = Integer.parseInt(nodostr.get(2));
                int ptsRec = Integer.parseInt(nodostr.get(3));
                Producto p = new Producto(id, precio, ptsNec, ptsRec);
                nodosmin[k] = p;
            }
            Nodo max= new Producto(MAX_INT,MAX_INT,MAX_INT,MAX_INT);
            Nodo min = max;
            int index = 0;
            int[] lecturas = new int[tempsn];
            int nlecturas=MEMOSIZE*(tempsn-1)+i;//-tempsn;

            for (int s = 0; s < tempsn; s++) {
                if(s!=tempsn-1){//caso archivos temporales llenos
                    lecturas[s]=MEMOSIZE-1;
                }
                else{//caso ultimo archivo temporal no lleno posiblemente
                    lecturas[s]=i-1;
                }
            }
            while(nlecturas>0) {
                for (int s = 0; s < tempsn; s++) {
                    if (lecturas[s]>=0) {
                        if (nodosmin[s].attr.get(attr) <= min.attr.get(attr)) {
                            min = nodosmin[s];
                            index = s;
                        }
                    }
                }
                lecturas[index]--;
                nlecturas--;
                if (lecturas[index] >= 0) {
                    String rawnode = readers[index].readLine();
                    List<String> nodostr = Arrays.asList(rawnode.split(","));
                    int id = Integer.parseInt(nodostr.get(0));
                    int precio = Integer.parseInt(nodostr.get(1));
                    int ptsNec = Integer.parseInt(nodostr.get(2));
                    int ptsRec = Integer.parseInt(nodostr.get(3));
                    Producto p = new Producto(id, precio, ptsNec, ptsRec);
                    nodosmin[index] = p;
                }
                Files.write(Paths.get("./final.txt"), (min.toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                min=max;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static void mergeSort(Nodo[] a, int n, String attr) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Nodo[] l = new Nodo[mid];
        Nodo[] r = new Nodo[n - mid];

        System.arraycopy(a, 0, l, 0, l.length);
        System.arraycopy(a, mid, r, 0, r.length);

        /*System.out.println("Left:");
        System.out.println(l.length);
        for(int p=0;p<l.length;p++){
            System.out.println(l[p]);
        }
        System.out.println("Right:");
        for(int q=0;q<r.length;q++){
            System.out.println(r[q]);
        }*/

        mergeSort(l, mid, attr);
        mergeSort(r, n - mid, attr);

        merge(a, l, r, mid, n - mid, attr);
    }

    private static void merge(Nodo[] a, Nodo[] l, Nodo[] r, int left, int right, String attr) {

        int i = 0, j = 0, k = 0;
        //System.out.println(l[i]);//.attr.get(attr));
        //System.out.println(r[j]);//.attr.get(attr));

        while (i < left && j < right) {
            //System.out.println(l[i].attr.get(attr));
            //System.out.println(r[j].attr.get(attr));
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
        }
    }
}
