package p2;

import p1.Nodo;

import java.util.ArrayList;
import java.util.List;

public class BInner implements BNode {

    private BInner father;
    private List<Nodo> keys;
    private int kLimit;
    private int currK;
    private List<BNode> children;
    private int cLimit;
    private String orderCriteria;
    private int currC;

    BInner(int b, String criteria) {
        this.keys = new ArrayList<>();
        this.kLimit = b / 2;
        this.currK = 0;
        this.children = new ArrayList<>();
        this.cLimit = b / 2 + 1;
        this.currC = 0;
        this.orderCriteria = criteria;
    }

    @Override
    public BTree insert(BTree t, Nodo n) {

        int value = n.getAttr().get(this.orderCriteria);
        for (int i = 0; i <= this.currK; i++) {
            if (i < this.currK) {
                if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                    this.children.get(i).insert(t, n);
                    break;
                }
            } else {
                this.children.get(i).insert(t, n);
            }
        }

        return t;
    }

    public int insertBcsOfSplitting(BTree t, Nodo n) {
        int value = n.getAttr().get(this.orderCriteria);
        int index = -1;
        for (int i = 0; i <= this.currK; i++) {
            if (i < this.currK) {
                if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                    this.keys.add(i, n);
                    index = i;
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
        return index;
    }

    public void insertChildren(BNode c, int index) {
        c.setFather(this);
        if (index >= this.currC) {
            this.children.add(c);
            this.currC++;
        } else {
            this.children.add(index, c);
            this.currC++;
        }
    }

    @Override
    public void split(BTree t, Nodo n, BNode l, BNode r) {

    }

    @Override
    public List<Nodo> search(Nodo n) {
        List<Nodo> res = new ArrayList<>();
        int value = n.getAttr().get(this.orderCriteria);
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (value == bufV) {
                res.addAll(this.children.get(i).search(n));
                res.add(this.keys.get(i));
            }
            if (value < bufV) {
                res.addAll(this.children.get(i).search(n));
                break;
            }
        }
        return res;
    }


    /*----------------------Getters----------------------*/

    public BInner getFather() {
        return this.father;
    }

    public List<Nodo> getKeys() {
        return this.keys;
    }

    public int getKLimit() {
        return this.kLimit;
    }

    public int getCurrK() {
        return this.currK;
    }

    public List<BNode> getChildren() {
        return this.children;
    }

    public int getCLimit() {
        return this.cLimit;
    }

    public String getOrderCriteria() {
        return this.orderCriteria;
    }

    public int getCurrC() {
        return this.currC;
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

    public void setCurrK(int currK) {
        this.currK = currK;
    }

    public void setChildren(List<BNode> children) {
        this.children = children;
    }

    public void setCLimit(int cLimit) {
        this.cLimit = cLimit;
    }

    public void setOrderCriteria(String orderCriteria) {
        this.orderCriteria = orderCriteria;
    }

    public void setCurrC(int currC) {
        this.currC = currC;
    }
}
