package vue.entites.propriete;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;
import vue.entites.EntiteFigure;
import vue.entites.propriete.listModel.MethodesListModel;
import vue.entites.propriete.listModel.ParametresListModel;
import vue.explorateur.ExplorateurProjet;
import controlleur.entites.AjoutCaracAction;
import controlleur.entites.ModifierCaracAction;

public class OngletMethodesPropriete extends JPanel{

	private ProprieteEntite proprietes;
	private JPanel pnlListe;
	private EntiteFigure entite;
	private JLabel lblMethodes;
	private JList<MethodeEntite> listeMethodes;
	private JScrollPane scrollpane, scrollpaneParametres;
	private MethodesListModel methodesListModel;
	private JLabel visi, typeDeRetour, nom;

	private JList<ParametreMethode> listeParametres;
	private ParametresListModel parametresListModel;

	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnSupprimerParametre;
	private JButton btnModifierParametre;



	public OngletMethodesPropriete(ProprieteEntite proprieteEntite, EntiteFigure entite2) {
		this.entite = entite2;
		this.proprietes = proprieteEntite;

		methodesListModel = new MethodesListModel(entite);
		listeMethodes = new JList<MethodeEntite>(methodesListModel);
		scrollpane = new JScrollPane(listeMethodes);
		//System.out.println(listeMethodes.getModel());

		pnlListe = new JPanel();
		pnlListe.setLayout(new BoxLayout(pnlListe, BoxLayout.PAGE_AXIS));
		TitledBorder general = new TitledBorder("Méthodes de l'entité "+entite.getEntite().getNom());
		pnlListe.setBorder(general);

		listeMethodes.setAlignmentX(SwingConstants.CENTER);


		JPanel pnlBoutons=new JPanel();
		pnlBoutons.setLayout(new BoxLayout(pnlBoutons, BoxLayout.X_AXIS));
		btnAjouter=new JButton(new AjoutCaracAction(entite,"METHODE"));
		btnModifier=new JButton(new ModifierCaracAction(proprieteEntite, "METHODE"));
		btnSupprimer=new JButton("Supprimer");
		pnlBoutons.setAlignmentX(SwingConstants.CENTER);

		pnlBoutons.add(btnAjouter);
		pnlBoutons.add(btnModifier);
		pnlBoutons.add(btnSupprimer);

		visi = new JLabel();
		JLabel lblVisi = new JLabel("Visibilité : ");
		JPanel pnlVisi = new JPanel();
		pnlVisi.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlVisi.add(lblVisi);
		pnlVisi.add(visi);

		typeDeRetour = new JLabel();
		JLabel lblTypeRetour = new JLabel("Type de retour : ");
		nom = new JLabel();
		JLabel lblNom = new JLabel("Nom : ");
		JLabel lblParametres = new JLabel("Paramètres : ");

		JPanel pnlTypeRetour = new JPanel();
		pnlTypeRetour.add(lblTypeRetour);
		pnlTypeRetour.add(typeDeRetour);
		pnlTypeRetour.setLayout(new FlowLayout(FlowLayout.LEADING));

		JPanel pnlNom = new JPanel();
		pnlNom.add(lblNom);
		pnlNom.add(nom);
		pnlNom.setLayout(new FlowLayout(FlowLayout.LEADING));

		listeParametres = new JList<ParametreMethode>();
		scrollpaneParametres = new JScrollPane(listeParametres);

		JPanel pnlProprietes = new JPanel();
		TitledBorder proprietes = new TitledBorder("Propriètés");
		pnlProprietes.add(pnlVisi);
		pnlProprietes.add(pnlTypeRetour);
		pnlProprietes.add(pnlNom);
		pnlProprietes.add(lblParametres);
		pnlProprietes.add(scrollpaneParametres);
		pnlProprietes.setLayout(new GridLayout(0,1));
		pnlProprietes.setBorder(proprietes);

		listeMethodes.addListSelectionListener(new ListSelectionListener() {

			//private ParametresListModel parametresListModel;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(listeMethodes.getSelectedIndex() >= 0) {

					if(!btnModifier.isEnabled()) {
						btnModifier.setEnabled(true);
					}

					if(!btnSupprimer.isEnabled()) {
						btnSupprimer.setEnabled(true);
					}

					//On recupere la methode selectionné
					MethodeEntite methode = methodesListModel.getElementAt(listeMethodes.getSelectedIndex());
					
					
					//on set les JLabel avec les bvaleur de la méthode
					typeDeRetour.setText(methode.getType().toString());
					nom.setText(methode.getNom().toString());
					visi.setText(methode.getVisibilite().toString());

					
					//on set le listModel qui copie les parametre 
					//de la methode selectionne avec le JLIst des parametre en bas a droite
					parametresListModel = new ParametresListModel(methode);
					listeParametres.setModel(parametresListModel);
				} else {
					if(btnModifier.isEnabled()) {
						btnModifier.setEnabled(false);
					}

					if(btnSupprimer.isEnabled()) {
						btnSupprimer.setEnabled(false);
					}

					visi.setText("");
					nom.setText("");
					typeDeRetour.setText("");

					//TODO faire en sorte que les element de la jlist de parametre se vide quand ya rien de selectionné
				}
			}
		});

		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MethodeEntite methodeASupprimer = methodesListModel.getElementAt(listeMethodes.getSelectedIndex());
				visi.setText("");
				nom.setText("");
				typeDeRetour.setText("");
				entite.getFeuille().getFenetre(entite).getOngletMethodes().getParamListModel().supprimerTousLesParametres();
				entite.getFeuille().getFenetre(entite).getOngletMethodes().getListModel().supprimerMethodeTemporaire(methodeASupprimer);	
				
				if(methodesListModel.getSize() == 0) {
					if(btnModifier.isEnabled()) {
						btnModifier.setEnabled(false);
					}

					if(btnSupprimer.isEnabled()) {
						btnSupprimer.setEnabled(false);
					}

				}
			
			}
		});
		btnModifier.setEnabled(false);
		btnSupprimer.setEnabled(false);
		this.setLayout(new /*BorderLayout()*/GridLayout(1,0));
		pnlListe.add(scrollpane);
		pnlListe.add(pnlBoutons);
		this.add(pnlListe/*, BorderLayout.WEST*/);
		this.add(pnlProprietes/*, BorderLayout.CENTER*/);


	}

	public MethodesListModel getListModel(){
		return methodesListModel;
	}

	public ParametresListModel getParamListModel() {
		return parametresListModel;
	}

	/**
	 * @return the entite
	 */
	public EntiteFigure getEntite() {
		return entite;
	}

	/**
	 * @return the listeMethodes
	 */
	public JList<MethodeEntite> getListeMethodes() {
		return listeMethodes;
	}

	/**
	 * @return the btnModifier
	 */
	public JButton getBtnModifier() {
		return btnModifier;
	}

	/**
	 * @return the btnSupprimer
	 */
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	


}





