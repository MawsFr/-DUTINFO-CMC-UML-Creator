package modele.entites;

import java.awt.Dimension;

public interface EntiteRedimensionnable extends EntiteDeplacable {
	
	public boolean estRedimensionnable();
	public boolean estEnRedimension();
	
	public void setEnRedimension(boolean b);
	public void setRedimensionnable(boolean b);
	
	public Dimension getTaille();
	public void setTaille(Dimension taille);
	
	public int getWidth();
	public int getHeight();
	
	public void setWidth(int width);
	public void setHeight(int heught);
	

}
