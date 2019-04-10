package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

@Deprecated
public interface IManager extends Remote {

    @Deprecated
    Catalogue catalogue() throws RemoteException;

    @Deprecated
    void envoyéCommande(Commande commande) throws RemoteException;

    @Deprecated
    Commande recevoirCommande() throws RemoteException;
}
