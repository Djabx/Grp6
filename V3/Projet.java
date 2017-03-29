package GRP.geotool;

import org.jxmapviewer.*;
import org.jxmapviewer.JXMapKit.DefaultProviders;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Projet {
 
private static JMenuBar buildMenu() {
	JMenuBar menu = new JMenuBar();
	JMenu mfichier = new JMenu("Importer un fichier");
	JMenuItem iquitter = new JMenuItem("Importer");
	iquitter.addActionListener(new ActionListener() {
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
				JXMapKit kit2= new JXMapKit();
				kit2.setAddressLocation(new GeoPosition(40, 2));
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
			JXMapKit kit = new JXMapKit();
			kit.setDefaultProvider(DefaultProviders.OpenStreetMaps);

			TileFactoryInfo info = new OSMTileFactoryInfo();
			TileFactory tf = new DefaultTileFactory(info);
			kit.setTileFactory(tf);
			kit.setZoom(14);
			kit.setAddressLocation(new GeoPosition(43.924, 2.15));
			kit.getMainMap().setDrawTileBorders(true);
			kit.getMainMap().setRestrictOutsidePanning(true);
			kit.getMainMap().setHorizontalWrapped(false);
			
			((DefaultTileFactory) kit.getMainMap().getTileFactory()).setThreadPoolSize(8);
			
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