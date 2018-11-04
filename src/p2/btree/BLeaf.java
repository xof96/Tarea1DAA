package p2.btree;

import p1.nodo.Nodo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BLeaf implements BNode, Serializable {
    private String path;
    private List<Nodo> keys;
    private int kLimit;
    private String orderCriteria;
    private int currK;

    BLeaf(int b, String criteria, String path) {
        this.path = path;
        this.keys = new ArrayList<>();
        this.kLimit = b;
        this.orderCriteria = criteria;
        this.currK = 0;
    }

    @Override
    public SplitResponse insert(BTree t, Nodo n) throws IOException {
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
            String lPath = String.format("%sl", this.path);
            BLeaf lLeaf = new BLeaf(this.kLimit, this.orderCriteria, lPath);
            Nodo med = this.keys.remove(middleN);
            this.currK--;
            for (int i = 0; i < middleN; i++) {
                leftKeys.add(this.keys.remove(0));
                this.currK--;
            }
            lLeaf.setKeys(leftKeys);
            lLeaf.setCurrK(middleN);
            return this.split(med, lLeaf);
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
    public List<Nodo> searchByRange(int ini, int fin, int incI, int incF) {
        if (ini == -1) return this.searchLesser(fin, incF);
        if (fin == -1) return this.searchBigger(ini, incI);

        List<Nodo> res = new ArrayList<>();
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (incI == 1 && incF == 1) {
                if (ini <= bufV && bufV <= fin) res.add(this.keys.get(i));
                if (bufV > fin) break;
            } else if (incI == 1 && incF == 0) {
                if (ini <= bufV && bufV < fin) res.add(this.keys.get(i));
                if (bufV >= fin) break;
            } else if (incI == 0 && incF == 1) {
                if (ini < bufV && bufV <= fin) res.add(this.keys.get(i));
                if (bufV > fin) break;
            } else {
                if (ini < bufV && bufV < fin) res.add(this.keys.get(i));
                if (bufV >= fin) break;
            }
        }
        return res;
    }

    @Override
    public List<Nodo> searchLesser(int value, int incF) {
        List<Nodo> res = new ArrayList<>();
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (incF == 1) {
                if (bufV <= value) res.add(this.keys.get(i));
                if (bufV > value) break;
            } else {
                if (bufV < value) res.add(this.keys.get(i));
                if (bufV >= value) break;
            }
        }
        return res;
    }

    @Override
    public List<Nodo> searchBigger(int value, int incI) {
        List<Nodo> res = new ArrayList<>();
        for (int i = 0; i < this.currK; i++) {
            int bufV = this.keys.get(i).getAttr().get(this.orderCriteria);
            if (incI == 1) {
                if (bufV >= value) res.add(this.keys.get(i));
            } else {
                if (bufV > value) res.add(this.keys.get(i));
            }
        }
        return res;
    }

    @Override
    public void printBT() {
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

    public String getPath() {
        return path;
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

    private void setPath(String path) {
        this.path = path;
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
