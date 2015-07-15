package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class File4Go extends DownloadLink {
	
	public File4Go(String link) {
		super(link);
	}

	@Override
	public void download(String path) {
		WebDriver driver = new PhantomJSDriver();
		driver.get(link);
		System.out.println(link);
		clickButton(driver,By.className("gerarlinkdownload"));
		String output = driver.findElement(By.className("ddda")).getAttribute("href");
		System.out.println(output);
		driver.close();
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
