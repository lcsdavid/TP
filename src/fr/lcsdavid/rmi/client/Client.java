package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.server.Catalogue;
import fr.lcsdavid.rmi.Manager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws Exception {
        /* On récupère le Registry disponible sur la machine 'localhost' sur le port 1099. */
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        /* On crée un objet Hello qui correspond à l'object distant stockée en tant que 'myhello'. */
        // Hello h = (Hello)registry.lookup("myhello");
        // System.out.println(h.sayHello("Vladimir Putin"));
        // System.out.println(h.data("Сейчас"));
        // System.out.println(h.foo().sayFoo("Luka"));
        Manager manager = (Manager) registry.lookup("manager");
        Catalogue catalogue = manager.catalogue();
        System.out.println(catalogue.toString());
    }
}
