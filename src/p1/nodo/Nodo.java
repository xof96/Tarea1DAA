package p1.nodo;

import java.io.Serializable;
import java.util.Hashtable;

public abstract class Nodo implements Serializable {

    Hashtable<String, Integer> attr;

    public Nodo() {
        this.attr = new Hashtable<>();
    }

    public Hashtable<String, Integer> getAttr() {
        return this.attr;
    }

}
