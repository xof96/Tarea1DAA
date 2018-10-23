package p1;

public class Consumidor extends Nodo {

    public Consumidor(int id, int rut, int ptsAc) {
        super();
        this.attr.put("id", id);
        this.attr.put("rut", rut);
        this.attr.put("ptsAc", ptsAc);
    }

    @Override
    public String toString() {
        //hay que tratar de quitarle el espacio despues de las comas
        return this.attr.get("id") + "," + this.attr.get("rut") + "," +
                this.attr.get("ptsAc");
    }

}
