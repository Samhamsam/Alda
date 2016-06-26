package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import backend.DictionarySetGet;
import backend.Functions;
/**
 * GuiSuchenLoeschenPanel enthält das Suchen und Loeschen Panel.
 * @author mark
 *
 */
public class GUISuchenLoeschenPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	// private Functions dict;
	private JTextField deutsch;
	private JButton buttonAnwenden;
	private JTextArea textArea;
	private JComboBox<String> sucheloesch;
	GridBagConstraints gbc = new GridBagConstraints();
	Functions functi = new Functions();
	// Anfang
	private GUIAusgabe aus;
	private DictionarySetGet disetget;

	public GUISuchenLoeschenPanel(DictionarySetGet dictsetget, GUIAusgabe au) {
		disetget = dictsetget;
		aus = au;

		// ComboBox
		// String comboBoxList[] = {"Exakte Suche", "Prefix-Suche", "Löschen"};
		sucheloesch = new JComboBox<String>();
		sucheloesch.addItem("Search");
		sucheloesch.addItem("PrintAll");
		sucheloesch.addItem("Delete");
		// prefixsuche.setBounds(2, 2, 10, 5);
		sucheloesch.setSelectedItem("Suchen");
		sucheloesch.addActionListener(this);
		// this.add(prefixsuche);

		// Anwenden Button hinzufügen
		buttonAnwenden = new JButton("Anwenden");
		// this.add(buttonAnwenden);
		buttonAnwenden.addActionListener(this);

		// Panel 1 anlegen
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1));
		panel1.add(new JLabel("Deutsch"));

		// Panel 2 anlegen
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 1));
		deutsch = new JTextField("", 10);
		panel2.add(deutsch);

		// Panel 3 anlegen
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 1));
		panel3.add(sucheloesch);
		// panel3.add(buttonAnwenden);

		// Border für SuchenLöschen
		Border border = BorderFactory.createTitledBorder("Suchen/Löschen");

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1, 1, 6, 3));
		panel4.setBorder(border);
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);
		panel4.add(buttonAnwenden);

		// Border für Ausgabe
		Border border2 = BorderFactory.createTitledBorder("Ausgabe");
		// Ausgabefeld anlegen
		textArea = new JTextArea(5, 30);
		textArea.setBorder(border2);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		// Layout festlegen

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(panel4);
		this.add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		// Text auslesen
		String deut = deutsch.getText();
		if (source == buttonAnwenden) {
			// Text löschen
			// textArea.setText("");

			// Exakte Suche verwenden
			if (sucheloesch.getSelectedItem() == "Search") {
				if (deut.equals("2")) {
					Functions.zeitmessunghilf(disetget.get());
				} else if (disetget.get().search(deut) == null) {
					textArea.setText("Das Wort wurde nicht gefunden!");
				} else if (disetget.get().search(deut) != null) {
					textArea.setText((String) disetget.get().search(deut));
				}

			} else if (sucheloesch.getSelectedItem() == "Delete") {
				if (disetget.get().search(deut) == null) {
					textArea.setText("Das Wort wurde nicht gefunden!");
				} else {
					disetget.get().remove(deut);
					aus.anzahl();
					textArea.setText("Das Wort wurde gelöscht!");

				}
			}
			else if (sucheloesch.getSelectedItem() == "PrintAll") {
				String arrays = disetget.get().toString();
				textArea.setText(arrays);
			}
		}
	}

}
