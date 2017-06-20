package image;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class Preview extends JPanel { // Récupération de l'image cliquée +
										// affichage dans le panel preview
	ImageIcon[] images;

	public Preview(ArrayList<image.Image> imagList) {

		images = new ImageIcon[imagList.size()];
		Integer[] intArray = new Integer[imagList.size()];
		for (int i = 0; i < imagList.size(); i++) {
			intArray[i] = new Integer(i);
			String path = imagList.get(i).file.getAbsolutePath();
			ImageIcon imageIcon = new ImageIcon(path);
			Image image = imageIcon.getImage(); // transforme l'image
			Image newimg = image.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			images[i] = imageIcon;

		}

		JComboBox affList = new JComboBox(intArray);
		ComboBoxRenderer rendu = new ComboBoxRenderer();
		rendu.setPreferredSize(new Dimension(330, 300));
		affList.setRenderer(rendu);
		affList.setMaximumRowCount(3);

		add(affList, BorderLayout.PAGE_START);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	protected static ImageIcon CreationIconeImage(String path) {
		if (path != null) {
			return new ImageIcon(path);
		} else {
			System.err.println("Fichier introuvable : " + path);
			return null;
		}
	}

	public static JComponent creationPreview(ArrayList<image.Image> imagList) {
		JComponent contenu = new Preview(imagList);
		return contenu;
	}

	class ComboBoxRenderer extends JLabel implements ListCellRenderer {

		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		// Permet de récupérer l'image sélectionner dans le panel preview
		public Component getListCellRendererComponent(JList liste, Object valeur, int indice, boolean estSelectionne,
				boolean estChoisi) {
			if (!liste.isSelectionEmpty()) {
				int indiceselection = ((Integer) valeur).intValue();

				if (estSelectionne) {
					setBackground(liste.getSelectionBackground());
					setForeground(liste.getSelectionForeground());
				} else {
					setBackground(liste.getBackground());
					setForeground(liste.getForeground());
				}

				ImageIcon icone = images[indiceselection];
				setIcon(icone);

				return this;
			} else {
				return this;
			}
		}
	}
}
