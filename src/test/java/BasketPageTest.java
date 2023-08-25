import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

public class BasketPageTest {
    WebDriver driver = new ChromeDriver();

    BasketPage basketPage = new BasketPage(driver);
    HomePage homePage = new HomePage(driver);
    public BasketPageTest() throws MalformedURLException {
    }

    @BeforeAll
    public static void initChrome() {
        InitChromeDriver.init();


    }
    @Test
    @DisplayName( " переход на страницу Корзина")
    public void moveToBasketPage(){

        driver.get("https://www.books.ru/");
        driver.manage().window().maximize(); // окно в полный размер
        homePage.pageLoading(); // загрузка главной страницы
        homePage.clickPopUpWin(); // закрытие всплывающего окна
        basketPage.basketButton();
    }
}
