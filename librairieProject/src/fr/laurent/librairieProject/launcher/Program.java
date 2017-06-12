package fr.laurent.librairieProject.launcher;

import java.util.Scanner;

import fr.laurent.librairieProject.domaine.Librairie;
import fr.laurent.librairieProject.presentation.LibrairieMenu;
import fr.laurent.librairieProject.service.ClienteleService;

/**
 * 
 * Classe principale dans laquelle se trouve la fonction main
 * @author Laurent
 *
 */
public class Program {

	/**
	 * Point d'entrée du programme
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LibrairieMenu().useMenu();
	}
	
	

}
