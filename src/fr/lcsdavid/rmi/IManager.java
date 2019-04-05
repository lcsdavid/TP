package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote {

    public Catalogue catalogue() throws RemoteException;

    public void envoyéCommande(Commande commande) throws RemoteException;

    public Commande recevoirCommande() throws RemoteException;
}
