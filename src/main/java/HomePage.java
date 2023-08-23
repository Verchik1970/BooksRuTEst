import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HomePage {
    private WebDriver driver;



    public HomePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;

    }

    /* **************************************
     *************** Locators ***************
     ***************************************/
    private static final String LOGO = "//header/div[2]/div[1]/a[1]"; //лого - главная страница
    private static final String HEADER = "//header";
    private static final String SEARCH_INPUT = "//header/div[2]/div[1]/div[1]/form[1]/input[2]"; //поле ввода
    private static final String SEARCH_BUTTON_HEADER = "//header/div[2]/div[1]/div[1]/form[1]/button[1]"; //кнопка лупа
    private static final String BODY_CONTAINS_RESULT = "//body/div[1]";
    private static final String SEARCH_RESULT_MESSAGE = "//body[1]/div[1]/div[1]/main[1]/div[1]/p[1]/b[2]"; //поле цифра Ноль
    //в результате поиска
    private static final String SEARCH_RESULT_COUNT = "//body/div[1]/div[1]/main[1]/div[3]/div[1]/div[1]";
    //карточка объекта книги
    private static final String POP_UP_WIN = "//div[@class='closeX']"; //закрытие всплывающего окна - крестик
    private static final String LINK_OLD_SITE = "//a[@id='link_old_site']"; // переход на старый сайт
    URL url = new URL("https://new.books.ru/") ;
    /* ********** Methods ***********
     ****************************************/
    public void pageLoading() {
        //загрузка главной страницы
        try {
            if (!((driver.findElement(By.xpath(LOGO))).isDisplayed()))
                throw new Exception();
        } catch (Exception ex) {
            throw new AssertionError("The 'Main Page' was not loaded");
        }
    }
    public void inputSearchInput(String str){
        //ввод поисковой фразы
        driver.findElement(By.xpath(SEARCH_INPUT)).sendKeys(str);

    }
    public void clickSearhButton (){
        //нажатие кнопки поиска -лупы
        driver.findElement(By.xpath(SEARCH_BUTTON_HEADER)).click();

    }
    public void clickPopUpWin(){
        //закрытие всплывающего окна
        driver.findElement(By.xpath(POP_UP_WIN)).click();
    }

    public int getResultsCount() {// подсчет количества элементов поиска
       List<WebElement> resultSearch = driver.findElements(By.xpath(SEARCH_RESULT_COUNT));
       return resultSearch.size();

    }
    public int countResultError(){ //число - в поле Найдено наименований
        driver.findElement(By.xpath(SEARCH_RESULT_MESSAGE)).getText();
        return 0;
    }
    public URL getLinkOldSite(){
        driver.findElement(By.xpath(LINK_OLD_SITE)).click();


        return url;
    }

    public void clearSearchInput(){
        driver.findElement(By.xpath(SEARCH_INPUT)).clear();
    }
}

