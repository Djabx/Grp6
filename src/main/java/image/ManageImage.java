package image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManageImage {

	public static final String DIR_NAME = "Dossier Image";

	public static final File DIR_FILE = new File(DIR_NAME);

	public ArrayList<Image> imagList;

	public ManageImage() {
		this.imagList = new ArrayList<Image>();
		File dossier;
		try {
			dossier = new File("Dossier Image").getCanonicalFile();
			String[] liste = dossier.list();
			for (int i = 0; i < liste.length; i++) {
				if (liste[i].endsWith(".jpg") || liste[i].endsWith(".gif") || liste[i].endsWith(".png")) {
					File f = new File(DIR_FILE, liste[i]).getCanonicalFile();
					float lat = ExtraireImage.getLatittude(f);
					float lon = ExtraireImage.getLongitude(f);
					Image imag = new Image(f, lat, lon);
					this.imagList.add(imag);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void BaseDonneeFermee(ArrayList<Image> ListImage){
		File dos;
		try {
			dos = new File("Dossier Image").getCanonicalFile();
			String[] list = dos.list();
			for(int i=0; i < list.length; i++){
				File f = new File(DIR_FILE,list[i]).getCanonicalFile();
				Image image = new Image(f, ExtraireImage.getLatittude(f),ExtraireImage.getLongitude(f));
				if(!(ListImage.contains(image))){
					f.delete();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
}
