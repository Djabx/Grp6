package projet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import carte.Carte;
import waypoint.WaypointAutourDuClic;
import waypoint.WaypointDefautModif;

public class ClickSouris extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carte notrecarte;
	public ArrayList<WaypointDefautModif> WaypointClicResultat;

	public ClickSouris(Carte _carte, ArrayList<waypoint.WaypointDefautModif> _waypointClicResultat) {

		this.notrecarte = _carte;
		this.WaypointClicResultat = _waypointClicResultat;
	}

	public void mouseClicked(MouseEvent clic) {

		// Récupère les coordonnees du clic
		Point _positionSouris = notrecarte.getMap().getMousePosition(); 

		// Check des waypoints autour du clic
		WaypointAutourDuClic.Check(this.notrecarte, _positionSouris, this.WaypointClicResultat); 
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
