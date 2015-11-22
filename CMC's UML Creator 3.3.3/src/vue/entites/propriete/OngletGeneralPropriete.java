package vue.entites.propriete;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modele.entites.ClasseModele;
import modele.entites.caracteristiques.Visibilite;
import modele.relation.CardinaliteModele;
import vue.entites.EntiteFigure;

public class OngletGeneralPropriete extends JPanel {

	private ProprieteEntite proprietes;
	private EntiteFigure entite;
	private JPanel general;
	private JPanel relation;

	private JLabel labelNom;
	private JTextField txtNom;

	private JLabel labelVisibilite;
	private JComboBox<Visibilite> comboVisibilite;

	private JCheckBox estFinal;
	private JCheckBox estAbstraite;
	///////

	private JLabel lblExtends;
	private JTextField txtExtends;
	private JLabel lblImplement;
	private JTextField txtImplement;

	private JButton boutonExtends;
	private JButton boutonImplement;
	private JLabel cardinalite;
	private JComboBox<CardinaliteModele> comboCardinalite;

	public OngletGeneralPropriete(ProprieteEntite proprieteEntite, EntiteFigure entite2) {

		this.entite = entite2;
		this.proprietes = proprieteEntite;

		TitledBorder t = new TitledBorder("General");

		general = new JPanel();

		general.setLayout(new GridLayout(0, 2));
		general.setBorder(t);

		labelNom = new JLabel("Nom");
		txtNom = new JTextField(entite2.getEntite().getNom());

		labelVisibilite = new JLabel("Visibilite");
		comboVisibilite = new JComboBox<Visibilite>(Visibilite.values());
		comboVisibilite.setSelectedItem(Visibilite.PUBLIC);

		cardinalite=new JLabel("Cardinalite");

		estFinal = new JCheckBox("Est final");
		estAbstraite = new JCheckBox("Est abstrait");
		
		
		
		
		general.add(labelNom);
		general.add(txtNom);
		general.add(labelVisibilite);
		general.add(comboVisibilite);
		
		if(entite.getEntite().isClasse()) { //on ne met pas abstract pour les interface ca sert a rien...
			general.add(estFinal);
			general.add(estAbstraite);
			estFinal.setSelected(((ClasseModele) entite.getEntite()).estFinale());
			estAbstraite.setSelected(((ClasseModele) entite.getEntite()).estAbstraite());
		}	
		
		estAbstraite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(estFinal.isSelected()) {
					
				}
				
			}
		});
		

		add(general);
		add(Box.createVerticalStrut(20));

		///////////////////////////////////////////////
		//si enumeration alors pas de final ni de abstrait , pas de cardinalite
		relation = new JPanel();
		TitledBorder p = new TitledBorder("Relation");
		relation.setBorder(p);
		relation.setLayout(new GridLayout(0, 2));

		lblExtends =new JLabel("Extends");
		txtExtends =new JTextField("");
		txtExtends.setColumns(20);

		lblImplement=new JLabel("Implements");
		txtImplement=new JTextField("");

		boutonExtends=new JButton("Rechercher");
		boutonImplement=new JButton("Rechercher");

		comboCardinalite=new JComboBox<CardinaliteModele>(CardinaliteModele.values());

		JPanel pnlExtends =new JPanel();
		pnlExtends.setLayout(new BoxLayout(pnlExtends, BoxLayout.X_AXIS));
		pnlExtends.add(txtExtends);
		pnlExtends.add(boutonExtends);
		
		
		JPanel pnlImplement=new JPanel();
		pnlImplement.setLayout(new BoxLayout(pnlImplement, BoxLayout.X_AXIS));
		
		pnlImplement.add(txtImplement);
		pnlImplement.add(boutonImplement);
			
		relation.add(lblExtends);
		relation.add(pnlExtends);

		relation.add(lblImplement);
		relation.add(pnlImplement);
		relation.add(cardinalite);
		relation.add(comboCardinalite);

		add(relation);
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //un en dessous de l'autre
	}
	
	

	/**
	 * @return the txtNom
	 */
	public JTextField getTxtNom() {
		return txtNom;
	}



	/**
	 * @return the comboVisibilite
	 */
	public JComboBox<Visibilite> getComboVisibilite() {
		return comboVisibilite;
	}



	/**
	 * @return the estFinal
	 */
	public JCheckBox getEstFinal() {
		return estFinal;
	}



	/**
	 * @return the estAbstraite
	 */
	public JCheckBox getEstAbstraite() {
		return estAbstraite;
	}



	public static void main(String[] args) {

		JFrame frame = new JFrame();
//		frame.getContentPane().add(new OngletGeneralPropriete());
		frame.pack();
		frame.setVisible(true);
	}

}