package Edit.SauceDemo;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

public class Asignación6 {
	String url = "https://www.saucedemo.com";
	WebDriver driver;
	String rutaDatos = "..\\SauceDemo\\Datos Entrada\\Datos Login SauceDemo.xlsx";
	String rutaEvidencia = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencia SauceDemo Asignación6.docx";
			
	
	@BeforeSuite
	public void abrirNavegador() throws InvalidFormatException, IOException, InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento, "Documento de Evidencias SauceDemo", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento, "Se validarán 04 Login Erróneos, 01 Usuario Bloqueado y por último 01 Login válido seguido de un proceso de compra exitosa de 2 artículos", 16);
		
	}
	
	@Test(dataProvider="Datos Login")
	public void Login(String username, String password, String FirstName, String LastName, String PostalCode) throws InvalidFormatException, IOException, InterruptedException {
	//Ir a Pag Inicio
		driver.get(url);
		
	//Rellenar campos del login
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();
		
	//Captura por logueo
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Login");
		
	//Completar la compra
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Selección Artículo N°1");
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Selección Artículo N°2");
		
		driver.findElement(By.cssSelector("#shopping_cart_container")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Carrito con los artículos seleccionados");
		driver.findElement(By.cssSelector("#checkout")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Datos de Usuario Vacio");
		
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(PostalCode);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Datos de Usuario Valido cargados");
		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Pantalla CHECKOUT");
		
		driver.findElement(By.xpath("//button[@id='finish']")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Compra Finalizada");
		
	}
	
	@DataProvider(name="Datos Login")
	public Object[][] leerDatosLogin() throws Exception {
		return DatosExcel.leerExcel(rutaDatos, "Login");
	}
	
	@AfterSuite
	public void cerrarNavegador() {
		driver.close();
	}
}