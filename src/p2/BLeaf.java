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
    public void insert(BTree t, Nodo n) {
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
                break;
            }
        }
        if (this.currK > this.kLimit) {
            int middleN = this.currK / 2;
            List<Nodo> leftKeys = new ArrayList<>();
            BLeaf lLeaf = new BLeaf(this.kLimit, this.orderCriteria);
            Nodo med = this.keys.remove(middleN);
            this.currK--;
            for (int i = 0; i < middleN; i++) {
                leftKeys.add(this.keys.remove(0));
                this.currK--;
            }
            lLeaf.setKeys(leftKeys);
            lLeaf.setCurrK(middleN);
            this.split(t, med, lLeaf, this);
        }
    }

    @Override
    public void split(BTree t, Nodo n, BNode l, BNode r) {
        if (this.father == null) {
            this.setFather(new BInner(this.kLimit, this.orderCriteria));
            this.father.insertChild(r, 0);
            t.setRoot(this.father);
        }
        int index = this.father.indexToInsert(n);
        this.father.insertChild(l, index);
        this.father.insertBcsOfSplitting(t, n, index);
    }

    @Override
    public List<Nodo> search(int value) {
        List<Nodo> res = new ArrayList<>();
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

    @Override
    public void printBT() {
//        System.out.println(this);
        for (Nodo n: this.keys) {
            System.out.println(n);
        }
    }

    public String toString() {
        if (this.currK == 0)
            return "BLeaf with no nodes";
        return "BLeaf\n\tfrom: " + this.keys.get(0) + "\n\tto: " + this.keys.get(this.currK - 1);
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

    private void setKeys(List<Nodo> keys) {
        this.keys = keys;
    }

    public void setkLimit(int kLimit) {
        this.kLimit = kLimit;
    }

    public void setOrderCriteria(String orderCriteria) {
        this.orderCriteria = orderCriteria;
    }

    private void setCurrK(int currK) {
        this.currK = currK;
    }

}
