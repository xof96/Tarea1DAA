package p2.btree;

import p1.nodo.Nodo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface BNode {

    /*
     * Inserta el Nodo n en el BTree t según el criterio por el que esté
     * creado el BTree.
     */
    SplitResponse insert(BTree t, Nodo n) throws IOException, ClassNotFoundException;

    /*
     * Separa el nodo que llama este método en dos l es el izquierdo y el derecho es this.
     */
    SplitResponse split(Nodo n, BNode l) throws IOException;

    /*
     * Retorna una List<Nodo> con la los nodos que cumplen la condición.
     */
    List<Nodo> search(int value) throws IOException, ClassNotFoundException;

    /*
     * Retorna una List<Nodo> con la los nodos que cumplen la condición.
     */
    List<Nodo> searchLesser(int ini, int fin, int incI, int incF) throws IOException, ClassNotFoundException;

    /*
     * Prints the node and its children.
     */
    void printBT() throws IOException, ClassNotFoundException;

    /*
     * Returns the path of the node.
     */
    String getPath();

    List<Nodo> searchLesser(int value, int incF) throws IOException, ClassNotFoundException;
    List<Nodo> searchBigger(int value, int incF);
}
