package controlleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;

import vue.CMCUmlView;

public class DescriptionBoutonAdapter extends MouseAdapter{
	
	@Override
	public void mouseEntered(MouseEvent e) {
		AbstractButton bouton = (AbstractButton) e.getComponent();
		CMCUmlView.getInstance().getBarreStatut().afficherStatut(bouton.getToolTipText());
	}
//	
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		CMCUmlView.getInstance().getBarreStatut().afficherStatut("");
//	}
//	
}
