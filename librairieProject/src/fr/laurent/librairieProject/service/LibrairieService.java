package fr.laurent.librairieProject.service;

import fr.laurent.librairieProject.dao.DaoLibrairie;
import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.domaine.Livre;
import fr.laurent.librairieProject.domaine.Livre.TypeLivre;
import fr.laurent.librairieProject.exception.ExceptionMontantNegatif;
import fr.laurent.librairieProject.exception.ExceptionValeurNull;

public class LibrairieService {
	// ================Propriete================
	private Librairie librairie;
	private DaoLibrairie daoLibrairie;

	// ================Constructeur================
	public LibrairieService() {
		librairie = new Librairie();
		daoLibrairie = new DaoLibrairie();
		daoLibrairie.initLibrairie(librairie);
	}

	// ================Getter================
	public Librairie getLibrairie() {
		return librairie;
	}

	// ================Methodes================
	public Boolean ajouterLivre(String pTitre, String pAuteur, String pDate, TypeLivre pType, Float pPrix,
			String pFournisseur) {
		Livre nouveauLivre = creerLivre(pTitre, pAuteur, pDate, pType, pPrix, pFournisseur);
		if (nouveauLivre != null)
			return librairie.getAllLivres().add(nouveauLivre);
		return false;
	}

	public Livre creerLivre(String pTitre, String pAuteur, String pDate, TypeLivre pType, Float pPrix,
			String pFournisseur)
	{
		try
		{
			if (pPrix == null)
			{
				if (pFournisseur == null)
					return new Livre(pTitre, pAuteur, pDate, pType);
				else
					return new Livre(pTitre, pAuteur, pDate, pType, pFournisseur);
			}
			else if (pFournisseur == null)
				return new Livre(pTitre, pAuteur, pDate, pType, pPrix);
			return new Livre(pTitre, pAuteur, pDate, pType, pPrix, pFournisseur);
		}
		catch (ExceptionMontantNegatif exceptionPrixNegatif)
		{
			System.out.println("Erreur lors de la creation d'un livre  !");
			System.out.println(exceptionPrixNegatif);
		}
		catch (ExceptionValeurNull exceptionValeurNull)
		{
			System.out.println("Erreur lors de la creation d'un livre  !");
			System.out.println(exceptionValeurNull);
		}
		return null;
	}

	public void afficherAllLivres() {
		System.out.println("=====Liste des livres=====");
		for (int i = 0; i < librairie.getAllLivres().size(); i++) {
			System.out.println("===> Livre n°=" + i);
			System.out.println(librairie.getAllLivres().get(i));
			System.out.println("----");
		}
		System.out.println();
	}

	public Livre chercherLivre(Integer idLivre) {
		Livre livre;
		try {
			return librairie.getAllLivres().get(idLivre);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Erreur ! L'indice du livre n'est pas correct !");
			return null;
		}
	}
}
