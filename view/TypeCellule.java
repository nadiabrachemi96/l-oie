package view;
/*
	Simple (Color.red),  
	Avance_3(Color.green) ,
	Avance_7 (Color.yellow),  
	Recul_3 (Color.pink), 
	Recul_7 (Color.black),
	X1(Color.cyan);
	
	private Color couleur;*/

import java.awt.Color;

public enum TypeCellule {
//si il tombe sur la case 6 il ira a la douze
	Pont(Color.red),
//case 19 il y reste 2 tours 
	Hotel(Color.black),
//puit attends qu'on le reléve 
	Puits(Color.yellow),
//case 42->30
	Retourne(Color.pink),
//58->0
	Mort(Color.blue),
//63
	Gagne(Color.yellow),
//tjr
	Normal(Color.blue);
	private TypeCellule(Color couleur) {
		this.couleur = couleur;
	}

	private Color couleur;

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

}
