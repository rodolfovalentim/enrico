package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.DriverManager;
import enrico.DriverManager.DriverType;

public class File4Go extends DownloadLink {
	
	public File4Go(String link) {
		super(link);
	}

	@Override
	public void download(String path) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(link);
		System.out.println(link);
		clickButton(driver,By.className("gerarlinkdownload"));
		String output = driver.findElement(By.className("ddda")).getAttribute("href");
		DriverManager.free(driver);
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
