package fr.lcsdavid.rmi.client;

import fr.lcsdavid.rmi.ArticleNotFound;
import fr.lcsdavid.rmi.server.Catalogue;
import fr.lcsdavid.rmi.Manager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private static int Choix(){
        System.out.println("Pour quitter entrez -1");
        System.out.println("Pour afficher tous les produits entrez 0");
        System.out.println("Pour afficher un produit entrez sa clé");
        System.out.println("Pour interagire avec le panier entrez 1");
        return -1;
    }

    private static int ChoixPanier(){

        System.out.println("Pour ne rien faire et retourner à l'affichage général entrez -1");
        System.out.println("Pour afficher tous les produits du panier entrez 0");
        System.out.println("Pour ajouter ou retirer un ou plusieurs produits entrez sa clé");
        System.out.println("Pour afficher le prix du panier entrez 1");
        return -1;
    }

    private static void manipulation(int choix, Catalogue catalogue){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        if(str.equals("0"))
            System.out.println(catalogue.toString());
        else if(str.equals("1")){
            ChoixPanier();
            str = sc.nextLine();
            if(str.equals("-1"))
                return;
            else if(str.equals("0"))
                //TODO;
                ;
            else if (str.equals("1"))
                //TODO
                ;
            else
                //TODO
                ;
        }
        //on est au moment où le client à forcement entré une clé (ou aurait du)
        else if(catalogue.isArticle(str))//si la clé existe
        {
            try {
                System.out.println(catalogue.article(str).toString());
            } catch (ArticleNotFound articleNotFound) {
                articleNotFound.printStackTrace();
            }
        }

        else //l'utilisateur aurait du entrer une clé mais elle n'est pas valide
            System.out.println("Cet objet n'existe pas!");
    }

    public static void main(String[] args) throws Exception{
        /* On récupère le Registry disponible sur la machine 'localhost' sur le port 1099. */
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        /* On crée un objet Hello qui correspond à l'object distant stockée en tant que 'myhello'. */
        // Hello h = (Hello)registry.lookup("myhello");
        // System.out.println(h.sayHello("Vladimir Putin"));
        // System.out.println(h.data("Сейчас"));
        // System.out.println(h.foo().sayFoo("Luka"));
        Manager serverManager = (Manager) registry.lookup("serverManager");
        Catalogue catalogue = serverManager.catalogue();
        System.out.println(catalogue.toString());

        Boolean shopping = true;

        while(shopping){
            int res = Choix();

            if(res == -1)
                break;
            manipulation(res, catalogue);
        }
    }
}
