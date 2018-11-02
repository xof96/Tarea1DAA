package SerialBTree;

import p1.Nodo;

import java.util.List;

public class BTree {

    private BNode root;

    public BTree(int b, String criteria) {
        this.root = new BLeaf(b, criteria);
    }

    public void insert(Nodo n) {
        this.root.insert(this, n);
    }

    public List<Nodo> search(int value) {
        return this.root.search(value);
    }

    public void printBT() {
        this.root.printBT();
    }

    /*---------------Getter---------------*/

    public BNode getRoot() {
        return root;
    }

    /*---------------Setter---------------*/

    void setRoot(BNode root) {
        this.root = root;
    }

}
