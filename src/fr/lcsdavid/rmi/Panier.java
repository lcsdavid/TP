package fr.lcsdavid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Panier extends Remote, Clearable {

    public void ajouterArticle(Article article) throws RemoteException;

    public void modifierQuantitéArticle(Article article, int nouvelleQuantité) throws RemoteException;

    public void retiterArticle(Article article) throws RemoteException;

    public double calculMontantPanier() throws RemoteException;

    public Commande toCommande(long client) throws RemoteException;




}
