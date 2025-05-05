package day10_22042025;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class HybridDrivenFramework {

	@Test
	public void executionMethod() throws IOException
	{
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		OrangeHRM_Login_POM_PageFact lin=new OrangeHRM_Login_POM_PageFact(driver);
		OrangeHRM_Logout_POM lout=new OrangeHRM_Logout_POM(driver);
		
		String filePath = "C:\\Users\\DhanaYuva\\Documents\\GrowSkill\\TestNG\\TestingData_OrangeHRM.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet dataSheet = workbook.getSheet("Datas");
	    XSSFSheet keySheet=workbook.getSheet("Keywords");

		int dRows = dataSheet.getLastRowNum();
		System.out.println("Total no of Rows : " + dRows);
		
		int kRows = keySheet.getLastRowNum();
		System.out.println("Total no of Rows : " + kRows);

		for (int dr = 1; dr <= dRows; dr++) {
			XSSFRow dRow = dataSheet.getRow(dr);
			XSSFCell usernm = dRow.getCell(0);
			XSSFCell pass = dRow.getCell(1);
			XSSFCell result=dRow.createCell(2);
			
			System.out.println("UserName----->"+usernm+"        Password----->"+pass);
			try {
			for(int kr=1;kr<=kRows;kr++)
			{
				XSSFRow kRow=keySheet.getRow(kr);
				XSSFCell key=kRow.getCell(1);
				XSSFCell match=kRow.createCell(2);
				
				System.out.println("Keywords-----> "+key);
				
				switch(key.toString())
				{
				case "url": 
					lin.url();
					System.out.println("URL Keyword is Matched");
					match.setCellValue("URL Keyword is Matched");
					break;
					
				case "enterUserName": 
					lin.enterUserName(usernm.toString());
					System.out.println("enterUserName Keyword is Matched");
					match.setCellValue("enterUserName Keyword is Matched");
					break;
					
				case "enterPassword": 
					lin.enterPassword(pass.toString());
					System.out.println("enterPassword Keyword is Matched");
					match.setCellValue("enterPassword Keyword is Matched");
					break;
					
				case "clickOnLoginBtn": 
					lin.clickOnLoginButton();
					System.out.println("clickOnLoginButton Keyword is Matched");
					match.setCellValue("clickOnLoginButton Keyword is Matched");
					break;
					
				case "clickOnProfile": 
					lout.clickOnProfile();
					System.out.println("clickOnProfile Keyword is Matched");
					match.setCellValue("clickOnProfile Keyword is Matched");
					break;
					
				case "clickOnLogoutLink": 
					lout.clickOnLogoutLink();
					System.out.println("clickOnLogoutLink Keyword is Matched");
					match.setCellValue("clickOnLogoutLink Keyword is Matched");
					break;
					
					default :System.out.println("Invalid Keyword");
				}

			}
			System.out.println("Valid Data");
			result.setCellValue("Valid Data");
			}
			catch(Exception e)
			{
				System.out.println("Invalid Data");
				result.setCellValue("Invalid Data");
			}
		}
		fis.close();
		//writing in the file
		FileOutputStream fos=new FileOutputStream(filePath);
		workbook.write(fos);
	}
}
