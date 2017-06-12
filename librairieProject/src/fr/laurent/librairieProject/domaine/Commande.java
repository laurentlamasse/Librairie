package fr.laurent.librairieProject.domaine;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author Laurent
 * La commande comporte un identifiant, une liste de livres, l'identifiant du client et une date
 */
public class Commande {
	private int idCommande;
	private CollectionLivre listeLivre;
	private int idClient;
	private String date;
	
	private static int nb_instance=0;
	
	//================Constructeur================
	public Commande(CollectionLivre pListeLivre, int pIdClient) {
		super();
		this.listeLivre = new CollectionLivre(pListeLivre);
		this.idClient = pIdClient;
		this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		this.idCommande = nb_instance;
		nb_instance++;
	}
	
	//================Getters et Setters================
	public int getIdCommande() {
		return idCommande;
	}
	
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}	
	
	public CollectionLivre getListeLivre() {
		return listeLivre;
	}

	public void setListeLivre(CollectionLivre listeLivre) {
		this.listeLivre = listeLivre;
	}
	
	// ================Methode================
	@Override
	public String toString() {
		String infos = "Identifiant commande : " + idCommande + "\n"
				+ "Date : " + date + "\n" + "Identifiant client : " + idClient +"\n\n";
		infos += "============Liste des livres============\n";
		infos += listeLivre.toString();
		
		return infos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!= null && (obj.getClass().equals(this.getClass())))
		{
			if(((Commande)obj).idCommande == idCommande)
				return true;
		}
		return false;
	}
}
