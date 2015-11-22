package controlleur.menu_barreoutils.feuille;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import modele.EditeurUmlModele;
import vue.BarreOutils;

public class AjouterEntiteMenuDragAction extends MouseAdapter {

	private JPopupMenu menuEntite;
	private BarreOutils toolbar;

	public AjouterEntiteMenuDragAction(EditeurUmlModele modele, JPopupMenu menu, BarreOutils toolbar) {
		this.toolbar = toolbar;
		this.menuEntite = menu;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		toolbar.setBtnClasseDragged(false);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		toolbar.setBtnClasseDragged(false);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		if(!toolbar.isBtnClasseDragged()) {
			toolbar.setBtnClasseDragged(true);
			Component c = (Component) arg0.getSource();
			Point p = c.getLocationOnScreen();

			menuEntite.show(c, 0, 0);
			menuEntite.setLocation(p.x, p.y+c.getHeight());
		}		
	}

}
