package p1.test;

import p1.nodo.Database;

public class MergeTester {

    public static void main(String[] args){
        Database d=new Database("./producto-3.txt");
        d.ordenar("precio");
    }
}

