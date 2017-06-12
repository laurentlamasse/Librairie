package fr.laurent.librairieProject.domaine;

import java.util.ArrayList;

import fr.laurent.librairieProject.domaine.Livre.TypeLivre;
import fr.laurent.librairieProject.exception.ExceptionMontantNegatif;
import fr.laurent.librairieProject.exception.ExceptionValeurNull;

/**
 * 
 * @author Laurent
 * La librairie comporte la liste de tous les livres disponibles (en stock).
 */
public class Librairie {
	
	private CollectionLivre livres;
	
	public Librairie()
	{
		livres = new CollectionLivre();
		
		String titres[] = new String[]{"Les chevaliers de la table ronde",
				"One Piece - Tome 1", "Asterix et Obelix", "Le seigneur des anneaux",
				"Game of thrones - Le trone de fer", null, "Histoire de toto"};
		String auteurs[] = new String[]{"Merlin",
				"Eiichiro Oda", "Uderzo et Goscinny", "Tolkien", "George R. R. Martin", null, "Toto"};
		String date[] = new String[]{"15/02/1984", "20/10/1999", "10/04/2004", "29/07/1954", "17/04/2011", null, "01/01/2017"};
		TypeLivre types[] = new TypeLivre[]{TypeLivre.roman, TypeLivre.manga, TypeLivre.bandedessinee,
				TypeLivre.roman, TypeLivre.roman, null, TypeLivre.roman};
		Float prix[] = new Float[]{null, 15f, 24f, 50f, null, null, -20f};
		String fournisseurs[]= new String[]{null, null, "Anderson", "Durand", null, null, null};
		
		System.out.println("=======ENREGISTREMENT DES LIVRES=======");
		for(int i=0; i<7; i++)
		{
			System.out.println("Enregistrement du livre n=°"+i+" en cours...");
			if(ajouterLivre(titres[i], auteurs[i], date[i], types[i], prix[i], fournisseurs[i]))
				System.out.println("Enregistrement du livre n=°"+i+" termine avec succes");
			else
				System.out.println("Enregistrement du livre n=°"+i+" non valide");
		}
		System.out.println();
		
		System.out.println("======SOLDE======");
		System.out.println(livres.get(0));
		System.out.println("===> Reduction du prix de 15%");
		livres.get(0).solder(0.15f);
		System.out.println(livres.get(0)+"\n");
	}
	
	public Boolean ajouterLivre(String pTitre, String pAuteur, String pDate, TypeLivre pType, Float pPrix, String pFournisseur)
	{
		Livre nouveauLivre = creerLivre(pTitre, pAuteur, pDate, pType, pPrix, pFournisseur);
		if(nouveauLivre != null)
			return livres.add(nouveauLivre);
		return false;
	}
	
	public Livre creerLivre(String pTitre, String pAuteur, String pDate, TypeLivre pType, Float pPrix, String pFournisseur)
	{
		try
		{
			if(pPrix==null)
			{
				if(pFournisseur==null)
					return new Livre(pTitre, pAuteur, pDate, pType);
				else
					return new Livre(pTitre, pAuteur, pDate, pType, pFournisseur);
			}
			else if(pFournisseur == null)
				return new Livre(pTitre, pAuteur, pDate, pType, pPrix);
			return new Livre(pTitre, pAuteur, pDate, pType, pPrix, pFournisseur);
		}
		catch(ExceptionMontantNegatif exceptionPrixNegatif)
		{
			System.out.println("Erreur lors de la creation d'un livre  !");
			System.out.println(exceptionPrixNegatif);
		}
		catch(ExceptionValeurNull exceptionValeurNull)
		{
			System.out.println("Erreur lors de la creation d'un livre  !");
			System.out.println(exceptionValeurNull);
		}
		return null;
	}
	
	public CollectionLivre getAllLivres()
	{
		return livres;
	}
	
	public void afficherAllLivres()
	{
		System.out.println("=====Liste des livres=====");
		for(int i=0; i<livres.size(); i++)
		{
			System.out.println("===> Livre n°=" + i);
			System.out.println(livres.get(i));
			System.out.println("----");
		}
		System.out.println();
	}

}
