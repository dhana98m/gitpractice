package day8_19042025;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import day7_18042025.OrangeHRM_Login_POM_PageFact;
import day7_18042025.OrangeHRM_Logout_POM;

public class DataDriverFrameworkDemo {

	@Test
	public void executionMethod() throws InterruptedException, IOException {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		OrangeHRM_Login_POM_PageFact lin=new OrangeHRM_Login_POM_PageFact(driver);
		OrangeHRM_Logout_POM lout=new OrangeHRM_Logout_POM(driver);
		
		lin.url();
		Thread.sleep(5000);
		
		String filePath = "C:\\Users\\DhanaYuva\\Documents\\GrowSkill\\TestNG\\TestingData_OrangeHRM.xlsx";
		// To read the file
		FileInputStream fis = new FileInputStream(filePath);
		// to use the below classes add jar files
		// 1.google 2.search for mvn repository 3.search for poi-ooxml 4.choose the
		// first option and click on recent version
		// copy the dependency and paste it on pom.xml file and click on CNTRL+S jar
		// files downloaded
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Datas");

		int rows = sheet.getLastRowNum();
		System.out.println("Total no of Rows : " + rows);

		for (int i = 1; i <= rows; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell usernm = row.getCell(0);
			XSSFCell pass = row.getCell(1);
			
			System.out.println("UserName----->"+usernm+"        Password----->"+pass);
			try {
			lin.enterUserName(usernm.toString());
			lin.enterPassword(pass.toString());
			lin.clickOnLoginButton();
			lout.clickOnProfile();
			lout.clickOnLogoutLink();
			System.out.println("Valid Data");
			}
			catch(Exception e)
			{
				System.out.println("Invalid Data");
			}
		}
		
		fis.close();
	}

}
