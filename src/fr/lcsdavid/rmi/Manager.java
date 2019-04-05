package fr.lcsdavid.rmi;

import fr.lcsdavid.rmi.server.Catalogue;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Remote {
    private Catalogue catalogue = new Catalogue();
    private List<Commande> commandes = new ArrayList<>();

    public Manager() {
        super();
    }

    public Catalogue catalogue() {
        return catalogue;
    }

    public void envoyéCommande(Commande commande) {
        commandes.add(commande);
    }

    public Commande recevoirCommande() {
        return commandes.remove(0);
    }
}
