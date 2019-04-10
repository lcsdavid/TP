package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Clearable;
import fr.lcsdavid.rmi.Pool;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.Supplier;

public class PoolImpl<T extends Remote> implements Pool {

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
    public T getInstance() {
        if (disponible.isEmpty() || tailleMax == 0)
            return null;
        T obj = disponible.remove(0);
        System.out.println((obj == null) + " " + obj.toString());
        utilisé.add(obj);
        //obj.clear();
        try {
            return (T) UnicastRemoteObject.exportObject(obj, 0);
        } catch (RemoteException e) {
            System.out.println("echec de l'envoie d'un panier");
            e.printStackTrace();
        }

        return null;
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
    public boolean restitution(Remote objet) throws RemoteException {
        return false;
    }


}
