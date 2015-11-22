package controlleur.menu_barreoutils.fichier;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.EditeurUmlModele;
import vue.CMCUmlView;
import vue.editeur.feuille.OngletFeuilleDessin;
import controlleur.AbstractMenuAction;

public class ExporterProjetImageAction extends AbstractMenuAction {

	public ExporterProjetImageAction(EditeurUmlModele modele) {
		super(modele, "Image", "xml_perspective.gif", "Exporter le projet en un fichier JPG");

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		OngletFeuilleDessin o = (OngletFeuilleDessin) CMCUmlView.getInstance().getEditeur().getGestionnaireFeuilles().getSelectedComponent();
		if(o != null) {
			BufferedImage bufferedImage = o.getFeuille().getImage();
			File outputfile = new File(o.getFeuille().getFeuilleModele().getNom() + ".jpg");
			CMCUmlView.getInstance().getBarreStatut().afficherErreur("L'image a été créé dans le meme repertoire que le logiciel");
			try {
				ImageIO.write(bufferedImage, "jpg", outputfile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
