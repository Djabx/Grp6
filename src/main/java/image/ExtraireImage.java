package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.io.IOUtils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDescriptor;
import com.drew.metadata.exif.GpsDirectory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ExtraireImage {

	private static JChooserPreviewPanel previewchooser = new JChooserPreviewPanel();

	// Méthode qui permet de récupérer le fichier, avec un gestionnaire de
	// fichier (recherche,parcourir,..)
	// Et récupère l'image
	public static Image LoadImage() throws IOException {
		File repertoire = null;
		try {
			repertoire = new File(".").getCanonicalFile();
			System.out.println("Répertoire courant : " + repertoire);
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		JFileChooser dial = new JFileChooser(repertoire);
		dial.setAccessory(previewchooser); // Ajout du JFileChooser preview +
		dial.addPropertyChangeListener(pcl); // Ecouteur de changements de choix
		dial.showOpenDialog(null);

		System.out.println("Fichier choisi :" + dial.getSelectedFile());
		File f = dial.getSelectedFile();
		String name = f.getName();
		Image image = null;

		try {
			image = new Image(f, getLatittude(f), getLongitude(f));
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// Si l'image n'a pas de coordonnées, on l'initialise à 0,0
			image = new Image(f, 0, 0);
		}
		InputStream inp = new FileInputStream(f);
		File dossier;
		try {
			dossier = new File("Dossier Image").getCanonicalFile();
			String[] list = dossier.list();
			int i = 0;

			boolean contain = false;
			while (i < list.length) {
				if (list[i].equals(name)) {
					contain = true;
				}
				i++;
			}
			if (contain == true) {
				int i2 = 0;
				int cpt = 0;

				while (i2 < list.length) {
					String[] pref = name.split("\\.");
					if (list[i2].startsWith(pref[0])) {
						cpt++;
					}
					i2++;
				}
				String[] pref = name.split("\\.");
				String str = String.valueOf(cpt);
				String str2 = "(".concat(str).concat(")");
				name = pref[0].concat(str2).concat(".").concat(pref[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream out = new FileOutputStream(new File(ManageImage.DIR_FILE, name));
		IOUtils.copy(inp, out);
		return image;

	}

	static PropertyChangeListener pcl = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
				File f = (File) e.getNewValue();
				if (f != null) {
					if (f.isFile()) {
						String s = f.getPath(), suffix = null;
						int i = s.lastIndexOf('.');

						if (i > 0 && i < s.length() - 1) {
							suffix = s.substring(i + 1).toLowerCase();

							if (suffix.equals("gif") || suffix.equals("png") || suffix.equals("jpg")) {
								previewchooser.getImagePreviewer().configure(f);
							}
						}
					}
				}

			}
		}
	};

	// Méthode qui récupère la Latitude et la convertie en degrées
	public static float getLatittude(File f) {
		String lat = "";
		try {
			Metadata data = ImageMetadataReader.readMetadata(f);
			if (data.containsDirectoryOfType(GpsDirectory.class)) {
				Collection<GpsDirectory> GpsDir = data.getDirectoriesOfType(GpsDirectory.class);
				for (GpsDirectory gpsDirectory : GpsDir) {
					GpsDescriptor gpsDescript = new GpsDescriptor(gpsDirectory);
					lat = lat + gpsDescript.getGpsLatitudeDescription();
				}
			}
		} catch (ImageProcessingException e) {
			System.out.println("Attention Erreur");
		} catch (IOException e) {
			System.out.println("Attention votre image n'a pas de coordonnées GPS");
		} catch (java.lang.ClassCastException e) {
			System.out.println("Attention Erreur 3");
		}
		// conversion//
		lat = lat.replace(",", ".");

		String[] tableau = lat.split("°");
		tableau[1] = tableau[1].substring(1, tableau[1].length());

		String[] tableau2 = tableau[1].split("'");
		tableau2[1] = tableau2[1].substring(1, tableau2[1].length() - 1);

		float deg = Float.parseFloat(tableau[0]);
		float min = Float.parseFloat(tableau2[0]);
		float sec = Float.parseFloat(tableau2[1]);

		float val = deg + min / 60 + sec / 3600;
		return val;
	}

	// Méthode qui récupère la Longitude et la convertie en degrées
	public static float getLongitude(File f) {
		String lon = "";
		try {
			Metadata data = ImageMetadataReader.readMetadata(f);
			if (data.containsDirectoryOfType(GpsDirectory.class)) {
				Collection<GpsDirectory> GpsDir = data.getDirectoriesOfType(GpsDirectory.class);
				for (GpsDirectory gpsDirectory : GpsDir) {
					GpsDescriptor gpsDescript = new GpsDescriptor(gpsDirectory);
					lon = lon + gpsDescript.getGpsLongitudeDescription();
				}
			}
		} catch (ImageProcessingException e) {
			System.out.println("Attention Erreur");
		} catch (IOException e) {
			System.out.println("Attention votre image n'as pas de coordonnées GPS");
		}
		// conversion//
		lon = lon.replace(",", ".");

		String[] tableau = lon.split("°");
		tableau[1] = tableau[1].substring(1, tableau[1].length());

		String[] tableau2 = tableau[1].split("'");
		tableau2[1] = tableau2[1].substring(1, tableau2[1].length() - 1);

		float deg = Float.parseFloat(tableau[0]);
		float min = Float.parseFloat(tableau2[0]);
		float sec = Float.parseFloat(tableau2[1]);

		float val = deg + min / 60 + sec / 3600;
		return val;
	}

}