package Handyspiel;

import javax.swing.JDialog;
import javax.swing.JTextPane;

public class DialogScoreboard extends JDialog
{
	public DialogScoreboard(GUI f) 
	{
		super(f);
		setTitle("Ergebnisse");
		setResizable(false);
		setModal(true);
		JTextPane nachricht = new JTextPane();
		nachricht.setText("Hier kommen die Ergebnisse rein wie weit jeder dran war\n\n" + "Sarah: \n" + "Dave: \n" + "Hurnsohn: \n");
		nachricht.setBackground(getContentPane().getBackground());
		nachricht.setEditable(false);
		add(nachricht);
		pack();
	}
}
