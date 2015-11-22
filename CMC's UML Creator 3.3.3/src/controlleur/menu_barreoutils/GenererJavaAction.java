package controlleur.menu_barreoutils;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import vue.fonctions.BoiteFonctionsVue;
import modele.EditeurUmlModele;
import modele.fonctions.BoiteFonctionsModele;
import controlleur.AbstractMenuAction;

public class GenererJavaAction extends AbstractMenuAction{

	public GenererJavaAction(EditeurUmlModele modele) {
		super(modele, "Code Java", "Génère le code java du projet selectinné", KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showSaveDialog(SwingUtilities.getWindowAncestor((Component) e.getSource())) == JFileChooser.APPROVE_OPTION) {
			BoiteFonctionsModele.exporterJava(fileChooser.getSelectedFile(), modele.getProjetSelectionne());
		}
	}

}
