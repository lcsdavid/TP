package fr.lcsdavid.rmi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Article implements Serializable {
    private String clé;
    private String description;
    private float prix;
    private Calendar calendar;

    public Article(String clé, String description, float prix) {
        this(clé, description, prix, null);
    }

    public Article(String clé, String description, float prix, Calendar calendar) {
        this.clé = clé;
        this.description = description;
        this.prix = prix;
        /* On ne replace pas la date si elle est passée. */
        this.calendar = Calendar.getInstance().after(calendar) ? null : calendar;
    }

    public Calendar date() {
        return calendar;
    }

    public void date(Calendar calendar) {
        this.calendar = calendar;
    }

    public String clé() {
        return clé;
    }

    public String description() {
        return description;
    }

    public double prix() {
        return prix;
    }

    public String toString() {
        return (calendar == null) ? (clé + " :\n" + description + "\nPour : " + prix + "\ndisponible dès maintenant!") : (clé + " :\n" + description + "\nPour : " + prix + "\n" + calendar.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj ==  null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Article) {
            Article article = (Article) obj;
            return article.clé.equals(clé) && article.description.equals(description) && article.prix == prix
                    && (article.calendar == null ? calendar == null : article.calendar.equals(calendar));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clé, description, prix, calendar);
    }
}
