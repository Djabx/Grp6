package carte;

import java.util.Set;

import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

class PainterModif extends WaypointPainter<Waypoint> {

	public void setWaypoint(Set<? extends Waypoint> waypoints) {
		super.setWaypoints(waypoints);
	}
}