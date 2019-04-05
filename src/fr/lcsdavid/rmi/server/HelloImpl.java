package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Data;
import fr.lcsdavid.rmi.Foo;
import fr.lcsdavid.rmi.Hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl implements Hello {
    /* TP du matin */
    private Foo foo = (Foo) UnicastRemoteObject.exportObject(new FooImpl(), 0);

    public HelloImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) {
        return "Привет " + name;
    }

    @Override
    public Data data(String name) {
        return new Data(name);
    }

    @Override
    public Foo foo() throws RemoteException {
        return foo;
    }


    /* TP de l'après-midi */
    private static Catalogue LE_CATALOGUE = new Catalogue();

    public Catalogue catalogue() {
        return LE_CATALOGUE;
    }
}
