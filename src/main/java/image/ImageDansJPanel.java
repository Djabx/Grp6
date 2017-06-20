package image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import carte.Carte;

public class ImageDansJPanel { // Création du JPanel preview

	public static JPanel method(final Carte _carte) {
		JPanel jpan = new JPanel();
		jpan.setBackground(Color.lightGray);
		jpan.setPreferredSize(new Dimension(350, 300));

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.0;
		c.gridx = 0;
		c.insets = new Insets(1, 0, 1, 0);
		jpan.setLayout(new GridBagLayout());
		jpan.add(Preview.creationPreview(_carte.RecupImageWaypointResultat()), c);

		return jpan;
	}
}
