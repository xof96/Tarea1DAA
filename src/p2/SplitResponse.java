package p2;

import p1.Nodo;

public class SplitResponse {
    private Nodo n;
    private String lPath;
    private String rPath;
    private String fPath;

    SplitResponse(Nodo n, String lPath, String rPath, String fPath) {
        this.n = n;
        this.lPath = lPath;
        this.rPath = rPath;
        this.fPath = fPath;
    }

    /*---------------Getters---------------*/

    Nodo getN() {
        return n;
    }

    String getlPath() {
        return lPath;
    }

    String getrPath() {
        return rPath;
    }

    String getfPath() {
        return fPath;
    }

    /*---------------Setters---------------*/

    public void setN(Nodo n) {
        this.n = n;
    }

    public void setlPath(String lPath) {
        this.lPath = lPath;
    }

    public void setrPath(String rPath) {
        this.rPath = rPath;
    }

    public void setfPath(String fPath) {
        this.fPath = fPath;
    }
}
