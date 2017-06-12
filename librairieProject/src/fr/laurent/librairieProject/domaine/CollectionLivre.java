package fr.laurent.librairieProject.domaine;

import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author Laurent Une collection de livres est un tableau d'objet de type
 *         Livre. La classe est une classe fille de Vector<Livre>. Elle hérite
 *         donc de son comportement.
 */
public class CollectionLivre extends Vector<Livre> {

	// ================Constructeurs================
	public CollectionLivre() {
		super();
	}

	/**
	 * Constructeur par copie. L'objet instancié sera différent de celui
	 * référencé par le paramètre.
	 * 
	 * @param pCollectionLivre
	 */
	public CollectionLivre(CollectionLivre pCollectionLivre) {
		super();
		if (pCollectionLivre != null) {
			Iterator<Livre> it = pCollectionLivre.iterator();
			while (it.hasNext()) {
				this.add((Livre) it.next());
			}
		}
	}

	// ================Methodes================
	@Override
	public synchronized String toString() {
		Iterator<Livre> it = this.iterator();
		String resultat = "";
		while (it.hasNext()) {
			Livre livre = (Livre) it.next();
			resultat += livre.toString() + "\n";
		}
		return resultat;
	}
}
