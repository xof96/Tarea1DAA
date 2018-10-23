package p1;

public class Producto extends Nodo {

    public Producto(String id, String precio, String ptsNec, String ptsRec) {
        super();
        this.attr.put("id", id);
        this.attr.put("precio", precio);
        this.attr.put("ptsNec", ptsNec);
        this.attr.put("ptsRec", ptsRec);
    }

    @Override
    public String toString() {
        //hay que tratar de quitarle el espacio despues de la coma
        return this.attr.get("id") + "," + this.attr.get("precio") + "," +
                this.attr.get("ptsNec") + "," + this.attr.get("ptsRec");
    }
}