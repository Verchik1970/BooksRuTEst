import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BasketTest {
    WebDriver driver = new ChromeDriver();


   HomePage homePage = new HomePage(driver);
     BasketAddPage basketAddPage = new BasketAddPage(driver);

    public BasketTest() throws MalformedURLException {
    }


    @BeforeAll
public static void initChrome(){
    InitChromeDriver.init();

}

    @Test
    public void addToBasket() throws IOException {
        String searchValue = ("Чехов");

        driver.get("https://www.books.ru/");
        driver.manage().window().maximize(); // окно в полный размер
        homePage.pageLoading(); // загрузка главной страницы
        homePage.clickPopUpWin(); // закрытие всплывающего окна
        homePage.inputSearchInput(searchValue);
        homePage.clickSearhButton(); // нажатие лупы - кнопки поиска
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        basketAddPage.addToBasketButton();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        basketAddPage.busketButton();
        Allure.addAttachment("Содержимое корзины", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        ;


    }
}
