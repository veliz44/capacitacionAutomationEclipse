package Edit.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class Asignación4 {
	String url = "http://automationpractice.pl";
	
	@Test
	public void SignIn() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
		
		// Paso 1 Click en "Sing In"
		driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		
		//Paso 2 Ingresar mail
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		
		driver.findElement(By.id("email_create")).sendKeys(email);
		
		//Paso 3 Hacer click en "Create an account"
		driver.findElement(By.id("SubmitCreate")).click();
		
		//Paso 4 Cargar información personal
		WebElement Nombre = driver.findElement(By.xpath("//input[@id='customer_firstname']"));Nombre.sendKeys("Rafa");
		//driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Rafa");
		
		driver.findElement(By.id("customer_lastname")).sendKeys("Veliz");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456");
		
		
		//Paso 5 Hacer click en "Registrer"
		driver.findElement(By.cssSelector("#submitAccount")).click();

		
	}
	
	
}
