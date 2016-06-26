package backend;

public class DictionarySetGet {
	private Dictionary<String, String> dictionary;
	
	public DictionarySetGet(Dictionary<String, String> dict){
		dictionary = dict;
	}
	
	//SET
	public void set(Dictionary<String, String> dict) {
		dictionary = dict;
		System.out.println(dictionary.getClass().getSimpleName());
	}
	
	//GET
	public Dictionary<String, String> get() {
		return dictionary;
	}

}

