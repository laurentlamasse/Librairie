package fr.laurent.librairieProject.domaine;

import fr.laurent.librairieProject.exception.ExceptionMontantNegatif;

/**
 * 
 * @author Laurent Classe décrivant un article. Un article contient un prix et
 *         le nom du fournisseur
 */
public abstract class Article {

	// ================Attributs================
	protected float prix;
	private String fournisseur;

	// ================Constructeurs================
	public Article(float pPrix, String pFournisseur) throws ExceptionMontantNegatif {
		if (pPrix < 0)
			throw new ExceptionMontantNegatif();
		this.prix = pPrix;
		this.fournisseur = pFournisseur;
	}

	public Article(float pPrix) throws ExceptionMontantNegatif {
		this(pPrix, "Dupont");
	}

	public Article(String pFournisseur) throws ExceptionMontantNegatif {
		this(10, pFournisseur);
	}

	public Article() throws ExceptionMontantNegatif {
		this(10, "Dupont");
	}

	// ================Getters et Setters================
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) throws ExceptionMontantNegatif {
		if (prix < 0)
			throw new ExceptionMontantNegatif();
		this.prix = prix;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	// ================Methode================
	@Override
	public String toString() {
		String resultat = "Prix : " + prix + " euros\n";
		resultat += "Fournisseur : " + fournisseur;
		return resultat;
	}
}
