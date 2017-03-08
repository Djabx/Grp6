import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Import {

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
}
