package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server implements RemoteServer {
    private Registry registry = LocateRegistry.createRegistry(1099);

    private Catalogue catalogue = new Catalogue();
    private Pool<Panier> pool = new ServerPool<>(ServerPanier::new, 10);

    private List<RemoteSubscriber> subscribers = new ArrayList<>();

    public Server() throws RemoteException, AlreadyBoundException {
        registry.bind("server", UnicastRemoteObject.exportObject(this, 0));
        registry.bind("pool", UnicastRemoteObject.exportObject(pool, 0));

        catalogue.ajouterArticle(new Article("Epée", "Excalibur !", 3));
        catalogue.ajouterArticle(new Article("ВОДКА", "Alcool Russe de bonne facture.", 15.99f));
        catalogue.ajouterArticle(new Article("Coca-Cola", "description coca", 3));
    }

    public Catalogue catalogue() throws RemoteException {
        return catalogue;
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }

    @Deprecated
    public void traiterCommande(Manager manager) throws RemoteException {
        manager.recevoirCommande(); /* À traîter comme on le souhaite. */
    }
}
