package fr.laurent.librairieProject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.laurent.librairieProject.domaine.Client;
import fr.laurent.librairieProject.domaine.Commande;
import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.domaine.Livre;

/**
 * 
 * @author Laurent
 * C'est la classe service. C'est ici qu'on va faire la correspondance entre les clients, leurs commandes et les livres de la librairie
 *  et les interactions entres eux.
 */
public class ClienteleService {
	private Map<Client, ArrayList<Commande>> associationCommandeClient;
	private Librairie librairie;

	public ClienteleService() {
		associationCommandeClient = new HashMap<Client, ArrayList<Commande>>();

		librairie = new Librairie();

		Client tabClients[] = new Client[3];
		tabClients[0] = new Client("Allen", "Barry");
		tabClients[1] = new Client("Kent");
		tabClients[2] = new Client("Wayne", "Bruce");

		for (int i = 0; i < tabClients.length; i++) {
			associationCommandeClient.put(tabClients[i], new ArrayList<Commande>());
		}
	}

	public Librairie getLibrairie() {
		return librairie;
	}

	public Set<Client> getAllClients() {
		Set<Client> clients = new HashSet<Client>();
		clients = associationCommandeClient.keySet();
		return clients;
	}

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

	public void afficherAllCommandes(Integer idClient) {
		Client client = getClient(idClient);
		if (client != null)
			afficherAllCommandes(client);
		else
			System.err.println("Aucun client ne possede cet identifiant.");
	}

	public void afficherAllCommandes(Client client) {
		if (!associationCommandeClient.containsKey(client)) {
			System.err.println("Le client d'identifiant \"" + client.getId() + "\" n'existe pas !");
		}
		ArrayList<Commande> listeCommandes = associationCommandeClient.get(client);

		for (Commande commande : listeCommandes) {
			System.out.println(commande);
			System.out.println("------------------------");
		}
	}

	public void afficherPanier(Integer idClient) {
		Client client = getClient(idClient);
		if (client == null) {
			System.err.println("Aucun client ne possede cet identifiant.");
			return;
		}
		client.afficherPanier();
	}

	public void afficherAllClients() {
		System.out.println("=====Liste des clients=====");
		Set<Client> clients = getAllClients();
		for (Client client : clients) {
			System.out.println(client);
			System.out.println("----");
		}
		System.out.println();
	}
	
	public Boolean ajouterLivre(Integer idClient, Integer idLivre) {
		return modifierPanier(true, idClient, idLivre);
	}
	
	public Boolean supprimerLivre(Integer idClient, Integer idLivre) {
		return modifierPanier(false, idClient, idLivre);
	}
	
	/**
	 * Methode privée qui est utilisée par les méthodes ajouterLivre et supprimerLivre
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
		
		Livre livre = chercherLivre(idLivre);
		if(livre == null)
			return false;
		
		if(ajout == true)
			return client.ajouterPanier(livre);
		return client.supprimerPanier(livre);
	}

	public Boolean validerCommande(Integer idClient) {
		Client client = getClient(idClient);
		if (client == null) {
			System.err.println("Aucun client ne possede cet identifiant.");
			return false;
		}
		Commande commande = client.commander();
		if (commande != null) {
			associationCommandeClient.get(client).add(commande);
			return true;
		}
		return false;
	}
	
	public void solderLivre(Integer indice, Float pourcentage)
	{
		Livre livre = chercherLivre(indice);
		if(livre == null)
			return;
		
		Float ancienPrix = livre.getPrix();
		livre.solder(pourcentage);
	}
	
	private Livre chercherLivre(Integer idLivre)
	{
		Livre livre;
		try{
			return librairie.getAllLivres().get(idLivre);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.err.println("Erreur ! L'indice du livre n'est pas correct !");
			return null;
		}
	}
}
