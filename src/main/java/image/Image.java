package image;

import java.io.File;
import java.util.ArrayList;

public class Image {
	File file;
	public float Latitude;
	public float Longitude;
	public long taille;
	public ArrayList<String> commentaire;
	public String nom;

	public Image(File f, float lat, float lon) { // instanciation des données
													// importantes de l'Image
		this.file = f;
		this.Latitude = lat;
		this.Longitude = lon;
		this.commentaire = new ArrayList<String>();
		this.taille = f.length();
		this.nom = f.getName();
	}

	public void addCom(String com2) throws NoStringException {
		if (com2 == "") {
			throw new NoStringException();
		} else {
			this.commentaire.add(com2);
		}
	}
}