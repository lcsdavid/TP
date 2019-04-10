package fr.lcsdavid.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Manager implements IManager {
    private Catalogue catalogue = new Catalogue();
    private List<Commande> commandes = new ArrayList<>();

    @Deprecated
    public Manager() {
        super();
    }

    @Deprecated
    public Catalogue catalogue() throws RemoteException {
        return catalogue;
    }

    @Deprecated
    public void envoyéCommande(Commande commande) throws RemoteException {
        commandes.add(commande);
    }

    @Deprecated
    public Commande recevoirCommande() throws RemoteException {
        return commandes.remove(0);
    }
}
