package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Panier extends Remote, Clearable {
    void ajouterArticle(Article article, int quantité) throws RemoteException;

    void modifierQuantitéArticle(Article article, int nouvelleQuantité) throws RemoteException;

    void retirerArticle(Article article) throws RemoteException;

    double montantPanier() throws RemoteException;

    @Deprecated
    Commande toCommande(long client) throws RemoteException;

    default String remoteToString() throws RemoteException {
        return toString();
    }
}
