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

    public void ajouterArticle(Article article, int quantité) {
        articles.put(article, quantité);
    }

    public void modifierQuantitéArticle(Article article, int nouvelleQuantité) {
        if (nouvelleQuantité < 0)
            throw new IllegalArgumentException();
        if (nouvelleQuantité != 0) {
            if (articles.containsKey(article))
                articles.replace(article, nouvelleQuantité);
            else
                ajouterArticle(article, nouvelleQuantité);
        } else
            retirerArticle(article);
    }

    public void retirerArticle(Article article) {
        articles.remove(article);
    }

    public double montantPanier() {
        double montant = 0;
        for (Map.Entry<Article, Integer> articleEntry : articles.entrySet())
            montant += articleEntry.getKey().prix() * articleEntry.getValue();
        return montant;
    }

    public Commande toCommande(long client) {
        return new Commande(client, articles);
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Article, Integer> articleEntry : articles.entrySet())
            s += "[Quantité: " + articleEntry.getValue() + "] " + articleEntry.getKey().clé() + "\n";
        return s;
    }

    @Override
    public void clear() {
        articles.clear();
    }
}
