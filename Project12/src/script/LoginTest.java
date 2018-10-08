package script;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test
	public void testLogin() throws Exception {
		LoginPage lp=new LoginPage(driver);
		String sheet="Sheet1";
		String un=Utility.getXLData(XL_DATA,sheet , 1, 0);
		lp.setUserName(un);
		String pass=Utility.getXLData(XL_DATA, sheet, 1, 1);
		lp.setPassword(pass);
		Thread.sleep(3000);
		lp.clickLogin();
		WebDriverWait wait=new WebDriverWait(driver,ETO);
		wait.until(ExpectedConditions.titleIs("actiTIME - Enter Time-Track"));
		String aTitle=driver.getTitle();
		String eTitle="actiTIME - Enter Time-Track";
		Assert.fail();
		//Assert.assertEquals(aTitle, eTitle);
	
	}

}
