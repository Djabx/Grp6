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
import waypoint.PainterModif;
import waypoint.WaypointDefautModif;

public class Carte {
	private JXMapKit carte;
	private Set<WaypointDefautModif> waypoint;

	private Component clickonMap;
	private ManageImage manageImage;
	private Image derniereimage;
	private ArrayList<Image> ImageWaypointResultat;
	private ArrayList<WaypointDefautModif> WaypointClicResultat;

	private PainterModif painter;

	public Carte() {
		this.manageImage = new ManageImage();
		this.carte = new JXMapKit();
		this.carte.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);

		this.waypoint = new HashSet<WaypointDefautModif>();
		this.WaypointClicResultat = new ArrayList<WaypointDefautModif>();

		this.painter = new PainterModif();
		this.carte.getMainMap().setOverlayPainter(painter);
		this.initialisation();

	}

	public JXMapKit getMap() { // Obtention de la carte
		return this.carte;
	}

	public void paint() {
		this.painter.setWaypoint(waypoint);

		this.carte.getMainMap().repaint();
	}

	public ManageImage getManageImage() {
		return this.manageImage;
	}

	public ArrayList<Image> getImagList() {
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

	public void ajoutWaypoint(double coordx, double coordy, int key) {
		this.waypoint.add(new WaypointDefautModif(coordx, coordy, key));
		this.paint();
	}

	public void ajoutWaypoint(GeoPosition point, int key) {
		this.waypoint.add(new WaypointDefautModif(point, key));
		this.paint();
	}

	public void AjoutImage() throws IOException {

		Image newimag = ExtraireImage.LoadImage();
		this.manageImage.imagList.add(newimag);
		this.derniereimage = newimag;

		this.ajoutWaypoint(newimag.Latitude, newimag.Longitude, this.manageImage.imagList.size() - 1);
	}

	public Image getLastImag() {
		return this.derniereimage;
	}

	public void centrerImag(GeoPosition pos) {
		this.carte.getMainMap().setCenterPosition(pos);

		this.paint();
	}

	public void initialisation() {
		if (!this.manageImage.imagList.isEmpty()) {
			for (int i = 0; i < this.manageImage.imagList.size(); i++) {
				this.waypoint.add(new WaypointDefautModif(this.manageImage.imagList.get(i).Latitude,
						this.manageImage.imagList.get(i).Longitude, i));
			}
		}
		this.paint();
	}

	public Collection<? extends WaypointDefautModif> RecupSetWaypoint() {
		return this.waypoint;
	}

}