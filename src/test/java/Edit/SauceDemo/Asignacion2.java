package Edit.SauceDemo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Asignacion2 {
	// Seccion 1 : Atributos o Variables de uso comun
	String url = "https://www.saucedemo.com/";
	
	// Seccion 2: Metodos de prueba
	@Test
	public void hacerBusquedaEnChrome()
	{
		//Paso 1. Configurar el navegador
		WebDriver navegador = new ChromeDriver();
		
		//Paso 2. Abrir el navegador 
		navegador.get(url);
		
		// Paso 3. Rellenar campos del login
		WebElement txtBuscador = navegador.findElement(By.id("user-name"));
		txtBuscador.sendKeys("standard_user");
		
		WebElement txtBuscador1 = navegador.findElement(By.id("password"));
		txtBuscador1.sendKeys("secret_sauce");
		
		// Paso 4. Hacer click
		WebElement txtBuscador2 = navegador.findElement(By.id("login-button"));
		txtBuscador2.click();
	}

}
