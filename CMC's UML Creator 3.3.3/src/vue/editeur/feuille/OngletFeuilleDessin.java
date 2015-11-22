package vue.editeur.feuille;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import modele.EditeurUmlModele;
import modele.FeuilleDessinModele;

public class OngletFeuilleDessin extends JPanel implements Observer{

	
	private JScrollPane scrollPane;
	private FeuilleDessin feuilleDessin;
	
	private EditeurUmlModele modele;
	
	public OngletFeuilleDessin(EditeurUmlModele modele, FeuilleDessinModele feuilleModele) {
		this.modele = modele;
		
		
		feuilleDessin = new FeuilleDessin(modele, feuilleModele);
		
		scrollPane = new JScrollPane(feuilleDessin);
		scrollPane.setDoubleBuffered(true);
		setLayout(new BorderLayout());
//		scrollPane.setPreferredSize(new Dimension(500, 500));
		
		setDoubleBuffered(true);
		add(scrollPane, BorderLayout.CENTER);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		repaint();
//		revalidate();
	}
	
	@Override
	public String toString() {
		return feuilleDessin.getFeuilleModele().getNom();
	}

	public FeuilleDessin getFeuille() {
		return this.feuilleDessin;
	}
	
}
