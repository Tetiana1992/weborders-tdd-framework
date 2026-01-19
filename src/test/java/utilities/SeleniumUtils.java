package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumUtils {
    public static String getScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String fileName = name + date + ".png";
        String target = System.getProperty("user.dir")+"/target/extentReport/"+ fileName;
        File finalDestination = new File(target);

        try{
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
