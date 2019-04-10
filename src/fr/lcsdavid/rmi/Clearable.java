package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Clearable extends Remote {

    void clear() throws RemoteException;
}
