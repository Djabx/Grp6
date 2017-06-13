package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import carte.Carte;
import image.ImageDansJScrollGauche;

public class Projet extends JFrame implements ActionListener {

	static Carte notre_carte;

	private JButton buttonAdd = new JButton("Ajouter Image");
	private JButton buttonExit = new JButton("Quitter");

	private JPanel panel_R = new JPanel();
	private JPanel panel_Down = new JPanel();
	private JScrollPane panel_L;

	public Projet() {

		notre_carte = new Carte();

		panel_L = ImageDansJScrollGauche.method(notre_carte.getImagList(), notre_carte);

		panel_Down.setLayout(new FlowLayout());
		panel_Down.setBackground(Color.green);
		panel_Down.setPreferredSize(new Dimension(800, 50));
		panel_Down.add(buttonExit);
		panel_Down.add(buttonAdd);
		buttonAdd.addActionListener(this);
		buttonExit.addActionListener(this);

		add(panel_L, BorderLayout.WEST);
		add(notre_carte.getMap(), BorderLayout.CENTER);
		add(panel_Down, BorderLayout.NORTH);

		setSize(1600, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Notre Projet");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == buttonAdd) {
			// System.out.println("Import");//
			try {
				this.notre_carte.AjoutImage();
				this.remove(panel_L);
				this.panel_L = ImageDansJScrollGauche.method(notre_carte.getImagList(), notre_carte);
				this.add(panel_L, BorderLayout.WEST);
				this.repaint();
				this.revalidate();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		if (source == buttonExit) {
			this.setVisible(false);
			dispose();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Projet().setVisible(true);
			}
		});
	}

}