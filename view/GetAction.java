package view;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class GetAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gui fenetre;
	
	public GetAction(Gui fenetre, String texte){
		super(texte);
		
		this.fenetre = fenetre;
	}
	//l'action qui se passe lorsqu'on appuie sur le bouton
	public void actionPerformed(ActionEvent e) { 
		String texteUtilisateur = fenetre.getTextField().getText();
		fenetre.getLabel().setText(texteUtilisateur);
	} 
}