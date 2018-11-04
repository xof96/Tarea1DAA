package p3;

import p1.nodo.Nodo;
import p2.btree.BNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Hashtable;
import java.util.List;

public class QueryMaker {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String pathP1 = "src/p3/files/producto1";
        String pathC1 = "src/p3/files/consumidor1";
        String pathP2 = "src/p3/files/producto2";
        String pathC2 = "src/p3/files/consumidor2";
        String pathP3 = "src/p3/files/producto3f";
        String pathC3 = "src/p3/files/consumidor3f";
        String pathP4 = "src/p3/files/producto4ff";


        ObjectInputStream oisp1 = new ObjectInputStream(new FileInputStream(pathP1));
        ObjectInputStream oisc1 = new ObjectInputStream(new FileInputStream(pathC1));
        ObjectInputStream oisp2 = new ObjectInputStream(new FileInputStream(pathP2));
        ObjectInputStream oisc2 = new ObjectInputStream(new FileInputStream(pathC2));
        ObjectInputStream oisp3 = new ObjectInputStream(new FileInputStream(pathP3));
        ObjectInputStream oisc3 = new ObjectInputStream(new FileInputStream(pathC3));
        ObjectInputStream oisp4 = new ObjectInputStream(new FileInputStream(pathP4));

        BNode p1 = (BNode) oisp1.readObject();
        BNode c1 = (BNode) oisc1.readObject();
        BNode p2 = (BNode) oisp2.readObject();
        BNode c2 = (BNode) oisc2.readObject();
        BNode p3 = (BNode) oisp3.readObject();
        BNode c3 = (BNode) oisc3.readObject();
        BNode p4 = (BNode) oisp4.readObject();

        /*
         * Para cada cliente, consultar por todos los productos que no requieren más puntos de los que él posee,
         * y listar al cliente con cada producto.
         */

        String timePath = "src/p3/res/times";

        doQuery1(c1, p1, "src/p3/res/c1p1.txt", timePath, "c1p1-Q1: ");
        doQuery1(c1, p2, "src/p3/res/c1p2.txt", timePath, "c1p2-Q1: ");
        doQuery1(c1, p3, "src/p3/res/c1p3.txt", timePath, "c1p3-Q1: ");
        doQuery1(c1, p4, "src/p3/res/c1p4.txt", timePath, "c1p4-Q1: ");
        doQuery1(c2, p1, "src/p3/res/c2p1.txt", timePath, "c2p1-Q1: ");
        doQuery1(c2, p2, "src/p3/res/c2p2.txt", timePath, "c2p2-Q1: ");
        doQuery1(c2, p3, "src/p3/res/c2p3.txt", timePath, "c2p3-Q1: ");
        doQuery1(c2, p4, "src/p3/res/c2p4.txt", timePath, "c2p4-Q1: ");
        doQuery1(c3, p1, "src/p3/res/c3p1.txt", timePath, "c3p1-Q1: ");
        doQuery1(c3, p2, "src/p3/res/c3p2.txt", timePath, "c3p2-Q1: ");
        doQuery1(c3, p3, "src/p3/res/c3p3.txt", timePath, "c3p3-Q1: ");
        doQuery1(c3, p4, "src/p3/res/c3p4.txt", timePath, "c3p4-Q1: ");


        /*
         * Para cada producto, consultar por todos los clientes que tienen al menos tantos puntos como cuesta
         * el producto, y listar al producto con cada uno de esos clientes
         */

        doQuery2(c1, p1, "src/p3/res/c1p1.txt", timePath, "c1p1-Q2: ");
        doQuery2(c1, p2, "src/p3/res/c1p2.txt", timePath, "c1p2-Q2: ");
        doQuery2(c1, p3, "src/p3/res/c1p3.txt", timePath, "c1p3-Q2: ");
        doQuery2(c1, p4, "src/p3/res/c1p4.txt", timePath, "c1p4-Q2: ");
        doQuery2(c2, p1, "src/p3/res/c2p1.txt", timePath, "c2p1-Q2: ");
        doQuery2(c2, p2, "src/p3/res/c2p2.txt", timePath, "c2p2-Q2: ");
        doQuery2(c2, p3, "src/p3/res/c2p3.txt", timePath, "c2p3-Q2: ");
        doQuery2(c2, p4, "src/p3/res/c2p4.txt", timePath, "c2p4-Q2: ");
        doQuery2(c3, p1, "src/p3/res/c3p1.txt", timePath, "c3p1-Q2: ");
        doQuery2(c3, p2, "src/p3/res/c3p2.txt", timePath, "c3p2-Q2: ");
        doQuery2(c3, p3, "src/p3/res/c3p3.txt", timePath, "c3p3-Q2: ");
        doQuery2(c3, p4, "src/p3/res/c3p4.txt", timePath, "c3p4-Q2: ");

    }

    private static void doQuery1(BNode c1, BNode p1, String cpPath, String timePath, String title) throws IOException, ClassNotFoundException {
        System.out.println(cpPath);
        long startTime = System.currentTimeMillis();
        Hashtable<Nodo, List<Nodo>> c1p1 = writeQ1(c1, p1);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Path fileC1P1 = Paths.get(cpPath);
        Path fileTime = Paths.get(timePath);
        try {
            Files.write(fileTime, String.format("%s %dms%n", title, elapsedTime).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(fileC1P1, String.format("-----Consumidor | Producto-----%n%n").getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Nodo n : c1p1.keySet()) {
            for (Nodo m : c1p1.get(n)) {
                try {
                    Files.write(fileC1P1, String.format("%s | %s%n", n.toString(), m.toString()).getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void doQuery2(BNode c1, BNode p1, String cpPath, String timePath, String title) throws IOException, ClassNotFoundException {
        System.out.println(cpPath);
        long startTime = System.currentTimeMillis();
        Hashtable<Nodo, List<Nodo>> c1p1 = writeQ2(c1, p1);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Path fileC1P1 = Paths.get(cpPath);
        Path fileTime = Paths.get(timePath);
        try {
            Files.write(fileTime, String.format("%s %dms%n", title, elapsedTime).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(fileC1P1, String.format("------Producto | Consumidor------%n%n").getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Nodo n : c1p1.keySet()) {
            for (Nodo m : c1p1.get(n)) {
                try {
                    Files.write(fileC1P1, String.format("%s | %s%n", n.toString(), m.toString()).getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Hashtable<Nodo, List<Nodo>> writeQ1(BNode c1, BNode p1) throws IOException, ClassNotFoundException {
        Hashtable<Nodo, List<Nodo>> res = new Hashtable<>();
        List<Nodo> consumidores = c1.searchBigger(0, 1);
        for (Nodo c : consumidores) {
            int ptsAc = c.getAttr().get("ptsAc");
            List<Nodo> productList1 = p1.searchLesser(ptsAc, 1);
            res.put(c, productList1);
        }
        return res;
    }

    private static Hashtable<Nodo, List<Nodo>> writeQ2(BNode c1, BNode p1) throws IOException, ClassNotFoundException {

        Hashtable<Nodo, List<Nodo>> res = new Hashtable<>();
        List<Nodo> productos = p1.searchBigger(0, 1);
        for (Nodo p : productos) {
            int ptsNec = p.getAttr().get("ptsAc");
            List<Nodo> conList1 = c1.searchBigger(ptsNec, 1);
            res.put(p, conList1);
        }
        return res;
    }
}
