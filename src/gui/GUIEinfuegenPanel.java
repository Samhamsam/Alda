package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import backend.Dictionary;
import backend.DictionarySetGet;
/**
 * GuiEinfuegenPanel enthält das EinfuegenPanel.
 * @author mark
 *
 */
public class GUIEinfuegenPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField deutsch;
	private JTextField englisch;
	private JButton buttonEinfuegen;
	GridBagConstraints gbc = new GridBagConstraints();
	private DictionarySetGet disetget;
	private GUIAusgabe aus;

	public GUIEinfuegenPanel(Dictionary<String, String> dict, GUIAusgabe au,
			DictionarySetGet dictsetget) {
		disetget = dictsetget;
		aus = au;

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));
		panel1.add(new JLabel("Deutsch"));
		panel1.add(new JLabel("Englisch"));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		deutsch = new JTextField("", 15);
		panel2.add(deutsch);
		englisch = new JTextField("", 15);
		panel2.add(englisch);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 1));

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2, 1));

		buttonEinfuegen = new JButton("Einfügen");
		// this.add(buttonEinfuegen);
		buttonEinfuegen.addActionListener(this);

		Border border = BorderFactory.createTitledBorder("Einfügen");
		this.setBorder(border);
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(buttonEinfuegen);
		this.add(panel4);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonEinfuegen) {

			String deut = deutsch.getText();
			String eng = englisch.getText();
			if (deut.equals("") || eng.equals("")) {
				return;
			}
			disetget.get().insert(deut, eng);
			aus.anzahl();
			deutsch.setText("");
			englisch.setText("");
		}

	}

	public static void main(String[] args) {

	}

}
