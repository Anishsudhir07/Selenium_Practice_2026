package tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	
	//isme basetest class ko extend krke loginpage mein kya kya kaam hoga wo rahega . its for functionality part
	
	

	
	@Test
	public void successfullogin()throws Throwable
	{
		LoginPage loginpage=new LoginPage(driver);
		
		loginpage.enterUsername(prop.getProperty("username"));
		loginpage.enterpassword(prop.getProperty("password"));
		loginpage.clickLogin();
		
		Thread.sleep(5000);
		
		
		
		
		
		
		//after running the test , verify if the test did run successfully 
		
		String actualHeaderText = driver.findElement(By.className("title")).getText();
	    Assert.assertEquals(actualHeaderText, "Products", "Login Unsuccessful!");
		
	    CaptureScreenshot("Loginsuccess");
		
		
	}
	
	

	


}
