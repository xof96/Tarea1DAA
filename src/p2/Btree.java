package p2;

import p1.Nodo;

import java.util.ArrayList;
import java.util.List;

public class Btree {

    private int b;
    private Nodo key;
    private String orderCriteria;
    private List<Btree> children;

    Btree(int b, String criteria) {
        this.b = b;
        this.orderCriteria = criteria;
        this.children = new ArrayList<>();
    }

    public int getB() {
        return this.b;
    }

    public Nodo getKey() {
        return this.key;
    }

    public String getOrderCriteria() {
        return this.orderCriteria;
    }

    public List<Btree> getChildren() {
        return this.children;
    }


    public void insert(Nodo n) {
        if (this.children == null) {

        }
    }

    List<Nodo> searchEqual(String key, int value) {
        List<Nodo> nlist = new ArrayList<>();
        if (this.children == null) {
            for (int i = 0; i < children.size(); i++) {
                Nodo ni = children.get(i).getKey();
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
