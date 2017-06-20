package image;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JChooserPreviewPanel extends JPanel { // Affichage d'un preview
													// dans le JFileChooser
	private ImagePreviewer previewer;

	public JChooserPreviewPanel() {
		JLabel label = new JLabel("Image choisie :", SwingConstants.CENTER);
		previewer = new ImagePreviewer();

		setPreferredSize(new Dimension(150, 150));
		setBorder(BorderFactory.createEtchedBorder());

		setLayout(new BorderLayout());

		label.setBorder(BorderFactory.createEtchedBorder());
		add(label, BorderLayout.NORTH);
		add(previewer, BorderLayout.CENTER);
	}

	public ImagePreviewer getImagePreviewer() {
		return previewer;
	}

	class ImagePreviewer extends JLabel {
		public void configure(File f) {
			Dimension size = getSize();
			Insets insets = getInsets();
			ImageIcon icon = new ImageIcon(f.getPath());

			int width = size.width - insets.left - insets.right;
			int height = size.height - insets.top - insets.bottom;
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
		}
	}

}