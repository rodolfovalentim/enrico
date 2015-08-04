package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.DriverManager;
import enrico.DriverManager.DriverType;

public class UppIt extends DownloadLink{

	public UppIt(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean download(String path) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(link);
		clickButton(driver,By.name("method_free"));
		String output = driver.findElement(By.className("m-btn")).getAttribute("href");
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
