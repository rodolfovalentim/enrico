package download;

import java.io.IOException;
import java.net.URLDecoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DirectVision extends DownloadLink {

	public DirectVision(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void download(String path) {
		WebDriver driver = new PhantomJSDriver();
		driver.get(link);
		String output = driver.findElement(By.className("download")).getAttribute("href");
		driver.close();
		try {
			download (output, path, URLDecoder.decode(output.split("/")[output.split("/").length-1],"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
