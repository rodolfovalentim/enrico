package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enrico.DriverManager;
import enrico.DriverManager.DriverType;

public class SolidFiles extends DownloadLink {

	public SolidFiles(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean download(String path) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(link);
		String output = driver.findElement(By.id("ddl-text")).getAttribute("href");
		DriverManager.free(driver);
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
