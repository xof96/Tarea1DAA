package p1.test;

import p1.nodo.Database;
import p1.nodo.Producto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class MergeTester {

    public static void main(String[] args) {
        Database d=new Database("./producto-3.txt");
        d.ordenar("precio");
    }
}

