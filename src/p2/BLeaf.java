package p2;

import p1.Nodo;

import java.util.ArrayList;
import java.util.List;

public class BLeaf implements BNode {
    private BInner father;
    private List<Nodo> keys;
    private int kLimit;
    private String orderCriteria;
    private int currK;

    BLeaf(int b, String criteria) {
        this.keys = new ArrayList<>();
        this.kLimit = b;
        this.orderCriteria = criteria;
        this.currK = 0;
    }

    @Override
    public BTree insert(BTree t, Nodo n) {
        int value = n.getAttr().get(this.orderCriteria);
        for (int i = 0; i <= this.currK; i++) {
            if (i < this.currK) {
                if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                    this.keys.add(i, n);
                    this.currK++;
                    break;
                }
            } else {
                this.keys.add(n);
                this.currK++;
            }
        }
        if (this.currK > this.kLimit) {
            int middleN = this.currK / 2;
            List<Nodo> leftKeys = this.keys.subList(0, middleN);
            BLeaf lLeaf = new BLeaf(this.kLimit, this.orderCriteria);
            lLeaf.setKeys(leftKeys);
            Nodo med = this.keys.get(middleN);
            for (int i = 0; i < middleN; i++)
                this.keys.remove(0);
            this.split(t, med, lLeaf, this);
        }
        return t;
    }

    @Override
    public void split(BTree t, Nodo n, BNode l, BNode r) {
        if (this.father == null) {
            this.setFather(new BInner(this.kLimit, this.orderCriteria));
            this.father.insertChildren(r, 0);
        }
        int index = this.father.insertBcsOfSplitting(t, n);
        this.father.insertChildren(l, index);
        t.setRoot(this.father);
    }

    @Override
    public List<Nodo> search(Nodo n) {
        List<Nodo> res = new ArrayList<>();
        int value = n.getAttr().get(this.orderCriteria);
        for (Nodo buf: this.keys) {
            int bufV = buf.getAttr().get(this.orderCriteria);
            if (value == bufV) {
                res.add(buf);
            } else if (value < bufV) {
                break;
            }
        }
        return res;
    }

    /*----------------------Getters----------------------*/

    public BInner getFather() {
        return father;
    }

    public List<Nodo> getKeys() {
        return keys;
    }

    public int getkLimit() {
        return kLimit;
    }

    public String getOrderCriteria() {
        return orderCriteria;
    }

    public int getCurrK() {
        return currK;
    }

    /*----------------------Setters----------------------*/

    public void setFather(BInner father) {
        this.father = father;
    }

    public void setKeys(List<Nodo> keys) {
        this.keys = keys;
    }

    public void setkLimit(int kLimit) {
        this.kLimit = kLimit;
    }

    public void setOrderCriteria(String orderCriteria) {
        this.orderCriteria = orderCriteria;
    }

    public void setCurrK(int currK) {
        this.currK = currK;
    }

}
