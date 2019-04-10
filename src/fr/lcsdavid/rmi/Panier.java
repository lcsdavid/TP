package fr.lcsdavid.rmi;

import java.rmi.Remote;

public interface Panier extends Clearable, Remote {

    public void ajouterArticle(Article article);

    public void modifierQuantitéArticle(Article article, int nouvelleQuantité);

    public void retiterArticle(Article article);

    public double calculMontantPanier();

    public Commande toCommande(long client);




}
