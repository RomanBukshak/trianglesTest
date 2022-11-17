package trianglesTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class TriangleTest {
	
	private WebDriver driver;
	FirefoxDriver driver1;
	ChromeDriver driver2;
	String baseURL, nodeURL;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@BeforeAll
	public void setUp() throws MalformedURLException {
		baseURL="https://www.calculator.net/triangle-calculator.html";
	    nodeURL="http://localhost:4444";
	}	
	
	//Тест с родными драйверами
	@Test
	@DisplayName("Большие числа1")
	@Tag("negative")
	public void bigValueTest() throws Throwable{
		System.out.println("Executing on FireFox");
		System.setProperty("webdriver.gecko.driver", "src\\test\\java\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
		capability.setBrowserName("firefox");
	    capability.setPlatform(Platform.WIN10);
	    driver1=new FirefoxDriver(options);
	    driver1.get(baseURL);
		driver1.manage().window().maximize();
		driver1.findElement(By.className("clearbtn")).click();
		driver1.findElement(By.name("va")).sendKeys("999");
		driver1.findElement(By.xpath("//input[@value='Calculate']")).click();
		Assertions.assertEquals(driver1.findElement(By.cssSelector("font")).getText(), "Angle \"a\" too big.");
		driver1.close();
	}
	
	//Тест с родными драйверами
	@Test
	@DisplayName("Большие числа2")
	@Tag("negative")
	public void bigValueTest1() throws Throwable{
		System.out.println("Executing on CHROME");
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		capability.setBrowserName("chrome");
	    capability.setPlatform(Platform.WIN10);
	    driver2=new ChromeDriver(options);
	    driver2.get(baseURL);
		driver2.manage().window().maximize();
		driver2.findElement(By.className("clearbtn")).click();
		driver2.findElement(By.name("va")).sendKeys("999");
		driver2.findElement(By.xpath("//input[@value='Calculate']")).click();
		Assertions.assertEquals(driver2.findElement(By.cssSelector("font")).getText(), "Angle \"a\" too big.");
		driver2.close();
	}
	

	@Test
	@DisplayName("Не все поля заданы")
	@Tag("negative")
	  public void notAllFieldsAreSet() throws Throwable{
		System.out.println("Executing on FireFox");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("firefox");
	    capability.setPlatform(Platform.WIN10);
	    driver=new RemoteWebDriver(new URL(nodeURL),capability);
	    driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("4");
	    driver.findElement(By.name("vy")).sendKeys("5");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.cssSelector("font")).getText(), "Please provide 3 positive values, including at least one side.");
	    driver.close();
	  }
	
	@Test
	@DisplayName("Не выполнились условия треугольника")
	@Tag("negative")
	  public void notTriangleCondition() throws Throwable{
		System.out.println("Executing on FireFox");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("firefox");
	    capability.setPlatform(Platform.WIN10);
	    driver=new RemoteWebDriver(new URL(nodeURL),capability);
	    driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("3");
	    driver.findElement(By.name("vy")).sendKeys("4");
	    driver.findElement(By.name("vz")).sendKeys("10");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.cssSelector("font")).getText(), "The sum of two sides must be larger than the third.");
	    driver.close();
	  }
	
	  @Test
	  @DisplayName("Это не треугольник")
	  @Tag("negative")
	  public void notTriangle() throws Throwable{
		System.out.println("Executing on FireFox");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vc")).sendKeys("100");
	    driver.findElement(By.name("va")).sendKeys("90");
	    driver.findElement(By.name("vb")).sendKeys("30");
	    driver.findElement(By.name("vx")).sendKeys("6");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.cssSelector("font")).getText(), "The sum of the three angles must equal 180° or π radians.");
	    driver.close();
	  }
	  
	  @Test
	  @DisplayName("Остроугольный треугольник")
	  @Tag("positive")
	  public void acuteTriangle() throws Throwable{
		System.out.println("Executing on CHROME");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("2");
	    driver.findElement(By.name("va")).sendKeys("10");
	    driver.findElement(By.name("vc")).sendKeys("84");
	    driver.findElement(By.name("vb")).sendKeys("86");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.xpath("//td//h3")).getText(), "Acute Scalene Triangle");
	    driver.close();
	  }
	  
	  @Test
	  @DisplayName("Прямоугольный треугольник")
	  @Tag("positive")
	  public void rightScaleneTriangle() throws Throwable{
		System.out.println("Executing on CHROME");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("4");
	    driver.findElement(By.name("vy")).sendKeys("3");
	    driver.findElement(By.name("vz")).sendKeys("5");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.xpath("//td//h3")).getText(), "Right Scalene Triangle");
	    driver.close();
	  }
	  
	  @Test
	  @DisplayName("Равнобедренный треугольник")
	  @Tag("positive")
	  public void obtuseIsoscelesTriangle() throws Throwable{
		System.out.println("Executing on CHROME");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("5");
	    driver.findElement(By.name("vy")).sendKeys("5");
	    driver.findElement(By.name("vz")).sendKeys("8");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.xpath("//td//h3")).getText(), "Obtuse Isosceles Triangle");
	    driver.close();
	  }
	  
	  @Test
	  @DisplayName("Равносторонний треугольник")
	  @Tag("positive")
	  public void equilateralTriangle() throws Throwable{
		System.out.println("Executing on CHROME");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vx")).sendKeys("5");
	    driver.findElement(By.name("vy")).sendKeys("5");
	    driver.findElement(By.name("vz")).sendKeys("5");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.xpath("//td//h3")).getText(), "Equilateral Triangle");
	    driver.close();
	  }
	  
	  @Test
	  @DisplayName("Тупоугольный треугольник")
	  @Tag("positive")
	  public void obtuseScaleneTriangle() throws Throwable{
		System.out.println("Executing on CHROME");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(nodeURL),capability);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.findElement(By.className("clearbtn")).click();
	    driver.findElement(By.name("vc")).sendKeys("120");
	    driver.findElement(By.name("vx")).sendKeys("1");
	    driver.findElement(By.name("va")).sendKeys("40");
	    driver.findElement(By.name("vb")).sendKeys("20");
		driver.findElement(By.xpath("//input[@value='Calculate']")).click();
	    Assertions.assertEquals(driver.findElement(By.xpath("//td//h3")).getText(), "Obtuse Scalene Triangle");
	    driver.close();
	  }
}
