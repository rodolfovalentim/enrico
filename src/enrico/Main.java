package enrico;

import java.io.IOException;

import animakai.AnimaKai;

public class Main {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		AnimaKai ank = new AnimaKai(1888);	
		Episode ep1 = ank.getEpisode(3, Quality.MP4);
		ank = new AnimaKai(1912);
		Episode ep2 = ank.getEpisode(3, Quality.MP4);
		
		System.out.println(ep1);
		System.out.println(ep2);
		
		ep1.mergeMirrors(ep2);
		System.out.println(ep1);
	}
}