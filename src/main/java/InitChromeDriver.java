import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class InitChromeDriver {

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        InitChromeDriver.driver = driver;
    }

    private static WebDriver driver;
    static HomePage homePage;
    public static void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        chromedriver()
                .setup();
        driver = new ChromeDriver(options);
        /*WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver-win64\\chromedriver.exe");*/
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        try {
            homePage = new HomePage(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

   /* private static io.github.bonigarcia.wdm.WebDriverManager chromedriver() {
        return chromedriver();
    }*/

}
