package waypoint;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class WaypointDefautModif extends DefaultWaypoint {

	private int key;
	private GeoPosition pos;

	public WaypointDefautModif(double latitude, double longitude, int _key) {
		super(latitude, longitude);
		this.key = _key;

	}

	public WaypointDefautModif(GeoPosition coord, int _key) {
		super(coord);
		this.key = _key;
	}

	public int getKey() {
		return this.key;
	}
}