package p2;

import p1.Nodo;

import java.util.ArrayList;
import java.util.List;

public class Btree {

    private int nLimit;
    private int cLimit;
    private List<Nodo> keys;
    private String orderCriteria;
    private List<Btree> children;

    Btree(int b, String criteria) {
        this.nLimit = b / 2;
        this.cLimit = b / 2 + 1;
        this.keys = new ArrayList<>();
        this.orderCriteria = criteria;
        this.children = new ArrayList<>();
    }

    public int getNLimit() {
        return this.nLimit;
    }

    public int getCLimit() {
        return this.cLimit;
    }

    public List<Nodo> getKey() {
        return this.keys;
    }

    public String getOrderCriteria() {
        return this.orderCriteria;
    }

    public List<Btree> getChildren() {
        return this.children;
    }

    public void insert(Nodo n) {
        if (this.children.isEmpty()) {
            if (this.keys.isEmpty()) {
                this.keys.add(n);
            }
            else {
                int i;
                for (i = 0; i <= this.keys.size(); i++) {
                    int v = n.getAttr().get(this.orderCriteria);
                    if(i < this.keys.size() && v <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                        this.keys.add(i, n);
                    }
                    else {
                        this.keys.add(n);
                    }
                }
                if (i > this.nLimit) {
                    this.reshape();
                }
            }
        }
        else {

        }
    }

    List<Nodo> searchEqual(String key, int value) {
        List<Nodo> nlist = new ArrayList<>();
        if (this.children == null) {
            for (int i = 0; i < children.length; i++) {
                Nodo ni = children.[i].getKey();
                if (ni.getAttr().get(key) == value) {
                    nlist.add(ni);
                }
            }
        }
        else {
            return nlist;
        }
        return nlist;
    }

}
