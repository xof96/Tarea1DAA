package p1.nodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Database {
    private Path file;
    private String name;
    private static int MEMOSIZE = 100000;
    private static int MAX_INT = 2147483647;

    public Database(String path) {
        this.name = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
        this.file = Paths.get(path);
    }

    public void insertar(Nodo n) {
        try {
            Files.write(this.file, (n.toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            //Se inserta el nodo recibido como linea de texto en el archivo de la base de datos
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ordenar(String attr) {
        Nodo[] l = new Nodo[MEMOSIZE];//Arreglo en memoria principal para almacenar los nodos leidos
        Charset charset = Charset.forName("US-ASCII");
        Path[] temps = new Path[1000];//Arreglo de rutas de archivos temporales
        int tempsn = 0;//Numero de archivos temporales
        try (BufferedReader reader = Files.newBufferedReader(this.file, charset)) {
            String line;//linea leida
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == MEMOSIZE) { //Si se alcanzo el limite de memoria
                    mergeSort(l, i, attr);//mergesort sobre el arreglo de nodos en memoria principal
                    temps[tempsn] = Paths.get("./temp" + String.valueOf(tempsn) + ".txt");//ruta de archivo temporal con el trozo ordenado
                    for (int w = 0; w < i; w++) {
                        try {
                            Files.write(temps[tempsn], (l[w].toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            //Se escribe en el archivo temporal los nodos del arreglo ordenado
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    i = 0;
                    tempsn++;
                }
                List<String> nodostr = Arrays.asList(line.split(","));//Se crea un Producto a partir de los datos de la linea leida
                int id = Integer.parseInt(nodostr.get(0));
                int precio = Integer.parseInt(nodostr.get(1));
                int ptsNec = Integer.parseInt(nodostr.get(2));
                int ptsRec = Integer.parseInt(nodostr.get(3));
                Producto p = new Producto(id, precio, ptsNec, ptsRec);//Se coloca el producto en el arreglo
                l[i] = p;
                i++;
            }
            mergeSort(l, i, attr);//mergesort sobre el arreglo en el ultimo trozo leido
            temps[tempsn] = Paths.get("./temp" + String.valueOf(tempsn) + ".txt");
            for (int w = 0; w < i; w++) {//Se escribe el ultimo trozo
                try {
                    Files.write(temps[tempsn], (l[w].toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tempsn++;
            //Proceso para seleccionar el nodo minimo de cada archivo temporal para ser escrito en el archivo final ordenado
            Nodo[] nodosmin = new Nodo[tempsn];//Arreglo de nodos minimos
            BufferedReader[] readers = new BufferedReader[tempsn];//arreglo de los readers de cada archivo temporal
            for (int k = 0; k < tempsn; k++) {//se llena por primera vez el arreglo de nodos minimos
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
            Nodo max = new Producto(MAX_INT, MAX_INT, MAX_INT, MAX_INT);//Nodo de referencia para la comparacion
            Nodo min = max;//Se resetea el nodo minimo
            int index = 0;
            int[] lecturas = new int[tempsn];//Arreglo con el numero de lecturas restantes por archivo
            int nlecturas = MEMOSIZE * (tempsn - 1) + i;//Numero de lecturas totales

            for (int s = 0; s < tempsn; s++) {
                if (s != tempsn - 1) {//caso archivos temporales llenos
                    lecturas[s] = MEMOSIZE - 1;
                } else {//caso ultimo archivo temporal no lleno posiblemente
                    lecturas[s] = i - 1;
                }
            }
            while (nlecturas > 0) {
                for (int s = 0; s < tempsn; s++) {
                    if (lecturas[s] >= 0) {//Solo se comparan los nodos en los cuales el archivo no ha terminado de leerse
                        if (nodosmin[s].attr.get(attr) <= min.attr.get(attr)) {//Se comparan los nodos del arreglo de nodos minimos para obtener el minimo
                            min = nodosmin[s];
                            index = s;
                        }
                    }
                }
                lecturas[index]--;
                nlecturas--;
                if (lecturas[index] >= 0) {//Se actualiza el nodo en la posicion delnodo leido
                    String rawnode = readers[index].readLine();
                    List<String> nodostr = Arrays.asList(rawnode.split(","));
                    int id = Integer.parseInt(nodostr.get(0));
                    int precio = Integer.parseInt(nodostr.get(1));
                    int ptsNec = Integer.parseInt(nodostr.get(2));
                    int ptsRec = Integer.parseInt(nodostr.get(3));
                    Producto p = new Producto(id, precio, ptsNec, ptsRec);
                    nodosmin[index] = p;
                }//Se escribe el nodo minimo en el archivo final
                Files.write(Paths.get("./" + this.name + "orderedby" + attr + ".txt"), (min.toString() + String.format("%n")).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                min = max;
            }
            for (int a = 0; a < tempsn; a++){//Se borran los archivos temporales
                Files.delete(temps[a]);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static void mergeSort(Nodo[] a, int n, String attr) {//mergeSort de siempre, pero recibe el atributo por el que se compara
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Nodo[] l = new Nodo[mid];
        Nodo[] r = new Nodo[n - mid];

        System.arraycopy(a, 0, l, 0, l.length);
        System.arraycopy(a, mid, r, 0, r.length);

        mergeSort(l, mid, attr);
        mergeSort(r, n - mid, attr);

        merge(a, l, r, mid, n - mid, attr);
    }

    private static void merge(Nodo[] a, Nodo[] l, Nodo[] r, int left, int right, String attr) {//el merge recibe el atributo por el que se comparan los nodos

        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
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
