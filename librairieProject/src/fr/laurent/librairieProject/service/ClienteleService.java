package fr.laurent.librairieProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.laurent.librairieProject.domaine.Client;
import fr.laurent.librairieProject.domaine.CollectionLivre;
import fr.laurent.librairieProject.domaine.Commande;
import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.domaine.Livre;

/**
 * 
 * @author Laurent C'est la classe service. C'est ici qu'on va faire la
 *         correspondance entre les clients, leurs commandes et les livres de la
 *         librairie et les interactions entres eux.
 */
public class ClienteleService {
	// ================Propriete================
	private Map<Client, ArrayList<Commande>> associationCommandeClient;
	LibrairieService librairieService;

	// ================Constructeur================
	public ClienteleService() {
		librairieService = new LibrairieService();

		associationCommandeClient = new HashMap<Client, ArrayList<Commande>>();

		// Client tabClients[] = new Client[3];
		// tabClients[0] = new Client("Allen", "Barry");
		// tabClients[1] = new Client("Kent");
		// tabClients[2] = new Client("Wayne", "Bruce");
		//
		// for (int i = 0; i < tabClients.length; i++) {
		// associationCommandeClient.put(tabClients[i], new
		// ArrayList<Commande>());
		// }
	}

	// ================Getter================
	/**
	 * Obtenir un client a partir de son identifiant
	 * 
	 * @param idClient
	 *            Identifiant du client que l'on a selectionne
	 * @return La reference du client. Si l'identifiant n'est attribue a aucun
	 *         client, retourne null
	 */
	public Client getClient(Integer idClient) {
		Set<Client> clients = getAllClients();
		Iterator<Client> it = clients.iterator();

		while (it.hasNext()) {
			Client client = it.next();
			if (client.getId() == idClient)
				return client;
		}

		return null;
	}

	public Set<Client> getAllClients() {
		Set<Client> clients = new HashSet<Client>();
		clients = associationCommandeClient.keySet();
		return clients;
	}

	// ================Methodes d'affichage================
	public void afficherAllClients() {
		System.out.println("=====Liste des clients=====");
		Set<Client> clients = getAllClients();
		for (Client client : clients) {
			System.out.println(client);
			System.out.println("----");
		}
		System.out.println();
	}

	public void afficherAllCommandes(Integer idClient) {
		Client client = getClient(idClient);
		if (client != null) {
			if (!associationCommandeClient.containsKey(client)) {
				System.err.println("Le client d'identifiant \"" + client.getId() + "\" n'existe pas !");
			}
			ArrayList<Commande> listeCommandes = associationCommandeClient.get(client);

			for (Commande commande : listeCommandes) {
				System.out.println(commande);
				System.out.println("------------------------");
			}
		} else
			System.err.println("Aucun client ne possede cet identifiant.");
	}

	public void afficherPanier(Integer idClient) {
		Client client = getClient(idClient);
		if (client == null) {
			System.err.println("Aucun client ne possede cet identifiant.");
			return;
		}

		if (client.getPanier().isEmpty()) {
			System.out.println("Le client " + client.getPrenom() + " " + client.getNom() + " n'a pas de panier.\n");
			return;
		}

		System.out.println("=====Panier du client " + client.getId() + "-" +
				client.getPrenom() + " " + client.getNom() + "=====");
		System.out.println(client.getPanier());
		System.out.println("=====Fin du panier=====\n");
	}

	// ================Methodes de gestion de commande================
	public Boolean ajouterLivre(Integer idClient, Integer idLivre) {
		return modifierPanier(true, idClient, idLivre);
	}

	public Boolean supprimerLivre(Integer idClient, Integer idLivre) {
		return modifierPanier(false, idClient, idLivre);
	}

	/**
	 * Methode privée qui est utilisée par les méthodes ajouterLivre et
	 * supprimerLivre
	 * 
	 * @param ajout
	 * @param idClient
	 * @param idLivre
	 * @return indique si l'ajout ou la suppression s'est bien effectué.
	 */
	private Boolean modifierPanier(Boolean ajout, Integer idClient, Integer idLivre) {
		Client client = getClient(idClient);
		if (client == null) {
			System.err.println("Aucun client ne possede cet identifiant.");
			return false;
		}

		Livre livre = librairieService.chercherLivre(idLivre);
		if (livre == null)
			return false;

		if (ajout == true)
			return client.getPanier().add(livre);
		return client.getPanier().remove(livre);
	}

	public Boolean validerCommande(Integer idClient) {
		Client client = getClient(idClient);
		if (client == null) {
			System.err.println("Aucun client ne possede cet identifiant.");
			return false;
		}
		if (client.getPanier() != null && !client.getPanier().isEmpty()) {
			Commande nouvelleCommande = new Commande(client.getPanier(), client.getId());
			client.getPanier().clear();
			client.setPanier(new CollectionLivre());
			associationCommandeClient.get(client).add(nouvelleCommande);
			return true;
		}
		return false;
	}
}
