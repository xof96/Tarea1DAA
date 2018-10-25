package p1;

import java.util.Hashtable;

public abstract class Nodo {

    protected Hashtable<String, Integer> attr;

    public Nodo() {
        this.attr = new Hashtable<String, Integer>();
    }

    public Hashtable<String, Integer> getAttr() {
        return this.attr;
    }

}
