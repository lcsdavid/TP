package fr.lcsdavid.rmi;

import java.io.Serializable;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.ArticleNotFound;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Catalogue implements Serializable {
    Set<Article> articles = new HashSet<>();

    public Catalogue() {
        super();
    }
    
    public Boolean isArticle(String clé){
        for(Article article : articles){
            return true;
        }
        return false;
    }

    public void ajouterArticle(Article article) {
        articles.add(article);
    }

    public Article article(String clé) throws ArticleNotFound {
        for (Article article : articles) {
            if (article.getClé().equals(clé))
                return article;
        }
        throw new ArticleNotFound(clé);
    }

    public int nombreArticle(){
        return articles.size();
    }

    @Override
    public String toString() {
        String s = "";
        for (Article article: articles)
            s += article.toString() + "\n\n";
        return s;
    }
}