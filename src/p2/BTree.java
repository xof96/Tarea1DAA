package p2;

import p1.Nodo;

import java.util.List;

public class BTree {

    private BNode root;

    BTree(int b, String criteria) {
        this.root = new BLeaf(b, criteria);
    }

    public BNode getRoot() {
        return root;
    }

    public void setRoot(BNode root) {
        this.root = root;
    }

    void insert(Nodo n) {
        this.root.insert(this, n);
    }

    List<Nodo> search(int value) {
        return this.root.search(value);
    }
}
