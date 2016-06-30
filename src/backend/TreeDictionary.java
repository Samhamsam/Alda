package backend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeDictionary<K extends Comparable<? super K>, V> implements
		Dictionary<K, V> {
	private int size;
	static private class Node<K,V>{
		int height;
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;
		private Node(K k, V v){
			height = 0;
			key = k;
			value = v;
			left = null;
			right= null;
		}
	}
	
	@Override
	public V insert(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int getHeight(Node<K, V> p){
		if(p == null)
			return -1;
		else
			return p.height;
	}
	
	private int getBalance(Node<K, V> p){
		if(p == null)
			return 0;
		else
			return getHeight(p.right)-getHeight(p.left);
	}
	
	private Node<K, V> balance(Node<K, V> p){
		if(p == null)
			return null;
		p.height = Math.max(getHeight(p.left), getHeight(p.right))+1;
		if(getBalance(p)==2){
			if(getBalance(p.left) <= 0)
				p = rotateRight(p);
			else
				p = rotateLeftRight(p);	
		}
		else if (getBalance(p) == +2){
			if(getBalance(p.right) >= 0)
				p = rotateLeft(p);
			else
				p = rotateRightLeft(p);
		}
		
		return p;
	}
	
	private Node<K, V> rotateRight(Node<K, V> p){
		assert p.left != null;
		Node<K, V> q = p.left;
		p.left = q.right;
		q.right = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right))+1;
		q.height = Math.max(getHeight(q.left), getHeight(q.right))+1;
		return q;
	}

	
	private Node<K, V> rotateLeft(Node<K, V> p){
		assert p.right != null;
		Node<K, V> q = p.right;
		p.right = q.left;
		q.left = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right))+1;
		q.height = Math.max(getHeight(q.left), getHeight(q.right))+1;
		return q;
	}
	private Node<K,V> rotateLeftRight(Node<K,V> p) {
		assert p.left != null;
		p.left = rotateLeft(p.left);
		return rotateRight(p);
	}
	
	
	private Node<K,V> rotateRightLeft(Node<K,V> p) {
		assert p.right != null;
		p.right = rotateRight(p.right);
		return rotateLeft(p);
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
	@SuppressWarnings("unchecked")
	public String zeit() {
		StringBuilder sb = new StringBuilder();
		long currentTime;
		String time;
		// suchen Erfolgreich
		currentTime = System.nanoTime();
		for (int i = 0; i < size; i++) {
			//search(data[i].key);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("ErfolgSuche: " + "SortedArrayDictionary" + " Zeit: " + time
				+ "ns" + "\n"));

		// suchen nicht Erfolgreich
		currentTime = System.nanoTime();
		for (int i = 0; i < size; i++) {
			//search((K) data[i].value);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("NichtErfolgSuche: " + "SortedArrayDictionary" + " Zeit: "
				+ time + "ns" + "\n"));
		return sb.toString();
	}

}
