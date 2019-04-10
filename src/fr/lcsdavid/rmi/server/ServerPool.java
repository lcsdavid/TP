package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Clearable;
import fr.lcsdavid.rmi.Pool;
import fr.lcsdavid.rmi.RemoteSubscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

public class ServerPool<T extends Remote & Clearable> implements Pool {
    private Supplier<T> supplier;
    private ArrayList<RemoteSubscriber> subscribers = new ArrayList<>();

    private List<T> disponible = new ArrayList<>();
    private Map<Remote, T> utilisé = new HashMap<>();

    private int tailleMax = 0;

    public ServerPool(Supplier<T> supplier) {
        this(supplier, 100);
    }

    public ServerPool(Supplier<T> supplier, int taille) {
        super();
        this.supplier = supplier;
        tailleMax = taille;
        for (int i = 0; i < tailleMax; i++)
            disponible.add(supplier.get());
    }

    @Override
    public T instance() {
        if (disponible.isEmpty() || tailleMax == 0)
            return null;
        T objet = disponible.remove(0);
        try {
            objet.clear();
            Remote remote = UnicastRemoteObject.exportObject(objet, 0);
            utilisé.put(remote, objet);
            System.out.println("Panier pris.");
            System.out.println("Nombre de panier libres : " + disponible.size() + ".");
            System.out.println("Nombre de panier occupés : " + utilisé.size() + ".");
            if (disponible.size() < tailleMax / 2)
                notify("La moitié des paniers a été consommé.");
            return (T) remote;
        } catch (RemoteException e) {
            System.out.println("Échec de l'envoie d'un panier");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int taille() {
        return tailleMax;
    }

    @Override
    public int nombreDisponible() {
        return disponible.size();
    }

    @Override
    public void restitution(Remote remote) throws RemoteException {
        /* On le fait si le remote existe. */
        if (utilisé.containsKey(remote)) {
            T objet = (T) utilisé.get(remote);
            /* Test du clear du T. À décommenter pour vérfier.  Ne fonctionne que avec Panier qui a un remoteToString(). */
            /*
            System.out.println(objet.remoteToString());
            objet.clear();
            System.out.println(objet.remoteToString());
            */

            /* On retire le Remote de la liaison. */
            if (!UnicastRemoteObject.unexportObject(utilisé.get(remote), true))
                throw new RemoteException();

            /* Le T est de nouveau disponible. */
            disponible.add(utilisé.get(remote));

            /* Le couple <Remote, T> est retiré des objets utilisés. */
            utilisé.remove(remote);

            System.out.println("Panier restitué. Nombre de panier libres : " + disponible.size() + ". Nombre de panier occupés : " + utilisé.size() + "\n");
        }
    }

    /* Implémentation de RemotePublisher. */

    @Override
    public void addRemoteSubscriber(RemoteSubscriber remoteSubscriber) {
        subscribers.add(remoteSubscriber);
    }

    @Override
    public void removeRemoteSubscriber(RemoteSubscriber remoteSubscriber) {
        subscribers.remove(remoteSubscriber);
    }

    @Override
    public void notify(String message) throws RemoteException {
        System.out.println(message);
        for (RemoteSubscriber subscriber : subscribers)
            subscriber.notify(message);
    }
}
