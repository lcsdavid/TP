package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Pool;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.function.Supplier;

public class PoolImpl<T extends Remote, Clearable> implements Pool {

    private Supplier<T> supplier;

    private ArrayList<T> disponible;

    private ArrayList<T> utilisé;

    private int tailleMax;

    public PoolImpl(Supplier<T> supplier){
            this.supplier = supplier;
            tailleMax = 100;
            disponible = new ArrayList<>();
            utilisé = new ArrayList<>();
            for(int  i =0; i < tailleMax; i++){
                disponible.add(supplier.get());
            }
    }

    public PoolImpl(Supplier<T> supplier, int taille){
        this.supplier = supplier;
        tailleMax = taille;
        disponible = new ArrayList<>();
        utilisé = new ArrayList<>();
        for(int  i =0; i < tailleMax; i++){
            disponible.add(supplier.get());
        }
    }


    @Override
    public Remote getInstance() {
        if(tailleMax == 0 || disponible.size()==0)
            return null;
        T obj = disponible.remove(0);
        utilisé.add(obj);
        return obj;

    }

    @Override
    public int getTaille() {
        return tailleMax;
    }

    @Override
    public int nbObjDispo() {
        return disponible.size();
    }

    @Override
    public Boolean restitution(Remote objet) {
        return null;
    }


}
