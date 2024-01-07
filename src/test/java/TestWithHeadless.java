import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestWithHeadless {
    WebDriver driver;
    private Logger logger = LogManager.getLogger(TestWithHeadless.class);

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStart() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        logger.info("Драйвер открыт");
    }

    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void headless() {

        driver.get("https://duckduckgo.com/");
        logger.info("Открыта поисковая страница");

        driver.findElement(By.cssSelector("[id=searchbox_input]"))
                .sendKeys("ОТУС" + Keys.ENTER);

        String str1 ="Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        String str2 = driver.findElement(By.xpath("//*[@id=\"r1-0\"]/div[2]/h2/a/span")).getText();
        if (str1.equals(str2)){
            System.out.println("Искомый текст- совпадает: Онлайн‑курсы для профессионалов, дистанционное обучение современным ... ");
            logger.info("Искомый текст- совпадает");
        }else{
            System.out.println("False. Искомый текст не совпадает");
            logger.info("Искомый текст не совпадает");
        }
        logger.info("Драйвер закрыт");
    }

}
