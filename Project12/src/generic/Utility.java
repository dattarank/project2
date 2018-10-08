package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	public static String getPropertyvalue(String path, String key) {
		String v = "";
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(path));
			v = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return v;
	}

	public static String getPhoto(WebDriver driver, String folder) {
		Date d = new Date();
		String date = d.toString();
		String dateTime = date.replaceAll(":", "_");
		String path = folder + "/" + dateTime + ".png";
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(path);
			FileUtils.copyFile(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getXLData(String path, String sheet, int r, int c) {
		String value = "";
		Workbook wb;
		try {
			wb = WorkbookFactory.create(new FileInputStream(path));
			value = wb.getSheet(sheet).getRow(r).getCell(c).toString();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return value;

	}

}
