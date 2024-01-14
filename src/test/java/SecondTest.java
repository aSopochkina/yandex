import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import spaces.InitWebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SecondTest {
    private WebDriver driver;
    private String buttonOrder;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String customerUnderground;
    private String customerMobileNumber;
    private String dateDelivery;
    private String rentalTime;
    private String colourSamokat;
    private String commentDelivery;

    public SecondTest(String buttonOrder, String customerName, String customerSurname, String customerAddress, String customerUnderground, String customerMobileNumber, String dateDelivery, String rentalTime, String colourSamokat, String commentDelivery){
        this.buttonOrder = buttonOrder;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerUnderground = customerUnderground;
        this.customerMobileNumber = customerMobileNumber;
        this.dateDelivery = dateDelivery;
        this.rentalTime = rentalTime;
        this.colourSamokat = colourSamokat;
        this.commentDelivery = commentDelivery;
    }

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = InitWebDriver.get("chrome");
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"Top","Александр","Буйнов","улица Зубковой","1","89109009090","17.01.2024","сутки","grey","ничего"},
                {"Lower","Анастасия","Рожкова","улица Штрауса","2","89909909090","16.01.2024","двое суток","black","нет комментария"}
        };
    }

    @Test
    public void Order(){
        boolean actual = new MainPage(driver)
                .clickAcceptCookie()
                .clickButtonOrder(buttonOrder)
                .setCustomerInfo(customerName,customerSurname,customerAddress,customerUnderground,customerMobileNumber)
                .clickButtonNext()
                .setRentalInfo(dateDelivery,rentalTime,colourSamokat,commentDelivery)
                .clickYesRegistrationOrder()
                .isOrderProcessed();

        assertTrue("Всплывающего окна Заказ оформлен нет",actual);
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
