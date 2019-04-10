package fr.lcsdavid.rmi;

import java.io.Serializable;
import java.util.Map;

@Deprecated
public class Commande implements Serializable {
    private long client;
    private Map<Article, Integer> articles;

    @Deprecated
    public Commande(long client, Map<Article, Integer> panier) {
        super();
        this.client = client;
        articles = panier;
    }
}
