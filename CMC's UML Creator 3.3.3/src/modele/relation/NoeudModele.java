package modele.relation;

/**
 * 
 * @author Maws
 * 
 * 
 * 
 * 
 */

import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;

public class NoeudModele extends Observable implements Serializable{
	
	private Point point;
	private boolean dragged;
	private final int TAILLE = 8;
	
	public NoeudModele(Point point) {
		this.point = point;
	}

	public NoeudModele() {
		this(null);
	}

	public Point getPoint() {
		return point;
	}

	public int getCenterX() {
		return point.x + TAILLE/2;
	}

	public int getCenterY() {
		return point.y + TAILLE/2;
	}
	
	public int getTaille() {
		return TAILLE;
	}
	
	public void setPoint(Point point) {
		this.point = point;
		notifier();
	}

	public void setDragged(boolean b) {
		this.dragged = b;
		
	}

	public boolean isDragged() {
		return this.dragged;
	}
	
	
	public void notifier() {
		setChanged();
		notifyObservers();
	}
	

}
