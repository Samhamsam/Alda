package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import backend.DictionarySetGet;
import backend.Functions;
/**
 * GuiMenubar enthält das Menu mit den Menuitems.
 * @author mark
 *
 */
public class GUIMenubar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 1L;
	// Menuleiste
	JMenuBar menuleiste;

	// Menuleiste Element
	JMenu datei;

	// Datei
	JMenuItem lesen, beenden;
	public int size;
	private GUIAusgabe aus;
	private DictionarySetGet digetset;
	public GUIMenubar(GUIAusgabe au, DictionarySetGet dictgetset) {
		aus = au;
		digetset = dictgetset;

		// menuleiste hinzufügen
		menuleiste = new JMenuBar();
		// menuelemente erzeugen
		datei = new JMenu("Datei");

		// untermenuelemente
		lesen = new JMenuItem("Dictionary lesen...");
		lesen.addActionListener(this);
		beenden = new JMenuItem("Dictionary beenden");
		beenden.addActionListener(this);

		// menuelemente hinzufügen
		menuleiste.add(datei);

		// untermenuelemente hinzufügen
		datei.add(lesen);
		datei.add(beenden);

		// ...
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == lesen) {

			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showOpenDialog(this);
			if (returnValue == JFileChooser.ERROR_OPTION) {
				JOptionPane.showMessageDialog(null, "Lesen fehlgeschlagen!");
				return;
			} else if (returnValue == JFileChooser.CANCEL_OPTION) {
				return;
			}
			// read(fc.getSelectedFile());
			LineNumberReader in = null;

			try {
				in = new LineNumberReader(new FileReader(fc.getSelectedFile()));
				String line;
				while ((line = in.readLine()) != null) {
					String[] sf = line.split(" ");
					digetset.get().insert(sf[0], sf[1]);
					size++;

				}
				aus.anzahl();
				in.close();
			} catch (IOException ex) {
				Logger.getLogger(Functions.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		if (source == beenden) {
			if (JOptionPane.showConfirmDialog(beenden,
					"Sind Sie sicher das Sie das Programm beenden wollen?", "",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			;
		}
	}

}
