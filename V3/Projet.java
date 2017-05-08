package GRP.geotool;

import org.jxmapviewer.*;

import org.jxmapviewer.JXMapKit.DefaultProviders;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Projet {
 
private static JMenuBar buildMenu() {
	JMenuBar menu = new JMenuBar();
	JMenu mfichier = new JMenu("Importer un fichier");
	JMenuItem iquitter = new JMenuItem("Importer");
	iquitter.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			
			JFileChooser dialogue = new JFileChooser();
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images","png","jpg");
			dialogue.setFileFilter(filtre);
			dialogue.setAcceptAllFileFilterUsed(false);
			File fichier;
			String Nomfichier;
			
			if(dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				fichier = dialogue.getSelectedFile();
				Nomfichier = dialogue.getSelectedFile().getName();
				System.out.println("Fichier sélectionné "+fichier);
				
			dialogue.setVisible(true);
			}
		}
		
		
		
	});
	mfichier.add(iquitter);
	menu.add(mfichier);
 
    return menu;
}

public static void main(String[] args){
	SwingUtilities.invokeLater(new Runnable()
	{
		public void run()
		{
			
			// Create a TileFactoryInfo for OSM
	        TileFactoryInfo info = new OSMTileFactoryInfo();
	        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
	        tileFactory.setThreadPoolSize(4);

	        // Setup local file cache
	        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
	        LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

	        // Setup JXMapViewer
	        JXMapViewer kit = new JXMapViewer();
	        kit.setTileFactory(tileFactory);
			
	        
	        MouseInputListener mia = new PanMouseInputListener(kit);
	        kit.addMouseListener(mia);
	        kit.addMouseMotionListener(mia);
	        kit.addMouseListener(new CenterMapListener(kit));
	        kit.addMouseWheelListener(new ZoomMouseWheelListenerCenter(kit));
	        kit.addKeyListener(new PanKeyListener(kit));
	        
	        GeoPosition hello = new GeoPosition(35.2,4);

			// Create waypoints from the geo-positions
	        Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
	                new SwingWaypoint("Random", hello )));

	        // Set the overlay painter
	        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
	        swingWaypointPainter.setWaypoints(waypoints);
	        kit.setOverlayPainter(swingWaypointPainter);

	        // Add the JButtons to the map viewer
	        for (SwingWaypoint w : waypoints) {
	            kit.add(w.getButton());
	        }
	        

			JFrame fen = new JFrame("Notre fenêtre");
			fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fen.add(kit);
			fen.pack();
			fen.setSize(800,600);
			fen.setJMenuBar(buildMenu());
			fen.setVisible(true);
	
};
});
};
};