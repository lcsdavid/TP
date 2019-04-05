package fr.lcsdavid.rmi;

public class ArticleNotFound extends Exception {
    public String getCle() {
        return cle;
    }

    private String cle;

    public ArticleNotFound(String cle){
        this.cle = cle;
    }
}