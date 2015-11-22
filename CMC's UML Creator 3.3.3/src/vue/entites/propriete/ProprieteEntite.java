package vue.entites.propriete;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.entites.ClasseModele;
import modele.entites.EntiteModele;
import modele.entites.caracteristiques.Visibilite;
import modele.exceptions.AlreadyExistException;
import vue.CMCUmlView;
import vue.entites.EntiteFigure;

public class ProprieteEntite extends JDialog implements WindowListener {
	private EntiteFigure entite;
	private JTabbedPane onglets;
	private JButton boutonOk, boutonAnnuler, boutonAppliquer;
	private Container c;

	private OngletGeneralPropriete ongletGeneral;
	private OngletAttributsPropriete ongletAttributs;
	private OngletMethodesPropriete ongletMethodes;

	public ProprieteEntite(EntiteFigure entite) {
		super(CMCUmlView.getInstance(), "Proprièté de l'entité " + entite.getEntite().getNom());

		this.entite = entite;

		onglets = new JTabbedPane();

		boutonOk = new JButton("OK");
		boutonAnnuler = new JButton("Annuler");
		boutonAppliquer = new JButton("Appliquer");

		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		boutons.add(boutonOk);
		boutons.add(boutonAnnuler);
		boutons.add(boutonAppliquer);



		onglets.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
			}
		});

		boutonAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fermerFenetre();
			}
		});

		boutonOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appliquerTout();
				fermerFenetre();
				
			}
		});

		boutonAppliquer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appliquerTout();
			}
		});


		addWindowListener(this);

		this.ongletGeneral = new OngletGeneralPropriete(this, entite);
		this.ongletAttributs = new OngletAttributsPropriete(this, entite);
		this.ongletMethodes = new OngletMethodesPropriete(this, entite);

		onglets.addTab("Général", ongletGeneral);
		onglets.addTab("Attributs", ongletAttributs);
		onglets.addTab("Méthodes", ongletMethodes);

		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(onglets, BorderLayout.CENTER);
		c.add(boutons, BorderLayout.SOUTH);


		setSize(500, 300);
		setMinimumSize(new Dimension(500,300));
		setLocationRelativeTo(null);
		//		setModal(true);


	}

	public JTabbedPane getOnglets() {
		return this.onglets;
	}

	public void selectionnerOngletGeneral() {
		this.onglets.setSelectedComponent(ongletGeneral);
	}

	public void selectionnerOngletAttributs() {
		this.onglets.setSelectedComponent(ongletAttributs);
	}

	public void selectionnerOngletMethodes() {
		this.onglets.setSelectedComponent(ongletMethodes);
	}

	public void fermerFenetre() {
		entite.getFeuille().fermerFenetre(this.entite);
		dispose();
	}

	public void appliquerGeneral() {
		EntiteModele e = entite.getEntite();
		try {
			e.setNom(ongletGeneral.getTxtNom().getText());
		} catch(IllegalArgumentException ex) {
			CMCUmlView.getInstance().getBarreStatut().afficherErreur(ex.getMessage());
		}
		e.setVisibilite((Visibilite) ongletGeneral.getComboVisibilite().getSelectedItem());
		if(e.isClasse()) {
			try {
				((ClasseModele) e).setFinale(ongletGeneral.getEstFinal().isSelected());
				((ClasseModele) e).setAbstraite(ongletGeneral.getEstAbstraite().isSelected());
			} catch(IllegalArgumentException ex) {
				CMCUmlView.getInstance().getBarreStatut().afficherErreur(ex.getMessage());
			}
		}

	}

	public void appliquerAttributs() {
		try {
			entite.getEntite().setAttributs(ongletAttributs.getListAttribut().getListeCopie());
		} catch (AlreadyExistException e) {
			CMCUmlView.getInstance().getBarreStatut().afficherErreur(e.getMessage());
		}
	}

	public void appliquerMethodes() {
		try {
			entite.getEntite().setMethodes(ongletMethodes.getListModel().getListeCopie());
		} catch (AlreadyExistException e) {
			CMCUmlView.getInstance().getBarreStatut().afficherErreur(e.getMessage());
		}
	}

	public void appliquerTout() {
		appliquerGeneral();
		appliquerAttributs();
		appliquerMethodes();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		entite.getFeuille().fermerFenetre(this.entite);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	public OngletGeneralPropriete getOngletGeneral() {
		return ongletGeneral;
	}

	public OngletAttributsPropriete getOngletAttributs() {
		return ongletAttributs;
	}

	public OngletMethodesPropriete getOngletMethodes() {
		return ongletMethodes;
	}

	/*public static void main(String[] args) {
		List<AttributEntite> attributs = new ArrayList<>();
		attributs.add(new AttributEntite("String", "tamere"));
		attributs.add(new AttributEntite("dfg", "tamere"));
		attributs.add(new AttributEntite("sdfghsdf", "tamere"));
		attributs.add(new AttributEntite("sdfhgf", "tamere"));

		EntiteModele entite = new ClasseModele("OSEF", Visibilite.PUBLIC, attributs, null, null);
		new ProprieteEntite(entite);

	}*/

}
