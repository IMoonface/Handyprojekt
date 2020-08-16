package Handyspiel;

import javax.swing.JFrame;

//Vorneweg müsste man noch festlegen wer welcher Spieler ist, hier starten wir direkt mit den Game
public class Main {
	public static void main(String[] args) {
		JFrame jf = new GUI();
		jf.setVisible(true);
		jf.setTitle("Fragespiel");
		jf.setSize(400, 110);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);	
	}
}
