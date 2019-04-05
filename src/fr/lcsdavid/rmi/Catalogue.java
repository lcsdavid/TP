package fr.lcsdavid.rmi;

import java.io.Serializable;

public interface Catalogue extends Serializable {

    public Article article(String clé) throws ArticleNotFound;

    public int nombreArticle();

    @Override
    public String toString();
}
