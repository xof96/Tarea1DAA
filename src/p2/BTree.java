package p2;

import p1.Nodo;

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
}
