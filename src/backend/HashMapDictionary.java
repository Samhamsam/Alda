package backend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashMapDictionary<K extends Comparable<? super K>, V> implements
		Dictionary<K, V> {
	private HashMap<K, V> dict;
	private int size;

	public HashMapDictionary() {
		dict = new HashMap<K, V>();
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
		sb.append(("ErfolgSuche: " + "HashMapDictionary" + " Zeit: " + time
				+ "ns" + "\n"));

		// suchen nicht Erfolgreich
		currentTime = System.nanoTime();
		for (Entry<K, V> eintrag : dict.entrySet())
			search((K) eintrag.getValue());
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("NichtErfolgSuche: " + "HashMapDictionary" + " Zeit: "
				+ time + "ns" + "\n"));
		return sb.toString();
	}

}
