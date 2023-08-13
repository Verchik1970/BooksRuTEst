import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public  class HomePageTest {
    private WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver()
                .setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Search button")
    public void testSearchValue(){
        driver.get("https://www.books.ru/");
        String searchValue = "Чехов";
        driver.manage().window().maximize();
        homePage.pageLoading();
        homePage.clickPopUpWin();
        homePage.inputSearchInput(searchValue);
        homePage.clickSearhButton();
        assertTrue(homePage.getResultsCount() > 0, "Search results amount equals zero");

    }
}