import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    private WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/
    private static final String LOGO = "//header/div[2]/div[1]/a[1]";
    private static final String HEADER = "//header";
    private static final String SEARCH_INPUT = "//header/div[2]/div[1]/div[1]/form[1]/input[2]";
    private static final String SEARCH_BUTTON_HEADER = "//header/div[2]/div[1]/div[1]/form[1]/button[1]";
    private static final String BODY_CONTAINS_RESULT = "//body/div[1]";
    private static final String SEARCH_RESULT_COUNT = "//body/div[1]/div[1]/main[1]/div[3]/div[1]/div[1]";
    private static final String POP_UP_WIN = "//div[@class='closeX']";
    /* ********** Methods ***********
     ****************************************/
    public void pageLoading() {
        try {
            if (!((driver.findElement(By.xpath(LOGO))).isDisplayed()))
                throw new Exception();
        } catch (Exception ex) {
            throw new AssertionError("The 'Main Page' was not loaded");
        }
    }
    public void inputSearchInput(String searchValue){
        driver.findElement(By.xpath(SEARCH_INPUT)).sendKeys(searchValue);

    }
    public void clickSearhButton (){
        driver.findElement(By.xpath(SEARCH_BUTTON_HEADER)).click();

    }
    public void clickPopUpWin(){
        driver.findElement(By.xpath(POP_UP_WIN)).click();
    }

    public int getResultsCount() {
       List<WebElement> resultSearch = driver.findElements(By.xpath(SEARCH_RESULT_COUNT));
       return resultSearch.size();
    }
}
