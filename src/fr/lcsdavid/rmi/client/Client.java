package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client implements RemoteSubscriber {

    private static String Choix(Scanner sc) {
        System.out.println("[0] Pour quitter.");
        System.out.println("[1] Pour afficher tous les produits.");
        System.out.println("[2] Pour interagire avec le panier.");
        System.out.println("Pour afficher un produit entrez sa clé.");
        return sc.nextLine();
    }

    private static String ChoixPanier(Scanner sc) {
        System.out.println("[0] Pour ne rien faire et retourner à l'affichage général.");
        System.out.println("[1] Pour afficher tous les produits du panier.");
        System.out.println("[2] Pour afficher le prix du panier.");
        System.out.println("Pour ajouter ou retirer un ou plusieurs produits entrez sa clé");

        return sc.nextLine();
    }

    private static void manipulation(String str, Catalogue catalogue, Panier panier, Scanner sc) throws RemoteException {
        if (str.equals("1"))
            System.out.println(catalogue.toString());
        else if (str.equals("2")) { /* Panier. */
            str = ChoixPanier(sc);
            if (str.equals("0"))
                return;
            else if (str.equals("1"))
                System.out.println(panier.remoteToString());
            else if (str.equals("2"))
                System.out.println(panier.montantPanier());
            else {
                try {
                    Article article = catalogue.article(str);
                    System.out.println("Entrez la quantité de produit désirée: ");
                    panier.modifierQuantitéArticle(article, sc.nextInt());
                } catch (ArticleNotFound articleNotFound) {
                    System.out.println("Cet objet '" + str + "' n'existe pas!");
                }
            }
        } else if (!str.isEmpty()) {
            try {
                System.out.println(catalogue.article(str).toString());
            } catch (ArticleNotFound articleNotFound) {
                System.out.println("Cet objet '" + str + "' n'existe pas!");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        /* On récupère le Registry disponible sur la machine 'localhost' sur le port 1099. */
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        /* On crée un objet Hello qui correspond à l'object distant stockée en tant que 'myhello'. */
        // Hello h = (Hello)registry.lookup("myhello");
        // System.out.println(h.sayHello("Vladimir Putin"));
        // System.out.println(h.data("Сейчас"));
        // System.out.println(h.foo().sayFoo("Luka"));
        IManager manager = (IManager) registry.lookup("manager");
        Catalogue catalogue = manager.catalogue();
        //System.out.println(catalogue.toString());
        Pool<Panier> pool = (Pool<Panier>) registry.lookup("pool");
        Panier panier = pool.instance();

        boolean shopping = true;
        if(panier == null){
            System.out.println("au secours on a pas de panier !");
            shopping = false;
        }
        Client client = new Client();
        client.subscribe(pool);

        Scanner sc = new Scanner(System.in);
        while (shopping) {
            String res = Choix(sc);
            if (res.equals("0"))
                break;
            manipulation(res, catalogue, panier, sc);
        }

        pool.restitution(panier);
        client.unsubscribe(pool);
    }
}
