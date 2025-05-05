package day2_08042025;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OrangeHRM_AnnotationsDemo {

	WebDriver driver;

	@BeforeSuite
	public void pageLoad() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}

	@Test(priority = 1)
	public void login()
	{
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(priority = 2)
	public void verifyLogin() {
		String dash = driver.findElement(By.xpath("//div[@id='app']/descendant::h6")).getText();
		Assert.assertEquals(dash, "Dashboard");
	}
	
	@Test(priority = 3)
	public void logout()
	{
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//ul[@class='oxd-dropdown-menu']/child::li[4]/child::a")).click();
		
	}
	
	@AfterSuite
	public void close()
	{
		driver.quit();
	}
}
