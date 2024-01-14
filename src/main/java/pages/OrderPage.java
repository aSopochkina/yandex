package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    //локатор названия формы заказа "Для кого самокат"
    private final By titleForClient = By.xpath("//*[@class = 'Order_Header__BZXOb' and text() = 'Для кого самокат']");
    //локатор поля имени
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    //локатор поля фамилия
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //локатор для поля ввода адреса доставки
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля станции метро
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    //локатор выпадающего поля станции метро
    private final By selectMetroStationField = By.xpath("//div[@class='select-search__select']");
    //локатор ввода номера телефона
    private final By mobileNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор для кнопки "Далее"
    private final By buttonNext = By.xpath("//button[contains(text(),'Далее')]");
    //локатор названия формы "Про аренду"
    private final By titleAboutRent = By.xpath("//div[@class='Order_Header__BZXOb']");
    //локатор поля когда привезти заказ
    private final By dataDeliveryField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор поля для комментариев
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки Заказать
    private final By titleOrderRegistration = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор согласия оформить заказ
    private final By acceptOrder = By.xpath("//button[text()='Да']");
    //локатор всплывающего окна "Заказ оформлен"
    private final By orderIsProcessed = By.xpath("//div[@class='Order_Modal__YZ-d3']");
    //Локатор для поля срока аренды
    private final By rentalPeriodField = By.className("Dropdown-placeholder");


    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage setCustomerName(String customerName) {
        driver.findElement(nameField).sendKeys(customerName);
        return this;
    }

    public OrderPage setCustomerSurname(String customerSurname) {
        driver.findElement(surnameField).sendKeys(customerSurname);
        return this;
    }

    public OrderPage setCustomerAddress(String customerAddress) {
        driver.findElement(addressField).sendKeys(customerAddress);
        return this;
    }

    public OrderPage setCustomerUnderground(String customerUnderground) {
        driver.findElement(metroStationField).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(selectMetroStationField));

        driver.findElement(By.xpath("//div[@class ='select-search__select']//li[" + customerUnderground + "]")).click();
        return this;
    }

    public OrderPage setCustomerMobileNumber(String customerMobileNumber) {
        driver.findElement(mobileNumberField).sendKeys(customerMobileNumber);
        return this;
    }

    public OrderPage setCustomerInfo(String customerName, String customerSurname, String customerAddress, String customerUnderground, String customerMobileNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(titleForClient));

        setCustomerName(customerName);
        setCustomerSurname(customerSurname);
        setCustomerAddress(customerAddress);
        setCustomerUnderground(customerUnderground);
        setCustomerMobileNumber(customerMobileNumber);
        return this;
    }

    public OrderPage clickButtonNext() {
        driver.findElement(buttonNext).click();
        return this;

    }

    public OrderPage setDateDelivery(String dateDelivery) {
        driver.findElement(dataDeliveryField).sendKeys(dateDelivery);
        driver.findElement(dataDeliveryField).sendKeys(Keys.ENTER);
        return this;
    }

    public OrderPage setRentalTime(String rentalTime) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath("//div[text() = '" + rentalTime + "']")).click();
        return this;
    }

    public OrderPage setColourSamokat(String colourSamokat) {
        driver.findElement(By.id(colourSamokat)).click();
        return this;
    }

    public OrderPage setCommentDelivery(String commentDelivery) {
        driver.findElement(commentField).sendKeys(commentDelivery);
        return this;
    }


    public OrderPage setRentalInfo(String dateDelivery, String rentalTime, String colourSamokat, String commentDelivery) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(titleAboutRent));

        setDateDelivery(dateDelivery);
        setRentalTime(rentalTime);
        setColourSamokat(colourSamokat);
        setCommentDelivery(commentDelivery);
        return this;
    }

    public OrderPage clickYesRegistrationOrder() {
        driver.findElement(titleOrderRegistration).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(acceptOrder));
        driver.findElement(acceptOrder).click();
        return this;
    }

    public boolean isOrderProcessed() {
        return driver.findElement(orderIsProcessed).isDisplayed();
    }

}
