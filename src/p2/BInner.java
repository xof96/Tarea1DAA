package p2;

import p1.Nodo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BInner implements BNode, Serializable {

    private String path;
    private String fatherPath;
    private List<Nodo> keys;
    private int kLimit;
    private int currK;
    private List<String> childrenPath;
    private int cLimit;
    private String orderCriteria;
    private int currC;

    BInner(int b, String criteria, String path) {
        this.path = path;
        this.keys = new ArrayList<>();
        this.kLimit = b / 2;
        this.currK = 0;
        this.childrenPath = new ArrayList<>();
        this.cLimit = b / 2 + 1;
        this.currC = 0;
        this.orderCriteria = criteria;
    }

    @Override
    public SplitResponse insert(BTree t, Nodo n) throws IOException, ClassNotFoundException {

        int value = n.getAttr().get(this.orderCriteria);
        ObjectInputStream ois = null;
        SplitResponse sr = null;
        for (int i = 0; i <= this.currK; i++) {
            if (i < this.currK) {
                if (value <= this.keys.get(i).getAttr().get(this.orderCriteria)) {
                    ois = new ObjectInputStream(new FileInputStream(this.childrenPath.get(i)));
                    BNode child = (BNode) ois.readObject();
                    sr = child.insert(t, n);
                    ObjectOutputStream cos = new ObjectOutputStream(new FileOutputStream(child.getPath()));
                    cos.writeObject(child);
                    cos.close();
                    break;
                }
            } else {
                ois = new ObjectInputStream(new FileInputStream(this.childrenPath.get(i)));
                BNode child = (BNode) ois.readObject();
                sr = child.insert(t, n);
                ObjectOutputStream cos = new ObjectOutputStream(new FileOutputStream(child.getPath()));
                cos.writeObject(child);
                cos.close();
                break;
            }
        }
        if (sr != null) {
            int index = indexToInsert(sr.getN());
            this.insertChildPath(sr.getlPath(), index);
            this.childrenPath.remove(index + 1);
            this.insertChildPath(sr.getrPath(), index + 1);
            return this.insertBcsOfSplitting(sr.getN(), index);

        }

        if (ois != null) ois.close();

        return null;
    }

    private int indexToInsert(Nodo n) {
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

    SplitResponse insertBcsOfSplitting(Nodo n, int index) throws IOException {
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
            String lPath = String.format("%sl", this.path);
            BInner lInner = new BInner(this.kLimit + this.cLimit, this.orderCriteria, lPath);
            Nodo med = this.keys.remove(middleN);
            this.currK--;
            for (int i = 0; i < middleN; i++) {
                leftKeys.add(this.keys.remove(0));
                this.currK--;
                lInner.insertChildPath(this.childrenPath.remove(0), lInner.getCurrC());
                this.currC--;
            }
            lInner.insertChildPath(this.childrenPath.remove(0), lInner.getCurrC());
            this.currC--;
            lInner.setKeys(leftKeys);
            lInner.setCurrK(middleN);
            return this.split(med, lInner);
        }

        ObjectOutputStream myos = new ObjectOutputStream(new FileOutputStream(this.path));
        myos.writeObject(this);
        myos.close();

        return null;
    }

    @Override
    public SplitResponse split(Nodo n, BNode l) throws IOException {
        String fPath = String.format("%sf", this.path);

        File f = new File(this.path);
        if(f.delete())
            System.out.printf("%s borrado%n", this.path);

        this.setPath(String.format("%sr", this.path));

        ObjectOutputStream myos = new ObjectOutputStream(new FileOutputStream(this.path));
        myos.writeObject(this);
        myos.close();

        ObjectOutputStream los = new ObjectOutputStream(new FileOutputStream(l.getPath()));
        los.writeObject(l);
        los.close();

        return new SplitResponse(n, l.getPath(), this.path, fPath);
    }

    void insertChildPath(String cPath, int index) {
        if (index >= this.currC) {
            this.childrenPath.add(cPath);
            this.currC++;
        } else {
            this.childrenPath.add(index, cPath);
            this.currC++;
        }
    }

    @Override
    public List<Nodo> search(int value) throws IOException, ClassNotFoundException {
        List<Nodo> res = new ArrayList<>();
        ObjectInputStream ois = null;
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (value == bufV) {
                ois = new ObjectInputStream(new FileInputStream(this.childrenPath.get(i)));
                BNode child = (BNode) ois.readObject();
                res.addAll(child.search(value));
                res.add(this.keys.get(i));
            }
            if (value < bufV) {
                ois = new ObjectInputStream(new FileInputStream(this.childrenPath.get(i)));
                BNode child = (BNode) ois.readObject();
                res.addAll(child.search(value));
                break;
            }
        }

        if (ois != null) {
            ois.close();
        }
        return res;
    }

    @Override
    public void printBT() throws IOException, ClassNotFoundException {
        for (int i = 0; i <= this.currK; i++) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.childrenPath.get(i)));
            BNode child = (BNode) ois.readObject();
            child.printBT();
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

    public String getPath() {
        return path;
    }

    public String getFatherPath() {
        return this.fatherPath;
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

    public List<String> getChildrenPath() {
        return childrenPath;
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

    private void setPath(String path) {
        this.path = path;
    }

    public void setFatherPath(String fatherPath) {
        this.fatherPath = fatherPath;
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

    public void setChildrenPath(List<String> childrenPath) {
        this.childrenPath = childrenPath;
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
