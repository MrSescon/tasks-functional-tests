package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);

		} finally {
			// Fechar navegador
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);

		} finally {
			// Fechar navegador
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemData() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);

		} finally {
			// Fechar navegador
			driver.quit();
		}

	}

	@Test
	public void deveSalvarTarefaComDataPassada() {

		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);

		} finally {
			// Fechar navegador
			driver.quit();
		}

	}

}
