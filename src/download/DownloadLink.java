package download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DownloadLink {
	
	protected String link;
	
	public DownloadLink(String link){
		this.link = link;
	}
	
	protected static void clickButton (WebDriver driver, By getMethod){
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getMethod));
		element.click();
	}
	
	protected static WebElement waitFor (WebDriver driver, By getMethod){
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(getMethod));
	}
	
	protected static void download (String downloadLink, String path, String fileName) throws IOException{
		URL website = new URL(downloadLink);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(path+fileName);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}
	
	public abstract void download(String path);

	public static DownloadLink create(String downLink) {
		switch(downLink.split("/")[2]){
			case("mega.co.nz"):
				return new Mega(downLink);
			case("mega.nz"):
				return new Mega(downLink);
			case("www.sizedrive.com"):
				return new File4Go(downLink);
			case("www.file4go.com"):
				return new File4Go(downLink);
			case("uppit.com"):
				return new UppIt(downLink);
			case("www.solidfiles.com"):
				return new SolidFiles(downLink);
			case("www.visionshare.com.br"):
				return new DirectVision(downLink);
		}
		return null;
	}
	
}
