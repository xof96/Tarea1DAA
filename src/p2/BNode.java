package p2;

import p1.Nodo;

import java.util.List;

public interface BNode {

    BTree insert(BTree t, Nodo n);
    void split(BTree t, Nodo n, BNode l, BNode r);
    List<Nodo> search(Nodo n);

    void setFather(BInner bInner);
}
