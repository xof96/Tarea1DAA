package p1;

public class Producto extends Nodo {

    public Producto (int id, int precio, int ptsNec, int ptsRec) {
        super();
        this.attr.put("id", String.valueOf(id));
        this.attr.put("precio", String.valueOf(precio));
        this.attr.put("ptsNec", String.valueOf(ptsNec));
        this.attr.put("ptsRec", String.valueOf(ptsRec));
    }

}