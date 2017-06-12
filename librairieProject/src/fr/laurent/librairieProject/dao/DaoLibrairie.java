package fr.laurent.librairieProject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.domaine.Livre;
import fr.laurent.librairieProject.domaine.Livre.TypeLivre;
import fr.laurent.librairieProject.exception.ExceptionMontantNegatif;
import fr.laurent.librairieProject.exception.ExceptionValeurNull;

public class DaoLibrairie {
	private AccesBaseDeDonnees accesBDD;
	
	public DaoLibrairie()
	{
		accesBDD = new AccesBaseDeDonnees();
	}

	public boolean initLibrairie(Librairie librairie) {
		ResultSet rs = accesBDD.executeSelect(
				"SELECT livre.*, typelivre.type AS type "
				+ "FROM livre, typelivre "
				+ "WHERE typelivre.ID = livre.IDType;");
		
		//Parcours du ResultSet
		try
		{
			while(rs.next())
			{
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String date = rs.getString("date");
				float prix = rs.getFloat("prix");
				String fournisseur = rs.getString("fournisseur");
				TypeLivre type = getType(rs.getString("type"));
				
				Livre livre = new Livre(titre, auteur, date, type, prix, fournisseur);
				librairie.getAllLivres().add(livre);
			}
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ExceptionValeurNull e)
		{
			e.printStackTrace();
		}
		catch(ExceptionMontantNegatif e)
		{
			e.printStackTrace();
		}
		finally
		{
			return false;
		}
	}

	private TypeLivre getType(String type) {
		switch(type)
		{
		case "Roman":
			return TypeLivre.roman;
		case "Manga":
			return TypeLivre.manga;
		case "Comics":
			return TypeLivre.comcics;
		case "Documentaire":
			return TypeLivre.document;
		case "Bande Dessinee":
			return TypeLivre.bandedessinee;
		default:
				return TypeLivre.inconnu;
		}
	}
}
