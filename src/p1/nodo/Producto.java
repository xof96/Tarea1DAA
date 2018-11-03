package p1.nodo;

public class Producto extends Nodo {

    public Producto(int id, int precio, int ptsNec, int ptsRec) {
        super();
        this.attr.put("id", id);
        this.attr.put("precio", precio);
        this.attr.put("ptsNec", ptsNec);
        this.attr.put("ptsRec", ptsRec);
    }

    @Override
    public String toString() {
        return this.attr.get("id") + "," + this.attr.get("precio") + "," +
                this.attr.get("ptsNec") + "," + this.attr.get("ptsRec");
    }
}