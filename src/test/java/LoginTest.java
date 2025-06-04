import io.opentelemetry.api.common.Value;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoginTest {

    public static WebDriver driver;

    @BeforeTest
    public static void  setup(){
        driver=new EdgeDriver();

    }

    @Test(dataProvider = "login-data",dataProviderClass = LoginDataProvider.class,priority = 1)
    public static void testlogin(String Email, String Password ) throws IOException {

    try{
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        driver.manage().window().maximize();
        WebElement mail_id = driver.findElement(By.xpath("//input[@name='login[username]']"));
        mail_id.click();
        mail_id.sendKeys(Email);


        WebElement pwd;
        pwd = driver.findElement(By.xpath("//input[@name='login[password]']"));
        pwd.click();
        pwd.sendKeys(Password);

        driver.findElement(By.xpath("//button[@class='action login primary']//span[text()='Sign In']")).click();

    } catch (Exception error) {
        TakesScreenshot success_screenshot=(TakesScreenshot)driver;
            File source=success_screenshot.getScreenshotAs(OutputType.FILE);
            File Target=new File("C:\\Users\\gowth\\IdeaProjects\\TDD_Project\\target\\failure_screenshot\\failure_page.png");
            FileUtils.copyFile(source, Target);
        System.out.println("Exception is : "+error);

    }


    }

    private static int screenshotCounter = 1;

    @AfterMethod
    public static void capture_Screenshot(ITestResult result) throws IOException {

        String status = (result.getStatus() == ITestResult.FAILURE) ? "Failure_" : "Success_";
        if(!status.equals("Failure_")) {

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File Target = new File("C:\\Users\\gowth\\IdeaProjects\\TDD_Project\\target\\testcase_screenshot\\screenshot_page" + screenshotCounter + ".png");
            FileUtils.copyFile(source, Target);
            screenshotCounter++;

            attachScreenshotToAllure("Screenshot", Target);
        }


    }
    public static void attachScreenshotToAllure(String screenshotName, File screenshotFile) throws IOException {
        Allure.addAttachment(screenshotName, new FileInputStream(screenshotFile));
    }

    @AfterTest
   public static void  tear_down(){
        driver.close();

    }
}




