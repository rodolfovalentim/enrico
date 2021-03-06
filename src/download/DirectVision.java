package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enrico.DriverManager;
import enrico.DriverManager.DriverType;

public class DirectVision extends DownloadLink {

	public DirectVision(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean download(String path) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(link);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("download"), "DOWNLOAD 1"));
		String output = driver.findElement(By.className("download")).getAttribute("href");
		DriverManager.free(driver);
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
