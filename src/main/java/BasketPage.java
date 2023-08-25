import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class BasketPage {
    private static WebDriver driver;
    static JavascriptExecutor js;


    public BasketPage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
    }
    /* **************************************
     *************** Locators ***************
     ***************************************/
    private final String BASKET_BUTTON = "//header/div[2]/div[1]/div[2]/a[2]";



    /******************************************
     **************METHODS********************
     ******************************************/

    public void basketButton (){
        WebElement basketButton = driver.findElement(By.xpath(BASKET_BUTTON));
         js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", basketButton);
    }
}
