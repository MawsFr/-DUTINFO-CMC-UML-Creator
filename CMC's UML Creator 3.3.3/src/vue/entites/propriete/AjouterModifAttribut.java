package vue.entites.propriete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.entites.caracteristiques.Visibilite;
import modele.entites.caracteristiques.attribut.AttributEntite;
import modele.entites.caracteristiques.methode.MethodeEntite;
import vue.entites.EntiteFigure;

public class AjouterModifAttribut extends JDialog{

	private JPanel pnlGeneral;

	private JPanel pnlInfo;
	private JPanel pnlCheck;
	private JPanel pnlBouton;

	private JLabel lbltype;
	private JTextField txtType;

	private JLabel lblNom;
	private JTextField txtNom;

	private JLabel lblInitialValue;
	private JTextField txtInitialValue;

	private JCheckBox checkStatic;
	private JCheckBox checkFinal;

	private ButtonGroup group;
	private JRadioButton radPublic;
	private JRadioButton radProtected;
	private JRadioButton radPrivate; 
	private JRadioButton radDefault;

	private JButton ok, annuler;
	private EntiteFigure entite;

	private AttributEntite attribut;
	
	private boolean modif;

	public AjouterModifAttribut(final EntiteFigure entite) {
		this.entite = entite;
		this.setTitle("Ajout d'Attribut");


		TitledBorder t = new TitledBorder("PropriÃ©tÃ©s Attribut");

		pnlGeneral =new JPanel();
		pnlGeneral.setLayout(new BoxLayout(pnlGeneral, BoxLayout.Y_AXIS));
		pnlGeneral.setBorder(t);

		pnlInfo=new JPanel();
		pnlInfo.setLayout(new GridLayout(0,2));
		pnlCheck=new JPanel();

		lbltype=new JLabel("Type : ");
		txtType=new JTextField("");

		lblNom=new JLabel("Nom : ");
		txtNom=new JTextField("");

		lblInitialValue =new JLabel("Valeur initiale : ");
		txtInitialValue =new JTextField("");

		checkStatic=new JCheckBox("Static");
		checkFinal = new JCheckBox("Final");

		ok = new JButton("OK");
		annuler = new JButton("Annuler");



		group =new ButtonGroup();
		radPublic =new JRadioButton("Public");
		radProtected =new JRadioButton("Protected");
		radPrivate =new JRadioButton("Private");
		radDefault=new JRadioButton("Default");
		radPublic.setActionCommand("public");
		radPrivate.setActionCommand("private");
		radProtected.setActionCommand("protected");
		radDefault.setActionCommand("default");

		group.add(radPublic);
		group.add(radPrivate);
		group.add(radProtected);
		group.add(radDefault);

		radPublic.setSelected(true);

		JPanel panVisibilite =new JPanel();
		TitledBorder visi = new TitledBorder("VisibilitÃ©");
		panVisibilite.setLayout(new BoxLayout(panVisibilite, BoxLayout.X_AXIS));
		panVisibilite.setBorder(visi);
		panVisibilite.add(radPublic);
		panVisibilite.add(radProtected);
		panVisibilite.add(radPrivate);
		panVisibilite.add(radDefault);
		panVisibilite.add(Box.createVerticalStrut(40));


		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!modif) {
					attribut = new AttributEntite();
				}

				attribut.setNom(txtNom.getText());
				attribut.setType(txtType.getText());
				attribut.setFinal(checkFinal.isSelected());
				attribut.setStatique(checkStatic.isSelected());
				attribut.setValeur(txtInitialValue.getText());

				if(radPublic.isSelected())
				{
					attribut.setVisibilite(Visibilite.PUBLIC);
				}

				if(radPrivate.isSelected())
				{
					attribut.setVisibilite(Visibilite.PRIVATE);
				}

				if(radProtected.isSelected())
				{
					attribut.setVisibilite(Visibilite.PROTECTED);
				}

				if(radDefault.isSelected())
				{
					attribut.setVisibilite(Visibilite.DEFAULT);
				}
				/*
				 * C'est de la que vient le probleme il faut appeler ajouterMethode de MethodeListModel
				 * Sauf que je sais pas comment on fait pour acceder Ã  OngletMethodePropriete a partir de cette vue lol
				 * 
				 * 
				 */
				if(!modif) {
					entite.getFeuille().getFenetre(entite).getOngletAttributs().getListAttribut().ajouterAttributTemporaire(attribut);
				} else {
					entite.getFeuille().getFenetre(entite).getOngletAttributs().getListeAttributs().setModel(entite.getFeuille().getFenetre(entite).getOngletAttributs().getListAttribut());
				}
				dispose();
			}
		});

		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		Container c = this.getContentPane();
		pnlInfo.add(lbltype);
		pnlInfo.add(txtType);
		pnlInfo.add(lblNom);
		pnlInfo.add(txtNom);
		pnlInfo.add(lblInitialValue);
		pnlInfo.add(txtInitialValue);
		pnlGeneral.add(pnlInfo);
		pnlBouton=new JPanel();
		pnlBouton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlBouton.add(ok);
		pnlBouton.add(annuler);

		pnlCheck.add(checkStatic);
		pnlCheck.add(checkFinal);
		pnlCheck.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlCheck.setAlignmentX(SwingConstants.WEST);
		pnlGeneral.add(pnlInfo);
		pnlGeneral.add(panVisibilite);

		pnlGeneral.add(pnlCheck);
		pnlGeneral.add(pnlBouton);
		c.add(pnlGeneral);


		this.setLocationRelativeTo(null);
		pack();
		setResizable(false);
	}

	public AjouterModifAttribut(EntiteFigure entite, AttributEntite attribut) {
		this(entite);
		this.attribut = attribut;
		modif = true;
		this.txtNom.setText(attribut.getNom());
		this.txtType.setText(attribut.getType());
		this.txtInitialValue.setText(attribut.getValeur());
		this.checkFinal.setSelected(attribut.estFinal());
		this.checkStatic.setSelected(attribut.estStatique());

		switch(attribut.getVisibilite()) {
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


	}
	/*public static void main(String[] args) {

			AjouterModifAttribut dialog = new AjouterModifAttribut();
			dialog.setVisible(true);
		}*/

}

