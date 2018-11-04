package p3;

import p1.nodo.Nodo;
import p2.btree.BNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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

        List<Nodo> consumidores10 = c1.searchByRange(0, -1, 1, 1);
        for (Nodo c : consumidores10) {
            int ptsAc = c.getAttr().get("ptsAc");
            int rut = c.getAttr().get("rut");
            System.out.println(String.format("----%ncliente <%d> tiene %d puntos:%n", rut, ptsAc));
            List<Nodo> productList = p1.searchLesser(ptsAc, 1);
            for (Nodo p : productList)
                System.out.println(p);

        }
    }
}
