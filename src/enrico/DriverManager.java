package enrico;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverManager {
	
	public enum DriverType{
		PHANTOMJS, HTML_UNIT, FIREFOX
	}
	
	private static class DriverStruct {
		private boolean busy;
		private DriverType type;
		private WebDriver driver;
		
		public DriverStruct (WebDriver driver,boolean busy){
			this.setDriver(driver);
			this.busy = busy;
		}
		
		public DriverStruct (WebDriver driver){
			this(driver,true);
		}
		
		public void setDriver(WebDriver driver){
			if (driver instanceof PhantomJSDriver || driver instanceof FixedPhantomJSDriver)
				type = DriverType.PHANTOMJS;
			else if (driver instanceof HtmlUnitDriver)
				type = DriverType.HTML_UNIT;
			else if (driver instanceof FirefoxDriver)
				type = DriverType.FIREFOX;
			else
				type = null;
			
			this.driver = driver;
		}
		
		public WebDriver getDriver(){
			return driver;
		}
		
		public void setBusy(){
			busy = true;
		}
		
		public void setAvailable(){
			busy = false;
		}
		
		public boolean isAvailabe(){
			return !busy;
		}
		
		public DriverType getType(){
			return this.type;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			else if (obj instanceof WebDriver)
				return this.getDriver().equals(driver);
			else if (obj instanceof DriverStruct)
				return this.getDriver().equals(((DriverStruct) obj).getDriver());
			else 
				return false;
		}
		
	}
	
	private static ArrayList<DriverStruct> drivers = new ArrayList<DriverStruct>();
	
	public static WebDriver newDriver(){
		return newDriver(DriverType.HTML_UNIT);
	}
	
	public static void newDriver(WebDriver driver){
		drivers.add(new DriverStruct(driver));
	}
	
	public static WebDriver getDriver(DriverType type){
		for (DriverStruct driver : drivers)
			if (driver.isAvailabe() && (driver.getType() == type)){
				driver.setBusy();
				return driver.getDriver();
			}
		return null;
	}
	
	public static WebDriver getDriver(){
		for (DriverStruct driver : drivers)
			if (driver.isAvailabe()){
				driver.setBusy();
				return driver.getDriver();
			}
		return null;
	}
	
	public static boolean available(){
		return !(getDriver() == null);
	}
	
	public static boolean available(DriverType type){
		return !(getDriver(type) == null);
	}
	
	public static WebDriver newDriver(DriverType type){
		WebDriver driver;
		switch (type){
			case PHANTOMJS:
				driver = new PhantomJSDriver();
				break;
			case HTML_UNIT:
				driver = new HtmlUnitDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			default:
				return null;
		}
		drivers.add(new DriverStruct(driver));
		return driver;
	}
	
	public static void free(WebDriver driver){
		for (DriverStruct ds : drivers){
			if(ds.equals(driver)){
				ds.setAvailable();
			}
		}
	}
	
	public static void deleteDriver(WebDriver driver){
		for (DriverStruct ds : drivers)
			if(ds.equals(driver) && ds.isAvailabe()){
				drivers.remove(ds);
				break;
			}
	}
	
	public static void deleteDriver(){
		for (DriverStruct ds : drivers)
			if(ds.isAvailabe()){
				drivers.remove(ds);
				break;
			}
	}
	
	public static void removeAvailableDrivers(){
		for (DriverStruct ds : drivers)
			if(ds.isAvailabe()){
				drivers.remove(ds);
			}
	}
	
	public static void removeBusyDrivers(){
		for (DriverStruct ds : drivers)
			if(!ds.isAvailabe()){
				drivers.remove(ds);
			}
	}
	
	public static void removeAllDrivers(){
		drivers.clear();
	}
}