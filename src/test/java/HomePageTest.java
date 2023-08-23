import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.RestAssured;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.*;
import static io.restassured.RestAssured.given;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public  class HomePageTest {
    private static WebDriver driver;
    static HomePage homePage;

    @BeforeAll //запуск Хромдрайвера
    @DisplayName("ChromeDriver Start")
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

    @Test
    @DisplayName("Search button")
    public void testSearchValue() throws IOException {
        driver.get("https://www.books.ru/");
        driver.manage().window().maximize(); // окно в полный размер
        homePage.pageLoading(); // загрузка главной страницы
        homePage.clickPopUpWin(); // закрытие всплывающего окна

        String[] searchValue = ReadSearch.readFromFile("Search.txt");
        StringBuilder testWord = new StringBuilder();
            for (String str : searchValue) {
                testWord.append(str);
                homePage.inputSearchInput(str);
                homePage.clickSearhButton(); // нажатие лупы - кнопки поиска
                Allure.addAttachment("ВВод тестового слова", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                ;
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                assertTrue(homePage.getResultsCount() > 0, "Search results amount equals zero"); // проверка что на странице
                //отображаются найденные книги больше 0

       /* assertEquals(homePage.countResultError(), 0, "Nothing found");
        // проверка что в поле Найдено наименований = 0*/

                homePage.clearSearchInput();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            }
        }


    @Test
    @DisplayName("Проверка кода ответа старого сайта")
    public void testGetReguest(){
        given()
                .when()
                .get(homePage.getLinkOldSite())
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("вывод кода старого сайта")
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