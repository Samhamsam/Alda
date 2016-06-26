package backend;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeMapDictionary<K extends Comparable<? super K>, V> implements
		Dictionary<K, V> {

	private TreeMap<K, V> dict;
	private int size;

	public TreeMapDictionary() {
		dict = new TreeMap<K, V>();
	}

	@Override
	public V insert(K key, V value) {
		size++;
		return dict.put(key, value);
	}

	@Override
	public V search(K key) {
		return dict.get(key);

	}

	@Override
	public V remove(K key) {
		size--;
		return dict.remove(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void read(File file) {
		LineNumberReader in = null;
		try {
			in = new LineNumberReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null) {
				String[] sf = line.split(" ");
				dict.put((K) sf[0], (V) sf[1]);
			}
			in.close();
		} catch (IOException ex) {
			Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<K, V> eintrag : dict.entrySet())
			sb.append(eintrag.getKey()).append(" ").append(eintrag.getValue())
					.append("\n");
		return sb.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public String zeit() {
		StringBuilder sb = new StringBuilder();
		long currentTime;
		String time;
		// suchen Erfolgreich
		currentTime = System.nanoTime();
		for (Entry<K, V> eintrag : dict.entrySet())
			search(eintrag.getKey());

		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("ErfolgSuche: " + "TreeMapDictionary" + " Zeit: " + time
				+ "ns" + "\n"));

		// suchen nicht Erfolgreich
		currentTime = System.nanoTime();
		for (Entry<K, V> eintrag : dict.entrySet())
			search((K) eintrag.getValue());
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("NichtErfolgSuche: " + "TreeMapDictionary" + " Zeit: "
				+ time + "ns" + "\n"));
		return sb.toString();
	}

}
