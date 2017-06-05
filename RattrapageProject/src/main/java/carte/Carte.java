package carte;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import image.ExtraireImage;
import image.Image;
import image.ManageImage;



public class Carte {
	private JXMapKit carte;
	private Component clickonMap;
	private ManageImage managImage;
	private Image derniereimage;


public Carte() {
	this.managImage = new ManageImage();
	this.carte = new JXMapKit();
	this.carte.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
	
	
}

public JXMapKit getMap() { //Obtention de la carte
	return this.carte;
}

public void paint(){
	this.carte.getMainMap().repaint();
}

public ManageImage getManageImage(){
	return this.managImage;
}

public ArrayList<Image> getImagList(){
	return this.managImage.imagList;
}

public void AjoutImage() throws IOException {
	
	Image newimag = ExtraireImage.LoadImage();
	this.managImage.imagList.add(newimag);
	this.derniereimage = newimag;
}

public Image getLastImag(){
	return this.derniereimage;
}

public void centrerImag(GeoPosition pos){
	this.carte.getMainMap().setCenterPosition(pos);
	
	this.paint();
}

}