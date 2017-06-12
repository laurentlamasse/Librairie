package fr.laurent.librairieProject.domaine;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.laurent.librairieProject.exception.ExceptionMontantNegatif;
import fr.laurent.librairieProject.exception.ExceptionValeurNull;

/**
 * Un livre comporte des informations sur un livre
 * 
 * @author Laurent
 *
 */
public class Livre extends Article {

	/**
	 * Enumeration sur les types de livre vendu dans la lirairie
	 * 
	 * @author Laurent
	 *
	 */
	public enum TypeLivre {
		inconnu, document, roman, manga, comcics, bandedessinee
	}

	// ================Attributs================
	private String titre;
	private String auteur;
	private String date;
	private TypeLivre type;

	// ================Constructeurs================
	/**
	 * 
	 * @param pTitre
	 * @param pAuteur
	 * @param pDate
	 * @param pType
	 * @param pPrix
	 * @param pFournisseur
	 */
	public Livre(String pTitre, String pAuteur, String pDate, TypeLivre pType, float pPrix, String pFournisseur) throws ExceptionMontantNegatif, ExceptionValeurNull
	{
		super(pPrix, pFournisseur);
		testerNotNull(pTitre, pAuteur, pDate, pType);
		this.titre = pTitre;
		this.auteur = pAuteur;
		this.date = pDate;
		this.type = pType;
	}

	/**
	 * 
	 * @param pTitre
	 * @param pAuteur
	 * @param pDate
	 * @param pType
	 * @param pPrix
	 * @throws ExceptionValeurNull 
	 */
	public Livre(String pTitre, String pAuteur, String pDate, TypeLivre pType, float pPrix) throws ExceptionMontantNegatif, ExceptionValeurNull
	{
		super(pPrix);
		testerNotNull(pTitre, pAuteur, pDate, pType);
		this.titre = pTitre;
		this.auteur = pAuteur;
		this.date = pDate;
		this.type = pType;
	}

	/**
	 * 
	 * @param pTitre
	 * @param pAuteur
	 * @param pDate
	 * @param pType
	 * @param pFournisseur
	 * @throws ExceptionValeurNull 
	 */
	public Livre(String pTitre, String pAuteur, String pDate, TypeLivre pType, String pFournisseur) throws ExceptionMontantNegatif, ExceptionValeurNull
	{
		super(pFournisseur);
		testerNotNull(pTitre, pAuteur, pDate, pType);
		this.titre = pTitre;
		this.auteur = pAuteur;
		this.date = pDate;
		this.type = pType;
	}

	/**
	 * Constructeur Livre
	 * 
	 * @param titre
	 * @param auteur
	 * @param date
	 * @param type
	 * @throws ExceptionValeurNull 
	 */
	public Livre(String pTitre, String pAuteur, String pDate, TypeLivre pType) throws ExceptionMontantNegatif, ExceptionValeurNull
	{
		super();
		testerNotNull(pTitre, pAuteur, pDate, pType);
		this.titre = pTitre;
		this.auteur = pAuteur;
		this.date = pDate;
		this.type = pType;
	}

	/**
	 * Constructeur par defaut
	 * @throws ExceptionValeurNull 
	 */
	public Livre() throws ExceptionMontantNegatif, ExceptionValeurNull
	{
		this("Sans titre", "Inconnu",
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()),
				TypeLivre.inconnu);
	}

	// ================Getters et Setters================
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public TypeLivre getType() {
		return type;
	}

	public void setType(TypeLivre type) {
		this.type = type;
	}
	
	// ================Methode================
	@Override
	public String toString() {
		String resultat = "Titre : " + titre + "\n";
		resultat += "Auteur : " + auteur + "\n";
		resultat += "Date : " + date + "\n";
		resultat += "Type : " + type + "\n";
		resultat += super.toString();
		return resultat;
	}
	
	public boolean equals(Object obj) {
		if(obj!= null && (obj.getClass().equals(this.getClass())))
		{
			if(((Livre)obj).titre == titre)
				if(((Livre)obj).auteur == auteur)
					if(((Livre)obj).date == date)
						return true;
		}
		return false;
	}
	
	private void testerNotNull(String pTitre, String pAuteur, String pDate, TypeLivre pType) throws ExceptionValeurNull
	{
		if(pTitre == null)
			throw new ExceptionValeurNull("Le titre du livre est null.");
		if(pAuteur == null)
			throw new ExceptionValeurNull("L'auteur du livre est null.");
		if(pDate == null)
			throw new ExceptionValeurNull("La date du livre est null.");
		if(pType == null)
			throw new ExceptionValeurNull("Le type du livre est null.");
	}

	@Override
	public void solder(float pourcentage) {
		float pourcentageSolde = pourcentage;
		if(pourcentageSolde > 1.0f)
			pourcentageSolde = 1.0f;
		else if(pourcentageSolde < 0.0f)
			pourcentageSolde = 0.0f;
		
		prix -= prix*pourcentageSolde;
		System.out.println("Le livre \""+titre+"\" a ete solde de " + pourcentage*100f +"%");
	}
}
