package controlleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import modele.EditeurUmlModele;
import vue.fonctions.BoiteFonctionsVue;

public abstract class AbstractMenuAction extends AbstractAction {

	protected EditeurUmlModele modele;
	
	
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String tooltip) {
		super(texte);
		this.modele = modele;
		putValue(SHORT_DESCRIPTION, tooltip);
	}
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String icone, String tooltip) {
		super(texte, BoiteFonctionsVue.getIcone(icone, texte));
		this.modele = modele;
		putValue(SHORT_DESCRIPTION, tooltip);
	}
	
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String icone, String tooltip, Integer mnemonic) {
		this(modele, texte, icone, tooltip);
		putValue(MNEMONIC_KEY, mnemonic);
	}
	
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String icone, String tooltip, KeyStroke raccourci) {
		this(modele, texte, icone, tooltip);
		putValue(ACCELERATOR_KEY, raccourci);
	}
	
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String icone, String tooltip, KeyStroke raccourci, Integer mnemonic) {
		this(modele, texte, icone, tooltip, raccourci);
		putValue(MNEMONIC_KEY, mnemonic);
	}
	
	public AbstractMenuAction(EditeurUmlModele modele, String texte, String tooltip, KeyStroke keyStroke) {
		this(modele, texte, tooltip);
		putValue(ACCELERATOR_KEY, keyStroke);
	}
	public AbstractMenuAction(EditeurUmlModele modele, String texte) {
		super(texte);
		this.modele = modele;
	}
	@Override
	public abstract void actionPerformed(ActionEvent e);

}
