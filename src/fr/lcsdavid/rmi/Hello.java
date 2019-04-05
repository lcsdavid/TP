package fr.lcsdavid.rmi;

import fr.lcsdavid.rmi.server.Catalogue;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    /* TP du matin */
    public String sayHello(String name) throws RemoteException;

    public Data data(String name) throws RemoteException;

    public Foo foo() throws RemoteException;


    /* TP après-midi */
    public Catalogue catalogue();

}
