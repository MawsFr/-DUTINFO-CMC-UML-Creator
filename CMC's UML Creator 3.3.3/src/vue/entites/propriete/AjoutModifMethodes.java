package vue.entites.propriete;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import modele.entites.caracteristiques.methode.ParametreMethode;
import vue.entites.EntiteFigure;
import vue.entites.propriete.listModel.MethodeEnCreationListModel;



public class AjoutModifMethodes extends JDialog{

	private JLabel lblNom;
	private JTextField txtNom;

	private JLabel lblType;
	private JTextField txtType;

	private JLabel lblVisibilite;

	private ButtonGroup groupVisibilite;
	private JRadioButton radPublic;
	private JRadioButton radProtected;
	private JRadioButton radPrivate;
	private JRadioButton radDefault;

	private ButtonGroup group;
	private JRadioButton radFinal;

	private JLabel lblParamType;
	private JTextField txtParamType;
	private JLabel lblParamNom;
	private JTextField txtParamNom;

	private JButton btAjouter;

	private JCheckBox checkabs;

	private JTextField txtException;
	private JButton btException;

	private JButton btEnregis, supprimer;
	private JButton btAnnuler;

	private EntiteFigure entite;
	private MethodeEntite methodeACreer;
	private MethodeEnCreationListModel listModel;
	private boolean modif;
	private JList<ParametreMethode> listeParametreEnCreation;

	public AjoutModifMethodes(final EntiteFigure entite) {
		this.setTitle("Ajout de Méthode");
		this.entite = entite;
		this.setMinimumSize(new Dimension(300,340));
		this.setLocationRelativeTo(null);

		methodeACreer = new MethodeEntite();

		listModel = new MethodeEnCreationListModel(methodeACreer);

		lblNom= new JLabel("Nom :");
		txtNom =new JTextField();

		lblType=new JLabel("Type de Retour : ");
		txtType=new JTextField();

		lblVisibilite =new JLabel("Visibilité : ");

		group = new ButtonGroup();
		radPublic =new JRadioButton("Public");
		radProtected =new JRadioButton("Protected");
		radPrivate =new JRadioButton("Private");
		radDefault = new JRadioButton("Default");
		radPublic.setActionCommand("public");
		radPrivate.setActionCommand("private");
		radProtected.setActionCommand("protected");
		radDefault.setActionCommand("default");
		group.add(radPublic);
		group.add(radPrivate);
		group.add(radProtected);
		group.add(radDefault);

		JPanel pnlAbstract = new JPanel();
		checkabs = new JCheckBox("Abstract : ");


		JPanel panPropriete =new JPanel();
		TitledBorder propriete = new TitledBorder("Propriétés");
		panPropriete.setLayout(new GridLayout(3,3));
		panPropriete.setBorder(propriete);
		panPropriete.add(lblNom);
		panPropriete.add(txtNom);
		panPropriete.add(lblType);
		panPropriete.add(txtType);
		panPropriete.add(checkabs);
		this.getContentPane().add(panPropriete);

		JPanel panVisibilite =new JPanel();
		TitledBorder visi = new TitledBorder("Visibilité");
		panVisibilite.setLayout(new BoxLayout(panVisibilite, BoxLayout.X_AXIS));
		panVisibilite.setBorder(visi);
		panVisibilite.add(radPublic);
		panVisibilite.add(radProtected);
		panVisibilite.add(radPrivate);
		panVisibilite.add(radDefault);
		radPublic.setSelected(true);
		panVisibilite.add(Box.createVerticalStrut(40));



		TitledBorder parametres = new TitledBorder("Paramètres");
		JPanel panParametre = new JPanel();
		JPanel panListeParametre= new JPanel();
		panListeParametre.setLayout(new GridLayout(0,1));
		listeParametreEnCreation = new JList<ParametreMethode>(listModel);
		JScrollPane scrollpane = new JScrollPane(listeParametreEnCreation);
		panListeParametre.add(scrollpane);
		//panParametre.setLayout(new GridLayout(2,2));
		panParametre.setLayout(new BoxLayout(panParametre, BoxLayout.Y_AXIS));
		panParametre.setBorder(parametres);

		JPanel pnlType = new JPanel();
		pnlType.setLayout(new GridLayout(0,1));
		lblParamType =new JLabel("Type : ");
		txtParamType =new JTextField();
		pnlType.add(lblParamType);
		pnlType.add(txtParamType);

		JPanel pnlNom = new JPanel();
		pnlNom.setLayout(new GridLayout(0,1));
		lblParamNom =new JLabel("Nom : ");
		txtParamNom =new JTextField();
		pnlNom.add(lblParamNom);
		pnlNom.add(txtParamNom);

		//		listeParametreEnCreation.addListSelectionListener(new ListSelectionListener(){
		//
		//			@Override
		//			public void valueChanged(ListSelectionEvent arg0) {
		//				if(listeParametreEnCreation.getSelectedIndex() >= 0) {
		//					ParametreMethode parametre = listModel.getElementAt(listeParametreEnCreation.getSelectedIndex());
		//					
		//				}
		//			}
		//
		//		});

		btAjouter =new JButton("Ajouter");
		btAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ParametreMethode parametre = new ParametreMethode(txtParamType.getText(), txtParamNom.getText());
				methodeACreer.ajouterParametre(parametre);

				//System.out.println(entite.getFeuille().getFenetre(entite).getOngletMethodes().getParamListModel());
				txtParamType.setText("");
				txtParamNom.setText("");

			}
		});
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				methodeACreer.retirerParametre(listModel.getElementAt(listeParametreEnCreation.getSelectedIndex()));

			}
		});

		panParametre.add(panListeParametre);
		panParametre.add(pnlNom);
		panParametre.add(pnlType);

		JPanel panBtAjouter=new JPanel();
		panBtAjouter.add(btAjouter);
		panBtAjouter.add(supprimer);

		TitledBorder p=new TitledBorder("Exceptions");
		JPanel panException =new JPanel();
		panException.setLayout(new BoxLayout(panException,BoxLayout.X_AXIS));

		txtException=new JTextField();
		panException.add(txtException);
		btException=new JButton("Ajouter");
		panException.add(btException);
		panException.setBorder(p);

		JPanel panEnregistrement =new JPanel();
		panEnregistrement.setLayout(new BoxLayout(panEnregistrement, BoxLayout.X_AXIS));
		btEnregis =new JButton("Enregistrer");
		btEnregis.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				methodeACreer.setNom(txtNom.getText());
				methodeACreer.setType(txtType.getText());
				methodeACreer.setAbstraite(checkabs.isSelected());

				if(radPublic.isSelected()) { 
					methodeACreer.setVisibilite(Visibilite.PUBLIC); 
				}
				else if(radProtected.isSelected()) { 
					methodeACreer.setVisibilite(Visibilite.PROTECTED);
				}
				else if(radPrivate.isSelected()) { 
					methodeACreer.setVisibilite(Visibilite.PRIVATE);
				}
				else { 
					methodeACreer.setVisibilite(Visibilite.DEFAULT);
				}
				//			MethodeEntite methode = new MethodeEntite(txtRetour.getText(), txtNom.getText(),false,entite.getFeuille().getFenetre(entite).getOngletMethodes().getParamListModel().getListeCopie() , false);
				if(!modif) {
					entite.getFeuille().getFenetre(entite).getOngletMethodes().getListModel().ajouterMethodeTemporaire(methodeACreer);
					if(entite.getFeuille().getFenetre(entite).getOngletMethodes().getListeMethodes().getSelectedIndex()>=0) {
						if(!entite.getFeuille().getFenetre(entite).getOngletMethodes().getBtnModifier().isEnabled()) {
							entite.getFeuille().getFenetre(entite).getOngletMethodes().getBtnModifier().setEnabled(true);
						}

						if(!entite.getFeuille().getFenetre(entite).getOngletMethodes().getBtnSupprimer().isEnabled()) {
							entite.getFeuille().getFenetre(entite).getOngletMethodes().getBtnSupprimer().setEnabled(true);
						}
					}
				} else {
					entite.getFeuille().getFenetre(entite).getOngletMethodes().getListeMethodes().setModel(entite.getFeuille().getFenetre(entite).getOngletMethodes().getListModel());
				}
				dispose();
			}
		});

		btAnnuler =new JButton("Annuler");
		btAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		panEnregistrement.add(btEnregis);
		panEnregistrement.add(btAnnuler);
		panEnregistrement.add(Box.createVerticalStrut(40));

		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.PAGE_AXIS));
		c.add(panPropriete);
		c.add(panVisibilite);
		c.add(panParametre);
		c.add(panBtAjouter);
		c.add(panException);
		c.add(panEnregistrement);
		pack();
		setModal(true);


	}

	public AjoutModifMethodes(EntiteFigure entite, MethodeEntite methode) {
		this(entite);
		modif = true;
		this.methodeACreer = methode;

		this.txtNom.setText(methode.getNom());
		this.txtType.setText(methode.getType());

		switch(methode.getVisibilite()) {
		case DEFAULT:
			this.radDefault.setSelected(true);
			break;
		case PRIVATE:
			this.radPrivate.setSelected(true);
			break;
		case PROTECTED:
			this.radProtected.setSelected(true);
			break;
		case PUBLIC:
			this.radPublic.setSelected(true);
			break;
		default:
			break;

		}

		listModel.setListeParametre(methodeACreer);
	}
}


