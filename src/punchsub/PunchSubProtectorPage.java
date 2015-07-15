package punchsub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PunchSubProtectorPage {

	public static String get(WebDriver driver, String relativeUrl) {

		driver.get(relativeUrl);

		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement divBottom = driver.findElement(By.id("download-botao"));
		wait.until(ExpectedConditions.textToBePresentInElement(divBottom,
				"Download"));
		return divBottom.getAttribute("href");
	}

	public static String get(String relativeUrl) {

		WebDriver driver = new PhantomJSDriver();
		driver.get(relativeUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement divBottom = driver.findElement(By.id("download-botao"));
		wait.until(ExpectedConditions.textToBePresentInElement(divBottom,
				"Download"));
		String out = divBottom.getAttribute("href");
		driver.close();

		return out;
	}

	public static String getWithoutWait(WebDriver driver, String relativeUrl) {

		driver.get(relativeUrl);
		WebElement pulaBottom = driver.findElement(By
				.className("download-pular"));
		pulaBottom.click();
		WebElement divBottom = driver.findElement(By.id("download-botao"));
		return divBottom.getAttribute("href");
	}
}
