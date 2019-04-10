package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool<T extends Remote & Clearable> extends Remote {

    public T getInstance() throws RemoteException;

    public int getTaille() throws RemoteException;

    public int nbObjDispo() throws RemoteException;

    public boolean restitution(T objet) throws RemoteException;
}
