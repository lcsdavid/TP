package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.Commande;
import fr.lcsdavid.rmi.Panier;

import java.util.HashMap;
import java.util.Map;

public class PanierImpl implements Panier {
    private Map<Article, Integer> articles = new HashMap<>();

    public PanierImpl() {
        super();
    }

    public void ajouterArticle(Article article) {
        articles.put(article, 0);
    }

    public void modifierQuantitéArticle(Article article, int nouvelleQuantité) {
        if (nouvelleQuantité < 1)
            throw new IllegalArgumentException();
        if (!articles.containsKey(article))
            throw new IllegalArgumentException();
        articles.put(article, nouvelleQuantité);
    }

    public void retiterArticle(Article article) {
        if (!articles.containsKey(article))
            throw new IllegalArgumentException();
        articles.remove(article);
    }

    public double calculMontantPanier() {
        double montant = 0;
        for (Map.Entry<Article, Integer> articleEntry : articles.entrySet())
            montant += articleEntry.getKey().getPrix() * articleEntry.getValue();
        return montant;
    }

    public Commande toCommande(long client) {
        return new Commande(client, articles);
    }

    @Override
    public Object clone() {
        PanierImpl panier = new PanierImpl();
        panier.articles = new HashMap<>(articles);
        return panier;
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Article, Integer> articleEntry : articles.entrySet())
            s += '[' + articleEntry.getValue() + "] " + articleEntry.getKey();
        return s;
    }

    @Override
    public void clear() {
        articles.clear();
    }
}
