package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginTests extends TestBase {



    @Test (groups = "smoke")
    public void testValidCredentials() throws IOException {
        Properties properties = new Properties();
        properties.load(new BufferedReader(new FileReader("config.properties")));
        String url = properties.getProperty("url");
        System.out.println(url);
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));
        System.out.println(properties.getProperty("browser"));

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.ENTER);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Web Orders");
    }
    @Test(groups = "smoke")
    public void testInvalidCredentials(){
        Driver.getDriver().get("https://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(ConfigReader.getProperty("username"), Keys.TAB, "invalid", Keys.ENTER);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Web Orders");
    }
    @Test
    public void testValidCredentialsNoUsername(){
        Driver.getDriver().get("https://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("", Keys.TAB, "invalid", Keys.ENTER);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Web Orders");
    }
    @Test
    public void testValidCredentialsNoPassword(){
        Driver.getDriver().get("https://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(ConfigReader.getProperty("username"), Keys.TAB, "", Keys.ENTER);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Web Orders");
    }
    @Test
    public void testValidCredentialsNoCred(){
        Driver.getDriver().get("https://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("", Keys.TAB, "", Keys.ENTER);
        Assert.assertEquals(Driver.getDriver().getTitle(), "Web Orders");
    }

}
