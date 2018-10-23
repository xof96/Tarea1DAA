package p1;

public class Producto extends Nodo {

    public Producto(int id, int precio, int ptsNec, int ptsRec) {
        super();
        this.attr.put("id", String.valueOf(id));
        this.attr.put("precio", String.valueOf(precio));
        this.attr.put("ptsNec", String.valueOf(ptsNec));
        this.attr.put("ptsRec", String.valueOf(ptsRec));
    }

    @Override
    public String toString() {
        //hay que tratar de quitarle el espacio despues de la coma
        return this.attr.get("id") + ", " + this.attr.get("precio") + ", " +
                this.attr.get("ptsNec") + ", " + this.attr.get("ptsRec");
    }
}