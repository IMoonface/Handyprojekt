package Handyspiel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class GUI extends JFrame 
{
	JTextArea frage;
	JScrollPane fScroll;
	JButton naechste;
	JButton abgeben;
	JTextField antwort;
	Random r = new Random();
	ArrayList <Integer> ergebnisse = new ArrayList<Integer>();
	static int eintrag, merke = -1, wert, fertig = 0;
	public GUI() 
	{
		//Kann man später noch anpassen hier erstmal als Test
		setLayout(null);

		frage = new JTextArea();
		frage.setEditable(false);
		fScroll = new JScrollPane(frage);
		fScroll.setBounds(10,10,200,20);
		fScroll.setVisible(true);
		add(fScroll);

		naechste = new JButton("Naechste Frage");
		naechste.setBounds(220, 10, 150, 20);
		naechste.addActionListener(new ActionHandler());
		add(naechste);

		abgeben = new JButton("Abgeben");
		abgeben.setBounds(220, 40, 150, 20);
		abgeben.addActionListener(new ActionHandler());
		abgeben.setEnabled(false);
		abgeben.addActionListener(new ActionHandler());
		add(abgeben);

		antwort = new JTextField();
		antwort.setBounds(10, 40, 200, 20);
		antwort.addCaretListener(new CaretHandler());
		antwort.addKeyListener(new KeyHandler());
		antwort.setEnabled(false);
		add(antwort);
	}
	
	private class ActionHandler implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == naechste) 
			{
				neachsteF(fragen());
				antwort.setEnabled(true);
				naechste.setEnabled(false);
			}
			else if(e.getSource() == abgeben) 
			{
				fertig += 1;
				wert = Integer.valueOf(String.valueOf(antwort.getText()));
				//Damit der Button nicht 2 mal aufgefuehrt wird (kann man bestimmt besser loesen schau da mal drueber)
				if (fertig % 2 == 0) {
					abgeben.setEnabled(false);
					ergebnisse.add(wert);
					antwort.setText("");
					Spielfuehrer(ergebnisse, antworten());
				}
			}
		}
	}

	private class CaretHandler implements CaretListener 
	{
		@Override
		public void caretUpdate(CaretEvent e) 
		{
			String s = String.valueOf(antwort.getText());
			s = s.trim();
			if(s.isEmpty()) 
			{
				abgeben.setEnabled(false);
			}
			else 
			{
				abgeben.setEnabled(true);
			}
		}
	}
	
	private void neachsteF (String feld []) 
	{
		eintrag = r.nextInt(5);
		while (merke==eintrag) {
			eintrag = r.nextInt(5);
		}
		frage.setText(feld[eintrag]);
		merke = eintrag;
	}
	//Zum Testen: Um die Fragen abzuspeichern
	public String [] fragen () 
	{
		String fragen [] = 
		{
			"Frage 1. Was ist ...",
			"Frage 2. Wo ist ...",
			"Frage 3. Wie ist ...",
			"Frage 4. Wer ist ...",
			"Frage 5. Warum ist ...",			
		};
		return fragen;
	}
	//Zum Testen: Um die Antworten abzuspeichern
	public String [] antworten () 
	{
		String antworten [] = 
		{
			"Antwort: 1",	
			"Antwort: 2",		
			"Antwort: 3",		
			"Antwort: 4",		
			"Antwort: 5",		
		};
		return antworten;
	}
	
	void Spielfuehrer(ArrayList <Integer> x, String [] antworten) 
	{
		//Test obs klappt (Die Arraylist kann man dann in den JDialog benutzen)
		System.out.println(ergebnisse);
		System.out.println(antworten[eintrag]);
		
		//Genereller Dialog Test
		DialogScoreboard score = new DialogScoreboard(GUI.this);
		score.setLocationRelativeTo(GUI.this);
		score.setVisible(true);
		
		naechste.setEnabled(true);
		antwort.setEnabled(false);
	}
	
	private class KeyHandler implements KeyListener 
	{	
		@Override
		public void keyReleased(KeyEvent e) 
		{
			String text = antwort.getText();
			int laenge = text.length();
			if (laenge > 0) 
			{
				char zeichen = text.charAt(laenge-1);
	        	if (!((zeichen >= '0') && (zeichen <= '9'))) 
	        	{
	        		JOptionPane.showMessageDialog(GUI.this, "Bitte nur Zahlen eingeben!") ;
	        		antwort.setText("");
	        	}
			}	
		}

		@Override
		public void keyTyped(KeyEvent e) 
		{
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			
		}
	}
}
