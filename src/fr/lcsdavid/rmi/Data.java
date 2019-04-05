package fr.lcsdavid.rmi;

import java.io.Serializable;
import java.util.Date;

public class Data implements Serializable {
    private String message;
    private Date date;

    public Data() {}

    public Data(String message) {
        super();
        this.message = message;
        date = new Date();
    }

    @Override
    public String toString() {
        return message + " " + date.toString();
    }
}
