package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.*;
import fr.lcsdavid.rmi.server.PoolImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client implements RemoteSubscriber {
    /* On récupère le Registry disponible sur la machine 'localhost' sur le port 1099. */
    private Registry registry = LocateRegistry.getRegistry("localhost", 1099);

    private IManager manager;
    private Catalogue catalogue;
    private Pool<Panier> pool;

    private Panier panier;

    public Client() throws RemoteException, NotBoundException {
        manager = (IManager) registry.lookup("manager");
        catalogue = manager.catalogue();
        pool = (Pool<Panier>) registry.lookup("pool");

        panier = pool.instance();
    }

    private String choix(Scanner scanner) {
        System.out.println("[0] Pour quitter.");
        System.out.println("[1] Pour afficher tous les produits.");
        System.out.println("[2] Pour interagire avec le panier.");
        System.out.println("Pour afficher un produit entrez sa clé.");
        return scanner.nextLine();
    }

    private String choixPanier(Scanner scanner) {
        System.out.println("[0] Pour ne rien faire et retourner à l'affichage général.");
        System.out.println("[1] Pour afficher tous les produits du panier.");
        System.out.println("[2] Pour afficher le prix du panier.");
        System.out.println("Pour ajouter ou retirer un ou plusieurs produits entrez sa clé");
        return scanner.nextLine();
    }

    private boolean manipulation(Scanner scanner) throws RemoteException {
        String input = choix(scanner);
        if (input.equals("0"))
            return false;
        else if (input.equals("1"))
            System.out.println(catalogue.toString());
        else if (input.equals("2")) { /* Panier. */
            input = choixPanier(scanner);
            switch (input) {
                case "0":
                    return true;
                case "1":
                    System.out.println(panier.remoteToString());
                    return true;
                case "2":
                    System.out.println(panier.montantPanier());
                    return true;
                default:
                    try {
                        Article article = catalogue.article(input);
                        System.out.println("Entrez la quantité de produit désirée: ");
                        panier.modifierQuantitéArticle(article, scanner.nextInt());
                    } catch (ArticleNotFound articleNotFound) {
                        System.out.println("Cet objet '" + input + "' n'existe pas!");
                    }
                    return true;
            }
        } else if (!input.isEmpty()) {
            try {
                System.out.println(catalogue.article(input).toString());
            } catch (ArticleNotFound articleNotFound) {
                System.out.println("Cet objet '" + input + "' n'existe pas!");
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        /* On crée un objet Hello qui correspond à l'object distant stockée en tant que 'myhello'. */
        // Hello h = (Hello)registry.lookup("myhello");
        // System.out.println(h.sayHello("Vladimir Putin"));
        // System.out.println(h.data("Сейчас"));
        // System.out.println(h.foo().sayFoo("Luka"));

        Client client = new Client();
        boolean shopping = true;
        if (client.panier == null) {
            System.out.println("Au secours on a pas de panier !");
            shopping = false;
        }
        client.subscribe(client.pool);

        Scanner scanner = new Scanner(System.in);
        while (shopping)
            shopping = client.manipulation(scanner);

        client.pool.restitution(client.panier);
        client.unsubscribe(client.pool);
    }
}
