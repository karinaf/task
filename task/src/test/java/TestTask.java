import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestTask {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    Actions actions;

    @Before
    public void BeforeTest() throws Exception {
        driver.manage().window().maximize();
        driver.get("https://www.yandex.ru/");
    }

    @Test
    public void Task() throws Exception {
        driver.findElement(By.cssSelector(".home-arrow__tabs > div > a:nth-child(2)")).click(); //маркет‚
        driver.findElement(By.cssSelector("li:nth-child(1) > a")).click(); //электроника
        driver.findElement(By.linkText("Мобильные телефоны")).click();
        driver.findElement(By.cssSelector("input#glf-priceto-var")).clear(); //цена до
        driver.findElement(By.cssSelector("input#glf-priceto-var")).sendKeys("20000");
        driver.findElement(By.cssSelector(":nth-child(13) .n-filter-block__header")).click(); //Диагональ
        driver.findElement(By.cssSelector(":nth-child(13) .n-filter-block__body_js_inited div > :nth-child(1)  input")).clear();
        driver.findElement(By.cssSelector(":nth-child(13) .n-filter-block__body_js_inited div > :nth-child(1)  input")).sendKeys("3");
        driver.findElement(By.linkText("Nokia")).click();
        driver.findElement(By.linkText("Samsung")).click();
        driver.findElement(By.linkText("LG")).click();
        driver.findElement(By.linkText("Apple")).click();
        driver.findElement(By.linkText("ASUS")).click();
        driver.findElement(By.cssSelector(".n-filter-panel-aside__apply ")).click();//применить
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".n-breadcrumbs__item_seo_yes")));
        assert (driver.findElements(By.cssSelector(".snippet-cell__header")).size() == 12);//проверить количество элементов на страницу
        String s = driver.findElements(By.cssSelector("a.snippet-cell__header")).get(0).getText(); //запомнить первый элемент
        driver.findElement(By.cssSelector(".n-filter-block_pos_left.i-bem > div:nth-child(3) > a")).click(); //отсортировать по цене
        do {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Показать еще")));
            driver.findElement(By.partialLinkText("Показать еще")).click();
            if (!driver.findElements(By.partialLinkText(s)).isEmpty()) {//кликнуть по элементу
                driver.findElement(By.partialLinkText(s)).click();
                break;
            }
        } while (!driver.findElements(By.partialLinkText("Показать еще")).isEmpty());
    }

    @After
    public void AfterTest() throws Exception {
        driver.quit();
    }

}

