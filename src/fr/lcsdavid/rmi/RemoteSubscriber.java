package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface RemoteSubscriber extends Remote {

    default void subscribe(RemotePublisher remotePublisher) throws RemoteException {
        remotePublisher.addRemoteSubscriber((RemoteSubscriber) UnicastRemoteObject.exportObject(this, 0));
    }

    default void unsubscribe(RemotePublisher remotePublisher) throws RemoteException {
        remotePublisher.removeRemoteSubscriber(this);
        if (!UnicastRemoteObject.unexportObject(this, true))
            throw new RemoteException();
    }

    default void notify(String message) throws RemoteException {
        System.out.println(message);
    }
}
