/**
 * 
 * 
 * 
 * Maws
 * Barre de statut
 * 
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.FeuilleMode;

public class BarreStatut extends JPanel {
	
	private JLabel statut;
	private JLabel mode;
	
	public BarreStatut() {
		this.statut = new JLabel();
		
		this.setLayout(new BorderLayout());
		this.add(statut, BorderLayout.WEST);
		this.mode = new JLabel();
		this.add(mode, BorderLayout.EAST);
		
	}
	
	public void afficherStatut(String statut) {
		this.statut.setForeground(Color.BLACK);
		this.statut.setText(statut);
		
	}
	
	public void afficherErreur(String erreur) {
		this.statut.setForeground(Color.RED);
		this.statut.setText(erreur);
	}
	
	public void effacerStatut() {
		this.statut.setText("");
	}

	public void afficherMode(FeuilleMode mode2) {
		this.mode.setText("Mode : " + mode2);
		
	}

}
