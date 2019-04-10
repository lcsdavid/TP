package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePublisher extends Remote {

    void addRemoteSubscriber(RemoteSubscriber remoteSubscriber) throws RemoteException;

    void removeRemoteSubscriber(RemoteSubscriber remoteSubscriber) throws RemoteException;

    void notify(String message) throws RemoteException;
}
