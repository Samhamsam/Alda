package backend;

import java.awt.print.Printable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

	private static class Entry<K, V> {
		K key;
		V value;

		Entry(K k, V v) {
			key = k;
			value = v;
		}
	}

	private static final int DEF_CAPACITY = 16;
	private int size;
	private Entry<K, V>[] data;

	
	@SuppressWarnings("unchecked")
	public SortedArrayDictionary() {
		size = 0;
		data = new Entry[DEF_CAPACITY];

	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		if (newCapacity < size)
			return;
		Entry<K, V>[] old = data;
		data = new Entry[newCapacity];
		//(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(old, 0, data, 0, size);
	}

	private int searchKey(K key) {
		int li = 0;
		int re = size - 1;

		while (re >= li) {
			int m = (li + re) / 2;
			if (key.compareTo(data[m].key) < 0)
				re = m - 1;
			else if (key.compareTo(data[m].key) > 0)
				li = m + 1;
			else
				return m; // key gefunden an stelle m?
		}
		return -1; // key nicht gefunden
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public V insert(K key, V value) {
		int i = searchKey(key);

		// Vorhandener Key wird überschrieben
		if (i != -1) {
			V r = data[i].value;
			data[i].value = value;
			return r;
		}

		// Neueintrag
		if (data.length == size) {
			ensureCapacity(2 * size);
		}
		int j = size - 1;
		while (j >= 0 && key.compareTo(data[j].key) < 0) {
			data[j + 1] = data[j];
			j--;
		}
		data[j + 1] = new Entry<K, V>(key, value);
		size++;
		return null;
	}

	@Override
	public V search(K key) {
		int i = searchKey(key);
		if (i >= 0)
			return data[i].value;
		else
			return null;
	}

	@Override
	public V remove(K key) {
		int i = searchKey(key);
		if (i == -1)
			return null;
		// Datensatz loeschen und Lücke schließen
		V r = data[i].value;
		for (int j = i; j < size - 1; j++)
			data[j] = data[j + 1];
		data[--size] = null;
		return r;

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
				insert((K) sf[0], (V) sf[1]);

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
		for (int i = 0; i < size; i++) {
			sb.append(data[i].key).append(" ").append(data[i].value)
					.append("\n");
		}

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
		for (int i = 0; i < size; i++) {
			search(data[i].key);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("ErfolgSuche: " + "SortedArrayDictionary" + " Zeit: " + time
				+ "ns" + "\n"));

		// suchen nicht Erfolgreich
		currentTime = System.nanoTime();
		for (int i = 0; i < size; i++) {
			search((K) data[i].value);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("NichtErfolgSuche: " + "SortedArrayDictionary" + " Zeit: "
				+ time + "ns" + "\n"));
		return sb.toString();
	}

	public static void main(String[] args) {
		SortedArrayDictionary<String, String> me = new SortedArrayDictionary<String, String>();
	}

}
