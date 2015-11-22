package modele.entites;

import java.awt.Point;

public interface EntiteDeplacable extends EntiteSelectionnable {
	
	public boolean estDeplacable();
	public boolean estEnDeplacement();
	
	public void setEnDeplacement(boolean b); //isDragging();
	public void setDeplacable(boolean b);
	
	public Point getPosition();
	public void setPosition(Point position);
	
	public int getX();
	public int getY();
	
	public void setX(int x);
	public void setY(int y);
	
	
	

}
