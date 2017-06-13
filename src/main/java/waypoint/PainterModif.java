package waypoint;

import java.util.Set;

import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

public class PainterModif extends WaypointPainter<Waypoint> {

	public void setWaypoint(Set<? extends Waypoint> waypoints) {
		super.setWaypoints(waypoints);
	}

}
