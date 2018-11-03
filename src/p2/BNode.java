package p2;

import p1.Nodo;

import java.io.IOException;
import java.util.List;

public interface BNode {

    /*
     * Inserta el Nodo n en el BTree t según el criterio por el que esté
     * creado el BTree.
     */
    splitResponse insert(BTree t, Nodo n) throws IOException, ClassNotFoundException;

    /*
     * Separa el nodo que llama este método en dos l es el izquierdo y el derecho es this.
     */
    splitResponse split(Nodo n, BNode l) throws IOException;

    /*
     * Retorna una List<Nodo> con la los nodos que cumplen la condición.
     */
    List<Nodo> search(int value) throws IOException, ClassNotFoundException;

    /*
     * Prints the node and its children.
     */
    void printBT() throws IOException, ClassNotFoundException;


    String getPath();
}
