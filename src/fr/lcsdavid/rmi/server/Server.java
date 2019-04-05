package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.Manager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static Manager manager = new Manager();

    public static void main(String[] args) throws Exception {
        // Hello h = new HelloImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("manager", UnicastRemoteObject.exportObject(manager, 0));
    }

    public void initialisation() {
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", ""));
    }

    public void traiterCommande() {
        manager.recevoirCommande(); // Traiter comme on le souhaite.
    }
}
