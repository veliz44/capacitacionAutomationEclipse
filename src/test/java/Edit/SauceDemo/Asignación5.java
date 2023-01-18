package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Asignación5 {
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	File pantalla;
	String rutaEvidencia = "..\\SauceDemo\\Evidencias\\";
	
	//Configurar Navegador
	@BeforeSuite
	public void abrirNavegador() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	//Casos de Prueba
	@Test (description="CP1 - Login SauceDemo", priority=1)
	public void login() throws IOException {
		//Paso1 Cargar datos en login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		
		//Evidencia 1 Datos login
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "01_Datos_Login.jpg"));
		
		
		// Paso2. Hacer click
		driver.findElement(By.id("login-button")).click();
	}
	
	@Test (description="CP2 - Seleccionar y cargar orden", priority=2)
	public void SeleccionarCargarEjecutar() throws IOException {
		//Evidencia 2 Pantalla Inventario
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "02_Pantalla_Inventario.jpg"));
		
		//Paso1 Seleccionar articulo "Sauce Labs Backpack"
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		//Evidencia 3 Icono Carrito con un articulo asignado
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "03_Icono_Carrito_con_Articulo.jpg"));
		
		//Paso2 Ir al carrito
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
		//Evidencia 4 Pantalla Carrito con Articulo cargado
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "04_Carrito_Con_Articulo.jpg"));
		
		//Validar Url del carrito
		String urlEsperada = "https://www.saucedemo.com/cart.html";
		String urlActual = driver.getCurrentUrl();
		
		Assert.assertEquals(urlActual, urlEsperada);
		
		//Validar Nombre del elemento seleccionado en el carrito
		String valorEsperado = "Sauce Labs Backpack";
		String valorActual = driver.findElement(By.id("item_4_title_link")).getText();
		
		Assert.assertEquals(valorActual, valorEsperado);
		
		
		
		//Paso3 CheckOut
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		//Paso4 Cargar info personal
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Rafa");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Veliz");
		driver.findElement(By.id("postal-code")).sendKeys("1629");
		//Evidencia 5 Datos personales cargados
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "05_Datos_Personales.jpg"));
		
		//Paso5 Click en continuar
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		//Paso6 Finalizar la Compra
		driver.findElement(By.id("finish")).click();
		//Evidencia 6 Compra finalizada
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla,new File(rutaEvidencia + "06_Finalizar_Compra.jpg"));
		
	}
	
	// Método para cerrar el navegador
		@AfterSuite
		public void cerrarNavegador() {
		driver.close();
		}
			
			
	
}
