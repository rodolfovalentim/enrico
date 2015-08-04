package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enrico.DriverManager;
import enrico.DriverManager.DriverType;

public class File4Go extends DownloadLink {
	
	public File4Go(String link) {
		super(link);
	}

	@Override
	public boolean download(String path) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(link);
		if (deleted(driver)){
			DriverManager.free(driver);
			return false;
		}
		clickButton(driver,By.className("gerarlinkdownload"));
		String output = driver.findElement(By.id("download_link")).getAttribute("href");
		DriverManager.free(driver);
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean deleted(WebDriver driver) {
		System.out.println(driver.findElement(By.id("download_area")).findElement(By.tagName("b")).getText());
		if (driver.findElement(By.id("download_area")).findElement(By.tagName("b")).getText().equals("INATIVIDADE + 90 DIAS SEM DOWNLOAD"))
			return true;
		else
			return false;
	}
}
