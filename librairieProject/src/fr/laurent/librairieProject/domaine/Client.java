package fr.laurent.librairieProject.domaine;

/**
 * La classe Client comporte les informations sur le client (nom, prenom, identifiant unique et son panier)
 * 
 * @author Laurent
 *
 */
public class Client implements Panier {

	// ================Attributs================
	private String nom;
	private String prenom;
	private int id;
	private CollectionLivre panier;

	// Variable static pour l'initialisation de l'identifiant du client
	private static int nb_instance = 0;

	public Client(String pNom, String pPrenom) {
		this.nom = pNom;
		this.prenom = pPrenom;
		this.panier = new CollectionLivre();
		this.id = nb_instance;
		System.out.println("=====Enregistrement du nouveau client=====\n" + this.toString() + "\n");

		nb_instance++;
	}
	
	public Client(String pNom) {
		this(pNom, "");
	}

	// ================Getters et Setters================
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSizePanier()
	{
		if(panier != null)
			return panier.size();
		return -1;
	}
	// ================Methodes Metier================
	@Override
	public String toString() {
		String infos = "Identifiant : " + this.id + "\n"
				+ "Prenom : " + this.prenom + "\n" + "Nom : " + this.nom;
		return infos;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj!= null && (obj.getClass().equals(this.getClass())))
		{
			if(((Client)obj).id == id)
				return true;
		}
		return false;
	}
	

	/**
	 * Ajoute un livre dans le panier du client
	 * 
	 * @param livre
	 */
	@Override
	public Boolean ajouterPanier(Livre livre) {
		return panier.ajouter(livre);
	}

	/**
	 * Supprime un livre dans le panier du client
	 * 
	 * @param livre
	 */
	@Override
	public Boolean supprimerPanier(Livre livre) {
		return panier.supprimer(livre);
	}

	/**
	 * Crée la commande à partir du panier du client. Une fois la commande validée, le panier sera vidé.
	 * 
	 * @return on retourne la nouvelle commande. Si le panier était déjà vide, la méthode retourne null
	 */
	public Commande commander() {
		if(panier != null && !panier.isEmpty())
		{
			Commande nouvelleCommande = new Commande(panier, id);
			viderPanier();
			return nouvelleCommande;
		}
		return null;
	}
	
	/**
	 * Affiche le contenu du panier du client
	 */
	@Override
	public void afficherPanier() {
		if (panier.isEmpty()) {
			System.out.println("Le client " + prenom + " " + nom + " n'a pas de panier.\n");
			return;
		}

		System.out.println("=====Panier du client " + id + "-" + prenom + " " + nom + "=====");
		System.out.println(panier);
		System.out.println("=====Fin du panier=====\n");
	}

	@Override
	public void viderPanier() {
		panier.clear();
		panier = new CollectionLivre();
	}
}
