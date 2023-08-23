import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class BasketAddPage {

    private WebDriver driver;



    public BasketAddPage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/
    private final String IN_THE_BASKET = "add_to_cart";
    private final String BASKET_BUTTON = "//header/div[2]/div[1]/div[2]/a[2]";



    /******************************************
    **************METHODS********************
    ******************************************/
    public void addToBasketButton(){
        driver.findElement(By.id(IN_THE_BASKET)).click();
    }

    public void busketButton(){
        driver.findElement(By.xpath(BASKET_BUTTON)).click();
    }
}
