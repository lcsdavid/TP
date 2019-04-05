package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Remote {
    private Catalogue catalogue = new Catalogue();
    private List<Commande> commandes = new ArrayList<>();

    public Manager() {
        super();
    }

    public Catalogue catalogue() throws RemoteException {
        return catalogue;
    }

    public void envoyéCommande(Commande commande) throws RemoteException {
        commandes.add(commande);
    }

    public Commande recevoirCommande() throws RemoteException {
        return commandes.remove(0);
    }
}
