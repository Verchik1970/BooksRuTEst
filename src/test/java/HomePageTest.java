import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public  class HomePageTest {
    private static WebDriver driver;
    static HomePage homePage;

    @BeforeAll //запуск Хромдрайвера
    public static void init() {
        WebDriverManager.chromedriver()
                .setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            homePage = new HomePage(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Search button")
    public void testSearchValue(){
        driver.get("https://www.books.ru/");
        String searchValue = "Чехов";
        driver.manage().window().maximize(); // окно в полный размер
        homePage.pageLoading(); // загрузка главной страницы
        homePage.clickPopUpWin(); // закртыие всплывающего окна
        homePage.inputSearchInput(searchValue); //ввод поисковой фразы
        homePage.clickSearhButton(); // нажатие лупы - кнопки поиска

        assertTrue(homePage.getResultsCount() > 0, "Search results amount equals zero"); // проверка что на странице
        //отображаются найденные книги больще 0

        assertEquals(homePage.countResultError(), 0, "Nothing found");
        // проверка что в поле Найдено наименований = 0
    }
    @Test
    public void testGetReguest(){
        given()
                .when()
                .get(homePage.getLinkOldSite())
                .then()
                .statusCode(200);
    }


    @Test
    public void testApiOLdPageCode() {
        Response response = RestAssured.
                get("https://new.books.ru/").andReturn();
        response.prettyPrint();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}