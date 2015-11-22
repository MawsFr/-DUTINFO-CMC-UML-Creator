package modele.fonctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Properties;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;
import modele.ProjetModele;
import modele.entites.EntiteModele;

public class BoiteFonctionsModele {

	public static final String proprietesLocation = "proprietes" + File.separator;
	public static final String projetsLocation = "projets" + File.separator;

	public static void sauvegarderProprietes(String nomFichier, Properties propriete) throws IOException, URISyntaxException {
		File prop = new File(proprietesLocation + nomFichier + ".prop");
		if (!prop.exists()) {
			prop.setWritable(true);
			if (Files.notExists(Paths.get(proprietesLocation)) && !prop.getParentFile().mkdir()) {
				throw new IOException("Impossible de créer le repertoire du fichier");
			}
			
			try {
				prop.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			propriete.storeToXML(new FileOutputStream(prop),
					"Fichier de propriete : " + nomFichier);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Properties chargerProprietes(String nomFichier) throws IOException {

		Properties p = new Properties();

		File prop = new File(proprietesLocation + nomFichier + ".prop");
		if (prop.exists()) {
			try {
				p.loadFromXML(new FileInputStream(prop));
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new IOException("Le fichier n'existe pas !");
		}

		return p;

	}
	
	public static void sauvegarderProjet(ProjetModele projet) throws IOException {
		
		File fichier = new File(projetsLocation + projet.getNom() + ".cmcproject");
		if (!fichier.exists()) {
			fichier.setWritable(true);
			if (Files.notExists(Paths.get(projetsLocation)) && !fichier.getParentFile().mkdir()) {
				throw new IOException("Impossible de créer le repertoire du fichier");
			}
			
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			
			oos.writeObject(projet);
			
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	public static ProjetModele ouvrirProjet(EditeurUmlModele modele, File fichier) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			
			ProjetModele projet1 = (ProjetModele) ois.readObject();
			ois.close();
			
			return projet1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
		
		
		
		
		
		
	}

	public static void exporterJava(File dossier, ProjetModele projet) {
		FileWriter fw = null;
		for(Iterator<FeuilleDessinModele> feuilles = projet.getFeuilles().iterator(); feuilles.hasNext();) {
			FeuilleDessinModele feuille = feuilles.next();
			for(Iterator<EntiteModele> it = feuille.getEntites().iterator(); it.hasNext();) {
				EntiteModele entite = it.next();
                    try {
						File f = new File(dossier.getAbsolutePath() + File.separator + projet.getNom() + File.separator + feuille.getNom());
						f.mkdirs();
						
						f = new File(f.getAbsoluteFile() + File.separator + entite.getNom()+ ".java");
						if(!f.exists()) {
							f.createNewFile();
						}
						
						fw = new FileWriter(f);
		                if(fw != null) {
		                    fw.write(entite.toStringJava());
		                    fw.close();
		                }
					} catch (IOException e) {
						e.printStackTrace();
					}
                    
			}
		}
	}

}
