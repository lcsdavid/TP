package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.Article;
import fr.lcsdavid.rmi.ArticleNotFound;
import fr.lcsdavid.rmi.Catalogue;
import fr.lcsdavid.rmi.IManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private static String Choix(Scanner sc) {
        System.out.println("Pour quitter entrez -1");
        System.out.println("Pour afficher tous les produits entrez 0");
        System.out.println("Pour afficher un produit entrez sa clé");
        System.out.println("Pour interagire avec le panier entrez 1");
        String str = sc.nextLine();
        return str;
    }

    private static String ChoixPanier(Scanner sc) {

        System.out.println("Pour ne rien faire et retourner à l'affichage général entrez -1");
        System.out.println("Pour afficher tous les produits du panier entrez 0");
        System.out.println("Pour ajouter ou retirer un ou plusieurs produits entrez sa clé");
        System.out.println("Pour afficher le prix du panier entrez 1");
        String str = sc.nextLine();
        return str;
    }

    private static void manipulation(String str, Catalogue catalogue, Panier panier, Scanner sc) {

        if (str.equals("0"))
            System.out.println(catalogue.toString());
        else if (str.equals("1")) {
            str = ChoixPanier(sc);
            if (str.equals("-1"))
                return;
            else if (str.equals("0"))
                System.out.println(panier.toString());
            else if (str.equals("1"))
                System.out.println(panier.calculMontantPanier());
            else{
                String produit = str;
                System.out.println("Entrez la quantité de produit désirée");
                int qt;
                qt = sc.nextInt();
                try {
                    panier.modifierQuantitéArticle(catalogue.article(produit), qt);
                } catch (ArticleNotFound articleNotFound) {
                    try {
                        panier.ajouterArticle(catalogue.article(produit));
                        panier.modifierQuantitéArticle(catalogue.article(produit), qt);
                    } catch (ArticleNotFound articleNotFound1) {
                        articleNotFound1.printStackTrace();
                    }
                }


            }
        }
        //on est au moment où le client à forcement entré une clé (ou aurait du)
        else if (catalogue.isArticle(str))//si la clé existe
        {
            try {
                System.out.println(catalogue.article(str).toString());
            } catch (ArticleNotFound articleNotFound) {
                articleNotFound.printStackTrace();
            }
        } else //l'utilisateur aurait du entrer une clé mais elle n'est pas valide
            System.out.println("Cet objet n'existe pas!");
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
        Panier panier = new Panier();
        Boolean shopping = true;

        Scanner sc = new Scanner(System.in);
        while (shopping) {
            String res = Choix(sc);

            if (res.equals("-1"))
                break;
            manipulation(res, catalogue, panier, sc);
        }
    }
}
