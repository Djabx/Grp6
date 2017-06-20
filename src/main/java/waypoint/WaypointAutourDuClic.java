package waypoint;

import java.awt.Point;
import java.util.ArrayList;

import org.jxmapviewer.viewer.GeoPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;

import carte.Carte;

public class WaypointAutourDuClic {

	public static void Check(Carte _carte, Point _sourisPosition, ArrayList<WaypointDefautModif> WaypointClicResultat) {

		// Mise à 0 de la liste
		WaypointClicResultat.clear();

		// Création d'un polygone (rectangle)
		Point point_bg = new Point();
		Point point_bd = new Point();
		Point point_hg = new Point();
		Point point_hd = new Point();

		// coordonnees du polygone
		point_bg.setLocation(_sourisPosition.getX() - 5, _sourisPosition.getY());
		point_bd.setLocation(_sourisPosition.getX() + 5, _sourisPosition.getY());
		point_hg.setLocation(_sourisPosition.getX() - 10, _sourisPosition.getY() + 37);
		point_hd.setLocation(_sourisPosition.getX() + 10, _sourisPosition.getY() + 37);

		// conversion de coordonnees pixel vers lon/lat
		GeoPosition geo_bg = _carte.getMap().getMainMap().convertPointToGeoPosition(point_bg);
		GeoPosition geo_bd = _carte.getMap().getMainMap().convertPointToGeoPosition(point_bd);
		GeoPosition geo_hg = _carte.getMap().getMainMap().convertPointToGeoPosition(point_hg);
		GeoPosition geo_hd = _carte.getMap().getMainMap().convertPointToGeoPosition(point_hd);

		// Tableau répertoriant les coordonnes des polygones
		Coordinate[] coordonnees = new Coordinate[5];
		coordonnees[0] = new Coordinate(geo_bg.getLongitude(), geo_bg.getLatitude());
		coordonnees[1] = new Coordinate(geo_hg.getLongitude(), geo_hg.getLatitude());
		coordonnees[2] = new Coordinate(geo_hd.getLongitude(), geo_hd.getLatitude());
		coordonnees[3] = new Coordinate(geo_bd.getLongitude(), geo_bd.getLatitude());
		coordonnees[4] = new Coordinate(geo_bg.getLongitude(), geo_bg.getLatitude());

		// Création du polygone
		GeometryFactory geomFact = new GeometryFactory();
		LinearRing contourpolygone = geomFact.createLinearRing(coordonnees);

		Geometry polygone = geomFact.createPolygon(contourpolygone);

		// Vérification si polygone contient un waypoint
		for (WaypointDefautModif notre_waypoint : _carte.RecupSetWaypoint()) {
			Geometry waypoint_test = geomFact.createPoint(new Coordinate(notre_waypoint.getPosition().getLongitude(),
					notre_waypoint.getPosition().getLatitude()));

			// Ajout du waypoint situé autour du click a la liste
			if (polygone.contains(waypoint_test)) {
				WaypointClicResultat.add(notre_waypoint);
			}
		}
	}
}