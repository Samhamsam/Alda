package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import backend.DictionarySetGet;
/**
 * GUIAusgabe erstellt eine TextArea und gibt dort die Anzahl der Dictionarys aus.
 * @author mark
 *
 */
public class GUIAusgabe extends JPanel {
	private JTextArea textArea;
	private static final long serialVersionUID = 1L;
	private DictionarySetGet disetget;
	public GUIAusgabe(DictionarySetGet dictsetget) {
		disetget = dictsetget;
		Border border2 = BorderFactory.createTitledBorder("Anzahl");
		textArea = new JTextArea(1, 7);
		textArea.setEditable(false);
		textArea.setBorder(border2);
		this.add(textArea);

	}
	public void anzahl() {
		String i = Integer.toString(disetget.get().size());
		textArea.setText(i);
	}

}
