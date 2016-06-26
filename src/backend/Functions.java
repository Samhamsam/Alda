package backend;

import java.io.File;

public class Functions {

	public static void zeitmessunghilf(Dictionary<String, String> dict) {

		dict = new SortedArrayDictionary<String, String>();
		zeitmessung(dict);
		dict = new TreeMapDictionary<String, String>();
		zeitmessung(dict);
		dict = new HashMapDictionary<String, String>();
		zeitmessung(dict);
	}

	public static void zeitmessung(Dictionary<String, String> dict) {

		long currentTime;
		String time;
		File File100 = new File("dtengl.txt");
		File File50 = new File("dtengl50.txt");
		currentTime = System.nanoTime();
		dict.read(File100);
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		System.out.println("File100: " + dict.getClass().getSimpleName()
				+ " Zeit: " + time + "ns");
		System.out.println(dict.zeit());

		currentTime = System.nanoTime();
		dict.read(File50);
		time = Long.toString((System.nanoTime() - currentTime) / (1000));
		System.out.println("File50: " + dict.getClass().getSimpleName()
				+ " Zeit: " + time + "ns");

		System.out.println(dict.zeit());
		return;
	}
}
