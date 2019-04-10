package fr.lcsdavid.rmi;

public class ArticleNotFound extends Exception {

    public ArticleNotFound(String clé){
        super("Cet article '" + clé + "' n'existe pas!");
    }
}