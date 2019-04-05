package fr.lcsdavid.rmi;

public interface Catalogue {
    public Article article(String clé) throws ArticleNotFound;

    public int nombreArticle();


    @Override
    public String toString();
}
