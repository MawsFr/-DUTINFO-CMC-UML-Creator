package vue.entites.propriete;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.entites.caracteristiques.CaracteristiqueAccessible;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import vue.entites.EntiteFigure;
import vue.entites.propriete.listModel.AttributsListModel;
import controlleur.entites.AjoutCaracAction;
import controlleur.entites.ModifierCaracAction;

public class OngletAttributsPropriete extends JPanel {

	private ProprieteEntite proprietes;
	private JPanel pnlListe, pnlProprietes;
	private final EntiteFigure entite;
	private JLabel lblAttributs;

	private JLabel lblVisi, lblType, lblNom,lblValeur,lblEstFinal,lblEstStatic;
	private JLabel visi, type, nom, valeur,estFinal,estStatic;
	private JPanel pnlVisi, pnlType, pnlNom ,pnlValeur, pnlFinal;

	private JList<AttributEntite> listeAttributs;
	private JScrollPane scrollpane;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSuppimer;
	private JPanel panelAttributs;

	private AttributsListModel attributListModel;
	private JPanel pnlStatic;
	


	public OngletAttributsPropriete(ProprieteEntite prop, EntiteFigure entite2) {
		this.entite = entite2;
		this.proprietes = prop;
		lblAttributs = new JLabel("Liste des attributs");
		attributListModel = new AttributsListModel(entite);
		listeAttributs = new JList<AttributEntite>(attributListModel);

		panelAttributs=new JPanel();
		panelAttributs.setLayout(new BoxLayout(panelAttributs,BoxLayout.LINE_AXIS));
		panelAttributs.add(lblAttributs);


		// listeAttributs.setVisibleRowCount(5);
		scrollpane = new JScrollPane(listeAttributs);

		pnlListe = new JPanel();
		pnlListe.setLayout(new BoxLayout(pnlListe, BoxLayout.PAGE_AXIS));


		//lblAttributs.setAlignmentX(SwingConstants.CENTER);
		listeAttributs.setAlignmentX(SwingConstants.CENTER);

		pnlProprietes = new JPanel();
		lblVisi = new JLabel("VisibilitÃ© : ");
		lblType = new JLabel("Type : ");
		lblNom = new JLabel("Nom : ");

		lblValeur=new JLabel("Valeur :");
		lblEstStatic=new JLabel("Est Static :");
		lblEstFinal=new JLabel("Est Final :");

		// visi = new
		// JLabel(((AttributEntite)listeModel.getElementAt(listeAttributs.getSelectedIndex())).getVisibilite().toString());
		// type = new
		// JLabel(((AttributEntite)listeModel.getElementAt(listeAttributs.getSelectedIndex())).getType().toString());
		// nom = new
		// JLabel(((AttributEntite)listeModel.getElementAt(listeAttributs.getSelectedIndex())).getNom().toString());

		visi = new JLabel();
		type = new JLabel();
		nom = new JLabel();
		valeur=new JLabel();
		estFinal=new JLabel();
		estStatic=new JLabel();



		pnlVisi = new JPanel();
		pnlVisi.add(lblVisi);
		pnlVisi.add(visi);
		pnlVisi.setLayout(new FlowLayout(FlowLayout.LEADING));
		//		pnlVisi.setLayout(new BoxLayout(pnlVisi, BoxLayout.X_AXIS));

		pnlType = new JPanel();
		pnlType.add(lblType);
		pnlType.add(type);
		//		pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.X_AXIS));
		pnlType.setLayout(new FlowLayout(FlowLayout.LEADING));

		pnlNom = new JPanel();
		pnlNom.add(lblNom);
		pnlNom.add(nom);
		//		pnlNom.setLayout(new BoxLayout(pnlNom, BoxLayout.X_AXIS));
		pnlNom.setLayout(new FlowLayout(FlowLayout.LEADING));

		pnlValeur = new JPanel();
		pnlValeur.add(lblValeur);
		pnlValeur.add(valeur);
		//		pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.X_AXIS));
		pnlValeur.setLayout(new FlowLayout(FlowLayout.LEADING));

		pnlFinal= new JPanel();
		pnlFinal.add(lblEstFinal);
		pnlFinal.add(estFinal);
		pnlFinal.setLayout(new FlowLayout(FlowLayout.LEADING));

		pnlStatic= new JPanel();
		pnlStatic.add(lblEstStatic);
		pnlStatic.add(estStatic);
		//		pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.X_AXIS));
		pnlStatic.setLayout(new FlowLayout(FlowLayout.LEADING));


		//		pnlProprietes.setLayout(new GridLayout(0,1));
		pnlProprietes.setLayout(new BoxLayout(pnlProprietes, BoxLayout.PAGE_AXIS));
		pnlProprietes.add(pnlNom);
		pnlProprietes.add(pnlVisi);
		pnlProprietes.add(pnlType);
		pnlProprietes.add(pnlValeur);
		pnlProprietes.add(pnlFinal);
		pnlProprietes.add(pnlStatic);


		//		

		//		pnlProprietes.setLayout(new BoxLayout(pnlProprietes,
		//				BoxLayout.Y_AXIS));
		//		pnlProprietes.setLayout(new SpringLayout());


		JPanel pnlBoutons = new JPanel();
		pnlBoutons.setLayout(new BoxLayout(pnlBoutons, BoxLayout.X_AXIS));
		btnAjouter = new JButton(new AjoutCaracAction(entite, "ATTRIBUT"));
		btnModifier = new JButton(new ModifierCaracAction(prop, "ATTRIBUT"));
		btnSuppimer = new JButton("Supprimer");

		pnlBoutons.setAlignmentX(SwingConstants.CENTER);

		pnlBoutons.add(btnAjouter);
		pnlBoutons.add(btnModifier);
		pnlBoutons.add(btnSuppimer);

		listeAttributs.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(listeAttributs.getSelectedIndex() >=0) { //si un attribut a bien été selectionné
					
					//on réactive les bouton modifier et supprimer
					if(!btnModifier.isEnabled()) {
						btnModifier.setEnabled(true);
					}
					
					if(!btnSuppimer.isEnabled()) {
						btnSuppimer.setEnabled(true);
					}
					
					//on set les jlabel au valeur de l'attribut selectionné
					visi.setText((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).getVisibilite().toString());
					type.setText((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).getType());
					nom.setText((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).getNom());
					valeur.setText((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).getValeur());
					estFinal.setText(((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).estFinal())? "Vrai" : "Faux");
					estStatic.setText(((attributListModel.getElementAt(listeAttributs
							.getSelectedIndex())).estStatique())? "Vrai" : "Faux");

				} else { //sinon si aucun attribut n'est selectionné
					
					//on desactive les bouton
					if(btnModifier.isEnabled()) {
						btnModifier.setEnabled(false);
					}
					
					if(btnSuppimer.isEnabled()) {
						btnSuppimer.setEnabled(false);
					}
					
					visi.setText("");
					type.setText("");
					nom.setText("");
					valeur.setText("");
					estFinal.setText("");
					estStatic.setText("");
				}
			}

		});
		
		btnSuppimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AttributEntite attributASupprimer = attributListModel.getElementAt(listeAttributs.getSelectedIndex());
				visi.setText("");
				nom.setText("");
				type.setText("");
				entite.getFeuille().getFenetre(entite).getOngletAttributs().getListAttribut().supprimerAttributTemporaire(attributASupprimer);
				
				if(attributListModel.getSize() == 0) {
					if(btnModifier.isEnabled()) {
						btnModifier.setEnabled(false);
					}

					if(btnSuppimer.isEnabled()) {
						btnSuppimer.setEnabled(false);
					}

				}
			}
		});
		
		btnModifier.setEnabled(false);
		btnSuppimer.setEnabled(false);
		this.setLayout(new BorderLayout());
		pnlListe.add(lblAttributs);
		pnlListe.add(scrollpane);
		pnlListe.add(pnlBoutons);



		this.add(pnlListe, BorderLayout.WEST);
		this.add(pnlProprietes, BorderLayout.CENTER);
		this.add(panelAttributs,BorderLayout.NORTH);

	}
	public AttributsListModel getListAttribut(){
		return attributListModel;
	}



	public JList<AttributEntite> getListeAttributs() {
		return listeAttributs;
	}
	
	public EntiteFigure getEntite() {
		return this.entite;
		
	}
}
