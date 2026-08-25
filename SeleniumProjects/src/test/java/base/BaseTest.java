package base;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	//this class is mainly for setup and teardown
	
	public static WebDriver driver;
	public static Properties prop;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		
		//1.Read the prperty config files ( usi mein to driver load ka details hai)
		
		prop=new Properties();
		
		//2. Connection baithao path k sath 
		
		FileInputStream fis=new FileInputStream("src/test/resources/config.properties");
		prop.load(fis);
		
		
		//3. ab jab driver file connection set ho gya to ab usmein se details lekr browser open kar lo 
		
		String Browsername=prop.getProperty("browser");
		
		if(Browsername.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		
		
		//4. ab window ko maximize karo 
		driver.manage().window().maximize();
		
		//5. ab implicit wait bhi daal do 
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//6. Now open the url 
		
		driver.get(prop.getProperty("url"));
			
		
	}
	
	
	public void CaptureScreenshot(String filename)
	{
		try 
		{  //isse ek folder banta hai jaha screenshot save hpta hai 
			File folder=new File("Screenshots");
			if(!folder.exists())
			{
				folder.mkdir();
			}
			
			//ab timestamp add krna hai screenshot main to uska setup krna hoga 
			
			String Timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			
			
			TakesScreenshot ts=(TakesScreenshot) driver;
			//ab screenshot lene k baad ek file mein store krna hai temporary memory mein
			
			File source=ts.getScreenshotAs(OutputType.FILE);
			
			//ab uss temporary place se computer mein store krne k liye ek file bnaye
			
			File Destination=new File("Screenshots/"+filename+" "+Timestamp+".png");
			org.openqa.selenium.io.FileHandler.copy(source, Destination);
			System.out.println("Screenshot Captured:-" + filename);
				
		}
		
		catch(Exception e)
		{
			System.out.println("Error thrown while capturing the screenshot"+e.getMessage());
		}
	}
	
	
	
	
	@AfterMethod
	public void teardown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		
	}
	
	

}
