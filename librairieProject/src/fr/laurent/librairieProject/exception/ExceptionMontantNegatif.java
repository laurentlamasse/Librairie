package fr.laurent.librairieProject.exception;

public class ExceptionMontantNegatif extends Exception {
	
	public ExceptionMontantNegatif()
	{
		super("Le montant d'un livre ne peut pas etre negatif");
	}
}
