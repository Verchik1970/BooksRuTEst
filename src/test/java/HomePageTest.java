import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public  class HomePageTest {
    private WebDriver driver;
    HomePage homePage;

    @BeforeEach //запуск Хромдрайвера
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
        String searchValue = "голкондрина для поклябывания";
        driver.manage().window().maximize(); // окно в полный размер
        homePage.pageLoading(); // загрузка главной страницы
        homePage.clickPopUpWin(); // закртыие всплывающего окна
        homePage.inputSearchInput(searchValue); //ввод поисковой фразы
        homePage.clickSearhButton(); // нажатие лупы - кнопки поиска
/*
        assertTrue(homePage.getResultsCount() > 0, "Search results amount equals zero"); // проверка что на странице
        //отображаются найденные книги больще 0
*/
        assertEquals(homePage.countResultError(), 0, "Nothing found");
        // проверка что в поле Найдено наименований = 0
    }
    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}