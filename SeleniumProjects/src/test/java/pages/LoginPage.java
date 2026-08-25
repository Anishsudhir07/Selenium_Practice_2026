package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	//pehle saare element search krke rakho
	WebDriver driver;
	
	@FindBy(id="user-name")
	private WebElement txtusername; 
	
	@FindBy(id="password")
	private WebElement txtpassword; 
	
	@FindBy(id="login-button")
	private WebElement btnLogin;
	
	
	
	
	
	//phir ek ek krke saare element jo search kiye unko chote chote functions mein set karo aur logintestpage mein pass kar do 
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this); //// Yeh line elements ko active karti hai
			
	}
	
	
	public void enterUsername(String username)
	{
		
		txtusername.sendKeys(username);
	}
	
	public void enterpassword(String password)
	{
		txtpassword.sendKeys(password);
		
	}
	
	
	public void clickLogin()
	{
		btnLogin.click();
		
	}
	

}
