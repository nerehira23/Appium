package BasicTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicJunit {
    private AppiumDriver appiumDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","HUAWEI P30 lite");
        desiredCapabilities.setCapability("platformVersion","10");
        desiredCapabilities.setCapability("appPackage","com.android.calculator2");
        desiredCapabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void cleanUp() throws InterruptedException {
        Thread.sleep(5000);
        appiumDriver.quit();
    }

    @Test
    public void testAdd(){
        //Click en el numero 4
        appiumDriver.findElement(By.id("com.android.calculator2:id/digit_4")).click();
        //Click en el boton sumar
        appiumDriver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        //Click en el numero 6
        appiumDriver.findElement(By.id("com.android.calculator2:id/digit_6")).click();
        // Click equals
        appiumDriver.findElement(By.id("com.android.calculator2:id/eq")).click();
        // Verificacion
        String expectedResult="10";
        String actualResult=appiumDriver.findElement(By.id("com.android.calculator2:id/formula")).getText();
        System.out.println("Resultado suma:" + actualResult);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR al sumar");
    }
}
