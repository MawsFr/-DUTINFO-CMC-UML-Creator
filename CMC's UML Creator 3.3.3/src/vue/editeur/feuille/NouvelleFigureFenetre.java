package vue.editeur.feuille;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vue.CMCUmlView;
import controlleur.feuille.CreerFigureAction;

public class NouvelleFigureFenetre extends JDialog {

	private FeuilleDessin feuille;
	private Point mouse;

	private JButton btnCreer;
	private JButton btnAnnuler;

	private JLabel lblTitre;
	private JTextField txtNom;

	public NouvelleFigureFenetre(FeuilleDessin feuille, Point mouse) {
		super(CMCUmlView.getInstance());
		this.feuille = feuille;
		this.mouse = mouse;

		this.lblTitre = new JLabel();

		switch(feuille.getFeuilleModele().getMode()) {
		case AGGREGATION:
			lblTitre.setText("Entrez le nom de l'aggrégation :");
			break;
		case ASSOCIATION:
			lblTitre.setText("Entrez le nom de l'association :");
			break;
		case CLASSE:
			lblTitre.setText("Entrez le nom de la classe :");
			break;
		case COMPOSITION:
			lblTitre.setText("Entrez le nom de la composition :");
			break;
		case DEPENDANCE:
			lblTitre.setText("Entrez le nom de la dependance :");
			break;
		case DIRECTASSOCIATION:
			lblTitre.setText("Entrez le nom de l'association directe :");
			break;
		case ENUMERATION:
			lblTitre.setText("Entrez le nom de l'énumération :");
			break;
		case GENERALISATION:
			lblTitre.setText("Entrez le nom de la généralisation :");
			break;
		case INTERFACE:
			lblTitre.setText("Entrez le nom de l'interface :");
			break;
		case REALISATION:
			lblTitre.setText("Entrez le nom de la réalisation :");
			break;

		default:
			break;


		}

		txtNom = new JTextField();

		this.btnCreer = new JButton(new CreerFigureAction(this, feuille, mouse));
		txtNom.setAction(new CreerFigureAction(this, feuille, mouse));
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.X_AXIS));
		boutons.add(btnCreer);
		boutons.add(btnAnnuler);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		c.add(lblTitre, BorderLayout.NORTH);
		c.add(txtNom, BorderLayout.CENTER);
		c.add(boutons, BorderLayout.SOUTH);

		setModal(true);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	public JTextField getTxtNom() {
		return this.txtNom;
	}
	//	public static void main(String[] args) {
	//		new NouvelleFigureFenetre(new FeuilleDessin(new EditeurUmlModele(), new FeuilleDessinModele("Feuille")), null);
	//	}

}
