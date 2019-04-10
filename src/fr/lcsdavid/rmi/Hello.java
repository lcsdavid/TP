package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    /* TP du matin */
    String sayHello(String name) throws RemoteException;

    Data data(String name) throws RemoteException;

    Foo foo() throws RemoteException;
}
