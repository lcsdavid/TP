package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.Manager;

import java.rmi.RemoteException;
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

    public void initialisation() throws RemoteException {
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", "description coca", 3));
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", "description coca", 3));
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", "description coca", 3));
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", "description coca", 3));
    }

    public void traiterCommande() throws RemoteException {
        manager.recevoirCommande(); // Traiter comme on le souhaite.
    }
}
