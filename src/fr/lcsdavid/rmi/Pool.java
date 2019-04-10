package fr.lcsdavid.rmi;

import fr.lcsdavid.rmi.Clearable;

import java.rmi.Remote;
import java.util.function.Supplier;

public interface Pool<T extends Remote, Clearable> extends Remote {

    public T getInstance();

    public int getTaille();

    public int nbObjDispo();

    public Boolean restitution(T objet);


}
