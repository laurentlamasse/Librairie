package fr.laurent.librairieProject.domaine;

public interface Panier {
	public Boolean ajouterPanier(Livre livre);
	public Boolean supprimerPanier(Livre livre);
	public void afficherPanier();
	public void viderPanier();
}
