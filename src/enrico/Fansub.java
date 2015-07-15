package enrico;

import java.util.ArrayList;

public abstract class Fansub {
	String name;
	String website;
	ArrayList<String> animes;
	
	public static boolean search(){
		return true;
	}
	
	public static java.util.List<Episode> getAll(String anime){
		return new java.util.ArrayList<Episode>();
	}
	
	public static Episode getLatestIfThereIs(){
		return new Episode("", 0);
		
	}
}
