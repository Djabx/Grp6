package carte;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import image.ExtraireImage;
import image.Image;
import image.ManageImage;
import projet.ClickSouris;
import carte.PainterModif;
import waypoint.WaypointDefautModif;

public class Carte {
	private JXMapKit carte;
	private Set<waypoint.WaypointDefautModif> waypoint; // waypoint qui seront
														// dessinés

	private PainterModif painter;
	private ManageImage manageImage; // Liste qui contient les images
	private Component clickonMap;
	public ArrayList<Image> ImageWaypointResultat;
	private ArrayList<waypoint.WaypointDefautModif> WaypointClicResultat;
	private Image derniereimage;

	public Carte() { // instanciation des données nécessaire à la map
		this.manageImage = new ManageImage();
		this.waypoint = new HashSet<waypoint.WaypointDefautModif>();
		this.WaypointClicResultat = new ArrayList<waypoint.WaypointDefautModif>();

		this.carte = new JXMapKit();
		this.carte.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);

		Component clickonmap = new ClickSouris(this, this.WaypointClicResultat); // Ecoute
																					// et
																					// récupère
																					// les
																					// coordonnées
																					// du
																					// click
		this.carte.getMainMap().addMouseListener((MouseListener) clickonmap); // Avec
																				// la
																				// class
																				// CLickSouris

		this.painter = new PainterModif();
		this.carte.getMainMap().setOverlayPainter(painter);
		this.initialisation();

	}

	public JXMapKit getMap() { // Récupération de la carte
		return this.carte;
	}

	public void paint() {
		this.painter.setWaypoint(waypoint); // donne la liste des waypoints

		this.carte.getMainMap().repaint();
	}

	public ManageImage getManageImage() { // Récupération de la liste de
											// manageImage
		return this.manageImage;
	}

	public ArrayList<Image> getImagList() { // Récupération de cette liste
											// d'images
		return this.manageImage.imagList;
	}

	public ArrayList<WaypointDefautModif> RecupClicWaypointResultat() {
		return this.WaypointClicResultat;
	}

	public ArrayList<Image> RecupImageWaypointResultat() {
		this.ImageWaypointResultat = new ArrayList<Image>();

		for (int i = 0; i < this.RecupClicWaypointResultat().size(); i++) {
			int key = this.RecupClicWaypointResultat().get(i).getKey();
			this.ImageWaypointResultat.add(this.getImagList().get(key));
		}
		return this.ImageWaypointResultat;
	}

	public void ajoutWaypoint(double coordx, double coordy, int key) { // Ajoute
																		// un
																		// waypoint
																		// au
		this.waypoint.add(new WaypointDefautModif(coordx, coordy, key)); // set
																			// de
																			// waypoints
		this.paint();
	}

	public void ajoutWaypoint(GeoPosition point, int key) { // Ajoute un
															// waypoint au
		this.waypoint.add(new WaypointDefautModif(point, key)); // set de
																// waypoints
		this.paint();
	}

	public void AjoutImage() throws IOException {

		Image newimag = ExtraireImage.LoadImage(); // Ouvrir la fenêtre de
													// recherche d'image
		this.manageImage.imagList.add(newimag); // Ajoute cette image à notre
												// Liste
		this.derniereimage = newimag;

		// Ajoute un waypoint avec sa latitude et sa longitude
		this.ajoutWaypoint(newimag.Latitude, newimag.Longitude, this.manageImage.imagList.size() - 1);
	}

	public Image getLastImag() { // Récupération de la dernière image
		return this.derniereimage;
	}

	public void centrerImag(GeoPosition pos) { // Centre à la position choisie
		this.carte.getMainMap().setCenterPosition(pos);

		this.paint();
	}

	public void initialisation() { // Permet d'afficher les waypoints d'images
									// déjà chargées dans le dossier
		if (!this.manageImage.imagList.isEmpty()) {
			for (int i = 0; i < this.manageImage.imagList.size(); i++) {
				this.waypoint.add(new WaypointDefautModif(this.manageImage.imagList.get(i).Latitude,
						this.manageImage.imagList.get(i).Longitude, i));
			}
		}
		this.paint();
	}

	public Collection<? extends WaypointDefautModif> RecupSetWaypoint() { // Récupération
																			// du
																			// set
																			// de
																			// waypoints
		return this.waypoint;
	}

}