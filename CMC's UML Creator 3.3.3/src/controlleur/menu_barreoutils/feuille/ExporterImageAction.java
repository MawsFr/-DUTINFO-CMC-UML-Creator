package controlleur.menu_barreoutils.feuille;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import vue.CMCUmlView;
import vue.editeur.feuille.GestionnaireFeuille;
import vue.editeur.feuille.OngletFeuilleDessin;
import modele.EditeurUmlModele;
import controlleur.AbstractMenuAction;

public class ExporterImageAction extends AbstractMenuAction{

	
	public ExporterImageAction(EditeurUmlModele modele, GestionnaireFeuille feuilles) {
		super(modele, "Image",  "image_obj.gif", "Exporte la feuille en image");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//
//		    int w = panel.getWidth();
//		    int h = panel.getHeight();
//		    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//		    Graphics2D g = bi.createGraphics();
//		    panel.paint(g);
//		    return bi;
//		}
		BufferedImage image = ((OngletFeuilleDessin) CMCUmlView.getInstance().getEditeur().getGestionnaireFeuilles().getSelectedComponent()).getFeuille().getImage();
		
	}

}
