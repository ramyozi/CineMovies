package fr.diginamic;

import fr.diginamic.menu.Menu;

/**
 * Classe principale de l'application, point d'entrée du programme. Crée une
 * instance de la classe Menu pour lancer le menu interactif.
 */
public class App {
	/**
	 * Point d'entrée du programme. Instancie la classe Menu et lance le menu
	 * interactif.
	 *
	 * @param args Les arguments en ligne de commande (non utilisés dans cette
	 *             application).
	 */
	public static void main(String[] args) {
		Menu m = new Menu();
		m.afficherMenu();
	}
}
