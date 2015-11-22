package vue.relation;

import java.awt.Stroke;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import modele.relation.NoeudModele;
import modele.relation.RelationModele;
import vue.editeur.feuille.FeuilleDessin;

public class RelationFigure implements Observer {
	
	protected RelationModele relation;
	protected FeuilleDessin feuille;
	protected Stroke bordure;
	protected LinkedList<NoeudFigure> noeuds;
	
	public RelationFigure(FeuilleDessin feuille, RelationModele relation) {
		this.feuille = feuille;
		this.relation = relation;
		this.noeuds = new LinkedList<NoeudFigure>();
		relation.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		for(Iterator<NoeudFigure> it = this.noeuds.iterator(); it.hasNext();) {
			feuille.remove(it.next());
		}
		this.noeuds.clear();
		for(Iterator<NoeudModele> it = relation.getNoeuds().iterator(); it.hasNext();) {
			NoeudFigure noeud = new NoeudFigure(feuille, this, it.next());
			noeuds.add(noeud);
			feuille.add(noeud);
			
			
		}
		
		feuille.repaint();
		
	}
	
	public RelationModele getRelation() {
		return this.relation;
	}

	public Stroke getStroke() {
		return this.bordure;
	}

	public LinkedList<NoeudFigure> getNoeuds() {
		return noeuds;
	}

	public FeuilleDessin getFeuille() {
		return feuille;
	}
	
	
	
	

}
