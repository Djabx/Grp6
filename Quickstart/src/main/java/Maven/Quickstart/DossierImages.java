package Maven.Quickstart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.drew.imaging.jpeg.JpegProcessingException;


public class DossierImages {
	
	public static ArrayList<Image> listeImages;
	public static final String nomDossier = "LesImages";
	public static final File fichiersDossier = new File(nomDossier);

	public DossierImages() throws JpegProcessingException, IOException {
		this.listeImages = new ArrayList<Image>();
		File dossier;
		dossier = new File("LesImages").getCanonicalFile();
		String[] liste = dossier.list();
		for (int i = 0; i < liste.length; i++) {
			File fichier = new File(fichiersDossier, liste[i]).getCanonicalFile();
			double lon = ExtraireDonnees.LaLatitude(fichier);
			double lat = ExtraireDonnees.LaLongitude(fichier);
			Image image = new Image(fichier, lon, lat);
			this.listeImages.add(image);	
		}
	}
	
	public void AjoutHorsUtilisation() throws IOException, JpegProcessingException {
		File dossier;
		dossier = new File("LesImages").getCanonicalFile();
		String[] liste = dossier.list();
		for (int i = 0; i < liste.length; i++) {
			File fichier = new File(fichiersDossier, liste[i]).getCanonicalFile();
			Image image = new Image(fichier, ExtraireDonnees.LaLatitude(fichier), ExtraireDonnees.LaLongitude(fichier));
			if (!(listeImages.contains(image))) {
				fichier.delete();
			}
		}
	}
	
}