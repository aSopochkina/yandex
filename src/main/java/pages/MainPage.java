package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Создали page object — класс для страницы
public class MainPage {

    //добавили поле driver
    private WebDriver driver;

    //локатор принятия куков
    private final By acceptCookie = By.xpath("//button[@class='App_CookieButton__3cvqF']");
    private final By topButtonOrder = By.xpath("//button[@class='Button_Button__ra12g']");

    //локатор нижней кнопки заказать
    private final By lowerButtonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор блока вопросов
    private final By questionBlock = By.className("Home_FAQ__3uVm4");
    //Добавили конструктор класса page object
    public MainPage(WebDriver driver) {
        this.driver = driver;// Инициализировали в нём поле driver
    }

    public MainPage clickAcceptCookie() {
        driver.findElement(acceptCookie).click();

        return this;
    }

    //получение текста ответа
    public String getAnswerText(String idQuestion){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='accordion__heading-" + idQuestion +"']")));
        return driver.findElement(By.xpath("//*[@id =\"accordion__panel-" + idQuestion + "\"]/p")).getText();


    }

    //клик по вопросу
    public MainPage clickOnQuestion(String question){
        WebElement element = driver.findElement(questionBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath("//*[text() = '"+ question + "']")).click();

        return this;
    }

    //клик по кнопке Заказать
    public OrderPage clickButtonOrder(String orderButton){

        switch (orderButton) {

            case "Top":

                driver.findElement(topButtonOrder).click();

                break;

            case "Lower":

                WebElement element = driver.findElement(lowerButtonOrder);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);


                driver.findElement(lowerButtonOrder).click();

                break;

        }

        return new OrderPage(driver);
    }


}
