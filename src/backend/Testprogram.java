package backend;

public class Testprogram {
	private static Dictionary<String, String> di;
	public Testprogram(Dictionary dict, DictionarySetGet dictsetget){
		di = dict;
		dictsetget.set(new TreeMapDictionary<String, String>());
		insert(dictsetget.get());
		System.out.println(dictsetget.get().size());
		System.out.println(dictsetget.get().getClass().getSimpleName());
		dictsetget.set(new HashMapDictionary<String, String>());
		System.out.println(dictsetget.get().getClass().getSimpleName());
		
	}
	
	private void insert(Dictionary dict){
		dict.insert("sdf", "dfsf");
	}
	
	public static void main(String[] args){
		
		DictionarySetGet disetget = new DictionarySetGet(di);
		Testprogram test = new Testprogram(di, disetget);
	}

}


/*
 * Test der verscheidenen Dictionary-Implementierungen
 *
* O. Bittel; 29.02.2016


package dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DictionaryTest {
	
	public static void main(String[] args) {
		List<Dictionary<String, String>> dictList = Arrays.asList(
				new SortedArrayDictionary<>(),
				new HashDictionary<>(7),
				new BinaryTreeDictionary<>(),
				new MapDictionary<>(new HashMap<>())
		);
	
		for (Dictionary<String, String> dict : dictList) {
			System.out.println("teste " + dict.getClass());
			System.out.println(dict.insert("gehen", "go") == null);
			System.out.println(dict.insert("gehen", "walk").equals("go"));
			System.out.println(dict.search("gehen").equals("walk"));
			System.out.println(dict.remove("gehen").equals("walk"));
			System.out.println(dict.remove("gehen") == null);
		}
		
		// Test fÃ¼r BinaryTreeDictionary mit prettyPrint (siehe Aufgabe 10; AIN 2)
		// Pruefen Sie die Ausgabe von prettyPrint auf Papier nach.
		Dictionary<Integer, Integer> btd = new BinaryTreeDictionary<>();
		btd.insert(10,0);
		btd.insert(20,0);
		btd.insert(30,0);
		btd.prettyPrint();
		btd.insert(40,0);
		btd.insert(50,0);
		btd.prettyPrint(); 
		btd.insert(21,0);
		btd.prettyPrint(); 
	}
}
*/