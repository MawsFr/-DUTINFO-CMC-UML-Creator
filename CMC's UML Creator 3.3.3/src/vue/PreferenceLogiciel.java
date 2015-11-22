package vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modele.EditeurUmlModele;

public class PreferenceLogiciel extends JFrame {
	
	private EditeurUmlModele modele;
	private JPanel preferenceLogiciel;
	private JPanel preferenceEditeur;
	
	private JButton restaurerPropLogiciel;
	private JButton restaurerPropEditeur;
	
	private JCheckBox antialiashing;
	private JCheckBox coordSpecial;
	
	private JButton btnOk;
	private JButton btnAnnuler;
	
	public PreferenceLogiciel(final EditeurUmlModele modele) {
		super("Préférences");
		this.modele = modele;
		JPanel panel = new JPanel();
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		preferenceEditeur = new JPanel();
		preferenceLogiciel = new JPanel();
		
		restaurerPropEditeur = new JButton("Restaurer par défaut");
		restaurerPropLogiciel = new JButton("Restaurer par defaut");
		
		antialiashing = new JCheckBox("Antialishing");
		coordSpecial = new JCheckBox("Calcul special Coordonnées");
		
		
		
		preferenceEditeur.setLayout(new BoxLayout(preferenceEditeur, BoxLayout.Y_AXIS));
		preferenceLogiciel.setLayout(new BoxLayout(preferenceLogiciel, BoxLayout.Y_AXIS));
		
		preferenceEditeur.add(this.antialiashing);
		preferenceEditeur.add(coordSpecial);
		preferenceEditeur.add(restaurerPropEditeur);
		
		preferenceLogiciel.add(restaurerPropLogiciel);
		
		preferenceLogiciel.setBorder(new TitledBorder("Propriétés Logiciel"));
		preferenceEditeur.setBorder(new TitledBorder("Propriétés Editeur"));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(preferenceLogiciel);
		panel.add(preferenceEditeur);
		
		c.add(panel, BorderLayout.CENTER);
		
		btnAnnuler = new JButton("Annuler");
		btnOk = new JButton("OK");
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modele.getProprietesEditeur().setProperty("antialiashing", (antialiashing.isSelected())? "true" : "false");
				modele.getProprietesEditeur().setProperty("coordspecial", (coordSpecial.isSelected())? "true" : "false");
				dispose();
			}
		});
		
		restaurerPropEditeur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modele.getProprietesEditeur().setProperty("antialiashing", "true");
				modele.getProprietesEditeur().setProperty("coordspecial", "false");
				reload();
				
			}
		});
		
		restaurerPropLogiciel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.getProprietesLogiciel().setProperty("nom", "CMC UML Creator");
				modele.getProprietesLogiciel().setProperty("coordx", "" + ((Toolkit.getDefaultToolkit().getScreenSize().width /2) - 600/2));
				modele.getProprietesLogiciel().setProperty("coordy", "" + ((Toolkit.getDefaultToolkit().getScreenSize().height /2) - 600/2));
				modele.getProprietesLogiciel().setProperty("width", "" + 600);
				modele.getProprietesLogiciel().setProperty("height", "" + 600);
				modele.getProprietesLogiciel().setProperty("state", "" + 0);
				
				CMCUmlView.getInstance().setTitle(modele.getProprietesLogiciel().getProperty("nom"));
				CMCUmlView.getInstance().setBounds(Integer.parseInt(modele.getProprietesLogiciel().getProperty("coordx")), Integer.parseInt(modele.getProprietesLogiciel().getProperty("coordy")), Integer.parseInt(modele.getProprietesLogiciel().getProperty("width")), Integer.parseInt(modele.getProprietesLogiciel().getProperty("height")));
				CMCUmlView.getInstance().setExtendedState(Integer.parseInt(modele.getProprietesLogiciel().getProperty("state")));
			}
		});
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		boutons.add(btnOk);
		boutons.add(btnAnnuler);
		
		c.add(boutons, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		
		
	}

	private void reload() {
		antialiashing.setSelected(modele.getProprietesEditeur().getProperty("antialiashing").equals("true"));
		coordSpecial.setSelected(modele.getProprietesEditeur().getProperty("coordspecial").equals("true"));
	}
}
