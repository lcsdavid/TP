package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Clearable extends Remote {

    public void clear() throws RemoteException;
}
