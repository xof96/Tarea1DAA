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
         List<Nodo> l=new ArrayList<Nodo>();

        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null && l.size()<=1000) {
                List<String> nodostr = Arrays.asList(line.split(","));
                int id=Integer.parseInt(nodostr.get(0).replaceAll("\\s+",""));
                int precio=Integer.parseInt(nodostr.get(1).replaceAll("\\s+",""));
                int ptsNec=Integer.parseInt(nodostr.get(2).replaceAll("\\s+",""));
                int ptsRec=Integer.parseInt(nodostr.get(3).replaceAll("\\s+",""));
                Producto p=new Producto(id,precio,ptsNec,ptsRec);
                l.add(p);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        //System.out.println(l.get(1));

    }
}
