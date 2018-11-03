package p2;

import p1.Nodo;

import java.io.*;
import java.util.List;

public class BTree {
    private int b;
    private String criteria;
    private String rootPath;

    public BTree(int b, String criteria, String path) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(new BLeaf(b, criteria, path));
        oos.close();
        this.b = b;
        this.criteria = criteria;
        this.rootPath = path;
    }

    public void insert(Nodo n) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rootPath));
        BNode root = (BNode) ois.readObject();
        ois.close();
        SplitResponse sr = root.insert(this, n);

        if (sr != null) {
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

    public List<Nodo> search(int value) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rootPath));
        BNode root = (BNode) ois.readObject();
        ois.close();
        return root.search(value);
    }

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
