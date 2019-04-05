package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.Commande;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Map<Article, Integer> panier = new HashMap<>();

    public Panier() {
        super();
    }

    public void ajouterArticle(Article article) {
        panier.put(article, 0);
    }

    public void modifierQuantitéArticle(Article article, int nouvelleQuantité) {
        if (nouvelleQuantité < 1)
            throw new IllegalArgumentException();
        if (!panier.containsKey(article))
            throw new IllegalArgumentException();
        panier.put(article, nouvelleQuantité);
    }

    public void retiterArticle(Article article) {
        if (!panier.containsKey(article))
            throw new IllegalArgumentException();
        panier.remove(article);
    }

    public double calculMontantPanier() {
        double montant = 0;
        for (Map.Entry<Article, Integer> articleEntry : panier.entrySet())
            montant += articleEntry.getKey().getPrix() * articleEntry.getValue();
        return montant;
    }

    public Commande toCommande(long client) {
        return new Commande(client, panier);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Panier panier = new Panier();
        return super.clone();
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Article, Integer> articleEntry : panier.entrySet())
            s += '[' + articleEntry.getValue() + "] " + articleEntry.getKey();
        return s;
    }
}
