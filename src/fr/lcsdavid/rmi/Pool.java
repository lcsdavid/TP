package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool<T extends Remote & Clearable> extends RemotePublisher {
    T instance() throws RemoteException;

    int taille() throws RemoteException;

    int nombreDisponible() throws RemoteException;

    void restitution(T objet) throws RemoteException;
}
