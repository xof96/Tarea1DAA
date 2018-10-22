package p1;

public class Consumidor extends Nodo {

    public Consumidor (int id, String rut, int ptsAc) {
        super();
        this.attr.put("id", String.valueOf(id));
        this.attr.put("rut", rut);
        this.attr.put("ptsAc", String.valueOf(ptsAc));
    }

    @Override
    public String toString() {
        return this.attr.get("id") + ", " + this.attr.get("rut") + ", " +
                this.attr.get("ptsAc");
    }

}
