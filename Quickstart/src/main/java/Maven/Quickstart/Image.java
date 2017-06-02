package Maven.Quickstart;

import java.io.File;
import java.util.ArrayList;

public class Image {

	File fichier;
	double lattitude;
	double longitude;
	String nom;

	public Image(File file, double lat, double lon) {
		this.fichier = file;
		this.lattitude = lat;
		this.longitude = lon;
		this.nom = file.getName();
	}
}
