package gui;

import java.awt.FlowLayout;
import java.io.PrintStream;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.*;

import backend.Dictionary;
import backend.DictionarySetGet;
import backend.SortedArrayDictionary;
/**
 * Gui ist die Hauptanwendung um das Programm zu starten.
 * @author mark
 *
 */
public class Gui extends JFrame {

	private static Dictionary<String, String> dictionary;
	private static final long serialVersionUID = 1L;

	public Gui(Dictionary<String, String> dict, GUIAusgabe guiAusgabe, DictionarySetGet dictsetget) {
		GUIMenubar jmenu = new GUIMenubar(guiAusgabe, dictsetget);
		GUIEinfuegenPanel einfuegenPanel = new GUIEinfuegenPanel(dictionary, guiAusgabe, dictsetget);
		GuiChoiceImp imple = new GuiChoiceImp(dictsetget, guiAusgabe);
		GUISuchenLoeschenPanel suchloeschPanel = new GUISuchenLoeschenPanel(dictsetget, guiAusgabe);
		final JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		this.setJMenuBar(jmenu.menuleiste);
		this.setLayout(new FlowLayout());
		this.add(mainPanel);
		this.add(einfuegenPanel);
		this.add(imple);
		this.add(suchloeschPanel);
		this.add(guiAusgabe);
		mainPanel.add(einfuegenPanel);
		mainPanel.add(imple);
		mainPanel.add(suchloeschPanel);
		mainPanel.add(guiAusgabe);
		this.setContentPane(mainPanel);
		// Sonstige Eigenschaften des Hauptfenster setzen:
		this.setTitle("Dictionary");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);

	}

	public static void main(String[] args) {
		DictionarySetGet dictsetget = new DictionarySetGet(dictionary);
		GUIAusgabe guiAusgabe = new GUIAusgabe(dictsetget);
		dictsetget.set(new SortedArrayDictionary<String, String>());
		guiAusgabe.anzahl();
		new Gui(dictionary, guiAusgabe, dictsetget);
	}

}
