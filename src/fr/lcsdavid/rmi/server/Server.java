package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private Registry registry = LocateRegistry.createRegistry(1099);

    // private Manager manager = new Manager();

    private Catalogue catalogue = new Catalogue();
    private Pool<Panier> pool = new PoolImpl<>(PanierImpl::new, 10);

    public Server() throws RemoteException, AlreadyBoundException {
        // registry.bind("manager", UnicastRemoteObject.exportObject(manager, 0));
        registry.bind("pool", UnicastRemoteObject.exportObject(pool, 0));

        catalogue.ajouterArticle(new Article("Epée", "Excalibur !", 3));
        catalogue.ajouterArticle(new Article("ВОДКА", "Alcool Russe de bonne facture.", 15.99f));
        catalogue.ajouterArticle(new Article("Coca-Cola", "description coca", 3));
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }

    @Deprecated
    public void traiterCommande(Manager manager) throws RemoteException {
        manager.recevoirCommande(); /* À traîter comme on le souhaite. */
    }
}
