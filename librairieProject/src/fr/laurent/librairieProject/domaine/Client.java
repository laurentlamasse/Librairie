package fr.laurent.librairieProject.domaine;

/**
 * La classe Client comporte les informations sur le client (nom, prenom,
 * identifiant unique et son panier)
 * 
 * @author Laurent
 *
 */
public class Client {

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

	public CollectionLivre getPanier() {
		return panier;
	}

	public void setPanier(CollectionLivre pPanier) {
		panier = pPanier;
	}

	// ================Methodes Metier================
	@Override
	public String toString() {
		String infos = "Identifiant : " + this.id + "\n" + "Prenom : " + this.prenom + "\n" + "Nom : " + this.nom;
		return infos;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj.getClass().equals(this.getClass()))) {
			if (((Client) obj).id == id)
				return true;
		}
		return false;
	}
}
