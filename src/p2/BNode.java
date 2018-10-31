package p2;

import p1.Nodo;

import java.util.List;

public interface BNode {

    /*
     * Inserta el Nodo n en el BTree t según el criterio por el que esté
     * creado el BTree.
     */
    void insert(BTree t, Nodo n);

    /*
     * Separa el nodo que llama este método en dos
     */
    void split(BTree t, Nodo n, BNode l, BNode r);

    /*
     * Retorna una List<Nodo> con la los nodos que cumplen la condición.
     */
    List<Nodo> search(int value);

    /*
     * Prints the node and its children.
     */
    void printBT();

    /*
     * Setter for BNode's father.
     */
    void setFather(BInner bInner);
}
