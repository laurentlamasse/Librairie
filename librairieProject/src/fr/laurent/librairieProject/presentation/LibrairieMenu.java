package fr.laurent.librairieProject.presentation;

import java.util.Scanner;

import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.service.ClienteleService;

public class LibrairieMenu {

	/**
	 * Affiche le menu de test
	 */
	private void afficherMenu() {
		System.out.println("=======[MENU]======");
		System.out.println("1. Afficher tous les livres de la librairie");
		System.out.println("2. Afficher tous les clients");
		System.out.println("3. Afficher la liste des commandes du client");
		System.out.println("4. Afficher le panier du client");
		System.out.println("5. Ajouter un livre au panier d'un client");
		System.out.println("6. Supprimer un livre du panier d'un client");
		System.out.println("7. Valider la commande d'un client");
		System.out.println("0. Quitter");
		System.out.println("\nVotre choix : ");
	}

	/**
	 * Pour effectuer la saisie dans la console
	 * 
	 * @param sc
	 * @return
	 */
	private Integer saisirValeurConsole(Scanner sc) {
		String idStr = sc.nextLine();
		Integer id;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			System.err.println("Erreur de saisi ! Il faut entrer un entier !");
			return -1;
		}
		return id;
	}

	public void useMenu() {
		// Declaration des variables pour les services
		Librairie librairieService;
		ClienteleService clienteleService;

		// Instanciations des services
		clienteleService = new ClienteleService();
		librairieService = clienteleService.getLibrairie();

		Boolean continuer = true;
		Integer idClient, idLivre;
		while (continuer) {
			afficherMenu();
			Scanner sc = new Scanner(System.in);
			String reponse = sc.nextLine();

			switch (reponse) {
			case "1":
				// Affichage des livres
				librairieService.afficherAllLivres();
				break;
			case "2":
				// Affichage des clients
				clienteleService.afficherAllClients();
				break;
			case "3":
				System.out.println("Saisir l'identifiant du client : ");
				idClient = saisirValeurConsole(sc);
				if (idClient != -1)
					clienteleService.afficherAllCommandes(idClient);
				break;
			case "4":
				System.out.println("Saisir l'identifiant du client : ");
				idClient = saisirValeurConsole(sc);
				if (idClient != -1)
					clienteleService.afficherPanier(idClient);
				break;
			case "5":
				System.out.println("Saisir l'identifiant du client : ");
				idClient = saisirValeurConsole(sc);
				System.out.println("Saisir l'indice du livre : ");
				idLivre = saisirValeurConsole(sc);
				if (clienteleService.ajouterLivre(idClient, idLivre))
					System.out.println("Le livre a ete ajoute au panier du client.");
				break;
			case "6":
				System.out.println("Saisir l'identifiant du client : ");
				idClient = saisirValeurConsole(sc);
				System.out.println("Saisir l'indice du livre : ");
				idLivre = saisirValeurConsole(sc);
				if (clienteleService.supprimerLivre(idClient, idLivre))
					System.out.println("Le livre a ete retire du panier du client.");
				break;
			case "7":
				System.out.println("Saisir l'identifiant du client : ");
				idClient = saisirValeurConsole(sc);
				if (idClient != -1) {
					if (clienteleService.validerCommande(idClient))
						System.out.println("Commande valide !!!");
					else
						System.out.println("Commande invalide !!!");
				}
				break;
			case "0": {
				System.out.println("Au revoir !!!");
				continuer = false;
				break;
			}
			default:
				System.err.println(
						"Saisie incorrecte\nEntrez une valeur correspondant a l'un des services proposes ci-dessus.\n");
			}
		}

	}
}
