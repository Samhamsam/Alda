package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import backend.DictionarySetGet;
import backend.HashDictionary;
import backend.HashMapDictionary;
import backend.SortedArrayDictionary;
import backend.TreeMapDictionary;
/**
 * GuiChoiceImp erstellt ein Panel um die verschiedenen Dicitonarys auszuwählen.
 * @author mark
 *
 */
public class GuiChoiceImp extends JPanel implements ActionListener {
	public int art;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> auswahlImp;
	private JLabel l1, l1_5;
	private DictionarySetGet disetget;
	private GUIAusgabe guiausgabe;
	public GuiChoiceImp(DictionarySetGet dictsetget, GUIAusgabe ausgabe) {
		disetget = dictsetget;
		guiausgabe = ausgabe;
		Border border = BorderFactory.createTitledBorder("Auswahl/Info");
		l1 = new JLabel("Implementierung: ");
		l1_5 = new JLabel();
		auswahlImp = new JComboBox<String>();
		auswahlImp.addItem("ArrayDictionary");
		auswahlImp.addItem("TreeMapDictionary");
		auswahlImp.addItem("HashMapDictionary");
		auswahlImp.addItem("HashDictionary");
		auswahlImp.setSelectedItem("ArrayDictionary");
		auswahlImp.addActionListener(this);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1, 100, 10));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		panel1.add(auswahlImp);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		panel2.add(l1);
		panel2.add(l1_5);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2, 30, 2));
		panel3.add(panel1);
		panel3.add(panel2);

		this.add(panel3);
		this.setBorder(border);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (auswahlImp.getSelectedItem() == "ArrayDictionary") {
			disetget.set(new SortedArrayDictionary<String, String>());
			guiausgabe.anzahl();
		} else if (auswahlImp.getSelectedItem() == "TreeMapDictionary") {
			disetget.set(new TreeMapDictionary<String, String>());
			guiausgabe.anzahl();
		} else if (auswahlImp.getSelectedItem() == "HashMapDictionary") {
			disetget.set(new HashMapDictionary<String, String>());
			guiausgabe.anzahl();

		}
		else if (auswahlImp.getSelectedItem() == "HashDictionary") {
			disetget.set(new HashDictionary<String, String>());
			guiausgabe.anzahl();

		}
	}

}
