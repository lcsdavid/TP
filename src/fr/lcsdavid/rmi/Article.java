package fr.lcsdavid.rmi;

import java.io.Serializable;
import java.util.Calendar;

public class Article implements Serializable {
    private String clé;
    private String description;
    private float prix;
    private Calendar date;

    public Article(String clé, String description, float prix) {
        this.clé = clé;
        this.description = description;
        this.prix = prix;
        date = null;
    }

    public Article(String clé, String description, float prix, Calendar date) {
        this.clé = clé;
        this.description = description;
        this.prix = prix;
        if (date.before(Calendar.getInstance()))
            date = null; // dans ce cas on  ne replace pas la date car elle n'est pas valide
        else
            this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getClé() {
        return clé;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public String toString() {
        return (date == null) ? (clé + " :\n" + description + "\nPour : " + prix + "\ndisponible dès maintenant!") : (clé + " :\n" + description + "\nPour : " + prix + "\n" + date.toString());
    }
}
