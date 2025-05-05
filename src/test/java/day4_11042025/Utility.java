package day4_11042025;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class Utility extends ListenersDemo{

	public void captureSS(String name) throws IOException
	{
		TakesScreenshot tks=(TakesScreenshot)driver;
		File src=tks.getScreenshotAs(OutputType.FILE);
		File dest=new File("C:\\Users\\DhanaYuva\\Documents\\GrowSkill\\Selenium\\Screenshot\\"+name+".jpeg");
		FileHandler.copy(src, dest);
	}
}
