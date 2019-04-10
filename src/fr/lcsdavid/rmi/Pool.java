package fr.lcsdavid.rmi;

import fr.lcsdavid.rmi.Clearable;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteStub;
import java.util.function.Supplier;

public interface Pool<T extends Remote & Clearable> extends Remote {

    public RemoteStub getInstance() throws RemoteException;

    public int getTaille() throws RemoteException;

    public int nbObjDispo() throws RemoteException;

    public Boolean restitution(T objet) throws RemoteException;


}
