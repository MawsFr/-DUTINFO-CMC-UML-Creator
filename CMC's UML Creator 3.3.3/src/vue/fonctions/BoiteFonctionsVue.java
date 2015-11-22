package vue.fonctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BoiteFonctionsVue {

	public static final int ERREUR = 1;
	public static final int INFO = 2;
	public static final int WARNING = 3;
	public static final int QUESTION = 4;

	public static final String iconesLocation = "/vue/icones/";


	public static ImageIcon getIcone(String nom, String desc) {

		URL url = BoiteFonctionsVue.class.getResource(iconesLocation + nom);

		return (url == null) ? null : new ImageIcon(url, desc);
	}

//	public JOptionPane creerDialog(int type, String titre, String texte) {
//		return new JOptionPane();
//	}

	public static String verifierIcone(ImageIcon i, String desc) {
		return ((i == null) ? desc : "");
	}

	// TODO Methodes pour afficher des fenetre ici genre JDialog d'erreur etc

}
