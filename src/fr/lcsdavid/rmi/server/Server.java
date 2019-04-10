package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.IManager;
import fr.lcsdavid.rmi.Manager;
import fr.lcsdavid.rmi.Panier;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws Exception {
        // Hello h = new HelloImpl();
        Manager manager = new Manager();
        Server.initialisation(manager);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("manager", UnicastRemoteObject.exportObject(manager, 0));

        PoolImpl<PanierImpl> pool = new PoolImpl<>(PanierImpl::new, 50);
        registry.bind("pool", UnicastRemoteObject.exportObject(pool, 0));
    }

    public static void initialisation(Manager manager) throws RemoteException {
        manager.catalogue().ajouterArticle(new Article("Epée", "clinc", 3));
        manager.catalogue().ajouterArticle(new Article("ВОДКА", "Alcool Russe de bonne facture.", 15.99f));
        manager.catalogue().ajouterArticle(new Article("Coca-Cola", "description coca", 3));
    }

    public void traiterCommande(Manager manager) throws RemoteException {
        manager.recevoirCommande(); // Traiter comme on le souhaite.
    }
}
