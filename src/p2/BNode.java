package p2;

import p1.Nodo;

import java.util.List;

public interface BNode {

    /*
     * Inserta el Nodo n en el BTree t según el criterio por el que esté
     * creado el BTree.
     */
    BTree insert(BTree t, Nodo n);

    void split(BTree t, Nodo n, BNode l, BNode r);

    List<Nodo> search(Nodo n);

    void setFather(BInner bInner);
}
