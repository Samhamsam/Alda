package backend;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.ResolutionSyntax;
import javax.xml.crypto.Data;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentSkipListSet;

public class HashDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	
	

	
	private static class LinkedEntry<K, V>{
		private K key;
		private V value;
		private LinkedEntry<K,V> next;
	}
	
	
	
	
	private LinkedEntry<K, V>[] data;
	
	private int count;
	
	public HashDictionary(){
		data = new LinkedEntry[64];
	}
	public HashDictionary(int initialSize) {
	      if (initialSize <= 0)
	         throw new IllegalArgumentException("Illegal table size");
	      data = new LinkedEntry[initialSize];
	   }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		HashDictionary<String, String> test = new HashDictionary<String,String>();
		test.insert("Hallo", "Hello");
		test.insert("sfd", "Hellasao");
		
		
		System.out.println(test.search("Hallo"));
	}

	@Override
	public V insert(K key, V value) {
		assert key != null;
		int hashZahl = returnhash(key);
		LinkedEntry<K, V> list = data[hashZahl];
		
		while(list != null){
			if (list.key.equals(key))
				break;
			list = list.next;
		}
		
		if(list != null){
			V valueOld = list.value;
			list.value = value;
			return valueOld;
		}else{
			if(count >= 0.75 * data.length){
				resize();
				hashZahl=returnhash(key);
			}
			LinkedEntry<K, V> newnode = new LinkedEntry<K,V>();
			newnode.key = key;
			newnode.value=value;
			newnode.next=data[hashZahl];
			data[hashZahl]=newnode;
			count++;
		}
		return null;
	}

	@Override
	public V search(K key) {
		int hashZahl = returnhash(key);
		LinkedEntry<K, V> list = data[hashZahl];
		while(list != null){
			if(list.key.equals(key)){
				return list.value;
			}
			list = list.next;
		}
		return null;
	}
	

	@Override
	public V remove(K key) {
		int hashZahl = returnhash(key);
		if(data[hashZahl]==null){
			return null;
		}
		
		if(data[hashZahl].key.equals(key)){
			V value=data[hashZahl].value;
			data[hashZahl]=data[hashZahl].next;
			count--;
			return value;
		}
		
		LinkedEntry<K, V> prev = data[hashZahl];
		LinkedEntry<K, V> curr = prev.next;
		while(curr != null && curr.key.equals(key)){
			curr = curr.next;
			prev = curr;
		}
		
		if(curr != null){
			prev.next = curr.next;
			count--;
		}
		
		return null;
	}
	
	public int returnhash(K wort){
		int worthash = wort.hashCode();
		//int hashZahl = (worthash % data.length);
		int hashZahl = (Math.abs(wort.hashCode())) % data.length;
		return hashZahl;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
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

	
	private void resize() {
		LinkedEntry[] newtable = new LinkedEntry[data.length*2];
	      for (int i = 0; i < data.length; i++) {
	    	  LinkedEntry list = data[i]; 
	         while (list != null) {
	        	 LinkedEntry next = list.next;
	            int hash = (Math.abs(list.key.hashCode())) % newtable.length;
	            list.next = newtable[hash];
	            newtable[hash] = list;
	            list = next; 
	         }
	      }
	      data = newtable;
	   }
	
	

	@Override
	@SuppressWarnings("unchecked")
	public String zeit() {
		StringBuilder sb = new StringBuilder();
		long currentTime;
		String time;
		// suchen Erfolgreich
		currentTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
			//search(data[i].key);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("ErfolgSuche: " + "SortedArrayDictionary" + " Zeit: " + time
				+ "ns" + "\n"));

		// suchen nicht Erfolgreich
		currentTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
			//search((K) data[i].value);
		}
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		sb.append(("NichtErfolgSuche: " + "SortedArrayDictionary" + " Zeit: "
				+ time + "ns" + "\n"));
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			LinkedEntry<K, V> list = data[i];
			while(list != null){
				sb.append(list.key).append(" ").append(list.value + "\n");
				list = list.next;
			}
		}

		return sb.toString();
	}

}
