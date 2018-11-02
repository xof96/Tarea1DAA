package SerialBTree;

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
    public void insert(BTree t, Nodo n) {

        int value = n.getAttr().get(this.orderCriteria);
        for (int i = 0; i <= this.currK; i++) {
            if (i < this.currK) {
                if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                    this.children.get(i).insert(t, n);
                    break;
                }
            } else {
                this.children.get(i).insert(t, n);
                break;
            }
        }
    }

    int indexToInsert(Nodo n) {
        int value = n.getAttr().get(this.orderCriteria);
        if (this.currK == 0)
            return 0;
        for (int i = 0; i < this.currK; i++) {
            if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                return i;
            }
        }
        return this.currK;
    }

    void insertBcsOfSplitting(BTree t, Nodo n, int index) {
        if (index < currK) {
            this.keys.add(index, n);
            this.currK++;
        } else {
            this.keys.add(n);
            this.currK++;
        }

        if (this.currK > this.kLimit) {
            int middleN = this.currK / 2;
            List<Nodo> leftKeys = new ArrayList<>();
            List<BNode> leftChildren = new ArrayList<>();
            BInner lInner = new BInner(this.kLimit + this.cLimit, this.orderCriteria);
            Nodo med = this.keys.remove(middleN);
            currK--;
            for (int i = 0; i < middleN; i++) {
                leftKeys.add(this.keys.remove(0));
                this.currK--;
                lInner.insertChild(this.children.remove(0), lInner.getCurrC());
//                leftChildren.add(this.children.remove(0));
                this.currC--;
            }
            lInner.insertChild(this.children.remove(0), lInner.getCurrC());
//            leftChildren.add(this.children.remove(0));
            this.currC--;
            lInner.setKeys(leftKeys);
            lInner.setCurrK(middleN);
//            lInner.setChildren(leftChildren);
//            lInner.setCurrC(middleN + 1);
            this.split(t, med, lInner, this);
        }
    }

    void insertChild(BNode c, int index) {
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
        if (this.father == null) {
            this.setFather(new BInner(this.kLimit + this.cLimit, this.orderCriteria));
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
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (value == bufV) {
                res.addAll(this.children.get(i).search(value));
                res.add(this.keys.get(i));
            }
            if (value < bufV) {
                res.addAll(this.children.get(i).search(value));
                break;
            }
        }
        return res;
    }

    @Override
    public void printBT() {
//        System.out.println(this);
        for (int i = 0; i <= this.currK; i++) {
            this.children.get(i).printBT();
            if (i < this.currK)
                System.out.println(this.keys.get(i));
        }
    }

    public String toString() {
        if (this.currK == 0)
            return "BInner with no nodes";
        return "BInner\n\tfrom: " + this.keys.get(0) + "\n\tto: " + this.keys.get(this.currK - 1);
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

    private int getCurrC() {
        return this.currC;
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

    private void setCurrK(int currK) {
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
