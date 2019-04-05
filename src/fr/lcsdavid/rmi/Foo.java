package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Foo extends Remote {

    public String sayFoo(String name) throws RemoteException;
}
