package es.codeurjc.test.web;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WikipediaTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() throws InterruptedException {
		
		driver.get("https://wikipedia.org");
        WebElement searchInput = driver.findElement(By.name("search"));

        Thread.sleep(2000);
        
        searchInput.sendKeys("Rick Astley");
        searchInput.submit();

        Thread.sleep(2000);
        
        WebElement link = driver.findElement(By.linkText("Rickrolling"));
        link.click();
        
        Thread.sleep(2000);

        boolean memeFound = driver.findElements(By.cssSelector("p"))
                .stream()
                .anyMatch(element -> element.getText().contains("meme"));

        assertTrue("Rickrolling page should contain meme word", memeFound);
	}

}







