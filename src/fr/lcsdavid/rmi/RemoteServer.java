package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    Catalogue catalogue() throws RemoteException;
}
