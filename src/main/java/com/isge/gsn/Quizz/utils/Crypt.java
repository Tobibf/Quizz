package com.isge.gsn.Quizz.utils;

import java.util.ArrayList;

/**
 * @ author Ulrich TRAORE version 1.0.
 */
public class Crypt {
	private static String enc = "";
	private static String dec = "";
	private static String t[];
	private static String tab[];
	static ArrayList<String> list_debut = new ArrayList<String>();
	static ArrayList<String> list_fin = new ArrayList<String>();

	private final static String fin[] = { "c", "d", "e", "f", "+", "g", "h", "i", "o", "p", "7", "8", "9", "q", "r",
			"s", "*", "t", "u", "v", "w", "x", "y", "z", "@", " ", "&", "4", "5", "6", "-", "a", "b", "A", "B", "C",
			"0", "1", "2", "D", "E", "F", "3", "G", "j", "k", "l", "m", "n", "H", "I", "J", "K", "L", "M", "N", "O",
			"U", "V", "W", "X", "Y", "Z", "é", "è", "à", "P", "Q", "R", "S", "T", "â", "ù", "ê", "ç" };
	
	private final static int nb = fin.length;
	static int tamp = 0;


	public static String encryption(String mot) {
		int cpt = 0;
		listInput();
		listOutput();

		t = mot.split("");

		cpt = t.length;
		tab = new String[cpt];
		for (int i = 0; i < cpt; i++) {
			for (int j = 0; j < nb; j++) {

				if (list_fin.get(j).contentEquals(t[i])) {
					tab[i] = list_debut.get(j);
					enc = enc + tab[i];

				}
			}
		}
		return enc;
	}

	public static String decryption(String mot) {
		listInput();
		listOutput();

		int cpt = 0;

		t = mot.split("");

		cpt = t.length;
		tab = new String[cpt];
		for (int i = 0; i < cpt; i++) {
			for (int j = 0; j < nb; j++) {
				if (list_debut.get(j).contentEquals(t[i])) {

					tab[i] = list_fin.get(j);
				
					dec = dec + tab[i];
				}
			}
		}

		return dec;
	}

	public static void listInput() {
		list_debut.clear();
		t = null;
		tamp = 0;
		int ta = 0;
		for (int i = 0; i < nb; i++) {
			tamp = i + 7;
			if (tamp >= nb) {
				list_debut.add(fin[ta]);
				ta++;
			}

			else {
				list_debut.add(fin[tamp]);
			}
		}
	}

	public static void listOutput() {
		list_fin.clear();

		for (int i = 0; i < nb; i++) {

			list_fin.add(fin[i]);

		}
	}

}
