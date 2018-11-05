package p2.btree;

import p1.nodo.Nodo;

import java.io.*;
import java.util.List;

public class BTree {
    private int b;
    private String criteria;
    private String rootPath;

    /*
     * Constructor de la clase.
     */
    public BTree(int b, String criteria, String path) throws IOException {
        // Se guarda la raíz del árbol en un archivo.
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(new BLeaf(b, criteria, path));
        oos.close();
        this.b = b;
        this.criteria = criteria;
        this.rootPath = path;
    }

    /*
     * Inserta un Nodo en el árbol.
     */
    public void insert(Nodo n) throws IOException, ClassNotFoundException {
        // Se lee el nodo raíz del archivo en donde se encuentra.
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rootPath));
        BNode root = (BNode) ois.readObject();
        ois.close();
        SplitResponse sr = root.insert(this, n); // Se inserta

        if (sr != null) { // Si el nodo raíz necesita hacer split.
            System.out.println("La raíz hace split");
            this.setRootPath(sr.getfPath());
            BInner bi = new BInner(this.b, this.criteria, this.rootPath);
            bi.insertChildPath(sr.getrPath(), 0);
            bi.insertChildPath(sr.getlPath(), 0);
            bi.insertBcsOfSplitting(sr.getN(), 0);

            ObjectOutputStream newOs = new ObjectOutputStream(new FileOutputStream(bi.getPath()));
            newOs.writeObject(bi);
            newOs.close();
        }
    }

    /*
     * Busca los Nodo's que tengan el atributo que se le pasa como parámetro.
     * @return Todas los matches.
     */
    public List<Nodo> search(int value) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rootPath));
        BNode root = (BNode) ois.readObject();
        ois.close();
        return root.search(value);
    }

    /*
     * Imprime en consola los nodos del árbol, ordenados de menor a mayor según el criterio del árbol.
     */
    public void printBT() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rootPath));
        BNode root = (BNode) ois.readObject();
        ois.close();
        root.printBT();
    }

    /*---------------Getter---------------*/

    public String getRootPath() {
        return rootPath;
    }

    /*---------------Setter---------------*/

    private void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

}
