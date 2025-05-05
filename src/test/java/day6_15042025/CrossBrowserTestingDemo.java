package day6_15042025;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTestingDemo {

	WebDriver driver=null;
	@Parameters("Browser")
	@Test(priority =0)
	public void launchBroswer(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		} 
	}
	
	@Test(priority=1)
	public void app()
	{
		driver.get("https://www.facebook.com/login.php/");
	}
}
