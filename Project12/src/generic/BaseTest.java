package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
@Listeners(Result.class)
public abstract class BaseTest implements IAutoConst {
	public WebDriver driver;
	public String url=Utility.getPropertyvalue(CONFIG_PATH, "URL");
	String iwait=Utility.getPropertyvalue(CONFIG_PATH, "ITO");
	public long ITO=Long.parseLong(iwait);
	String ewait=Utility.getPropertyvalue(CONFIG_PATH, "ETO");
	public long ETO=Long.parseLong(ewait);
	
	static {
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	@BeforeMethod
	public void openApp() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		driver.get(url);	
	}
	
	@AfterMethod
	public void closeApp(ITestResult result) {
		String name=result.getName();
		int status = result.getStatus();
		if(status==2) {
			Utility.getPhoto(driver, PHOTO_PATH);
			Reporter.log("test:"+name+" Failed",true);
		}else {
			Reporter.log("Test:"+name+" passed",true);
		}
		driver.close();
	}

}
