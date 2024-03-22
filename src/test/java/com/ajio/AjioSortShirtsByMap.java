package com.ajio;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AjioSortShirtsByMap {
	public static WebDriver driver;
	public static Actions action;
	public static List<WebElement> options;
	public static Select select;
	public static boolean flag = true;
	
	@Given("Go to Ajio website {string}")
	public void go_to_ajio_website(String url) {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("start-maximized");
		driver = new EdgeDriver(options);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	}

	@And("Navigate to Mens menu and click on Shirts")
	public void navigate_to_mens_menu_and_click_on_shirts() {
		WebElement moveToMenCategory = driver.findElement(By.xpath("//span[text()='MEN']"));
		action.moveToElement(moveToMenCategory).build().perform();
		WebElement shirts = driver.findElement(By.xpath("//a[text()='Shirts']"));
		shirts.click();
	}

	@When("User clicks on sort dropdown and select option in a dropdown with one dim map")
	public void user_clicks_on_sort_dropdown_and_select_option_in_a_dropdown_with_one_dim_map(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		String sortValue = map.get("sort3");
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='sortBy']"));
		 select = new Select(dropdown);
		 List<WebElement> options = select.getOptions();
		 for(WebElement option : options) {
			 String text = option.getText();
			 if(text.contains(sortValue)) {
				 select.selectByVisibleText(text);
			 }
		 }
	}

	@Then("Validate the results")
	public void validate_the_results() {
		try{
			List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price  ']"));
			Thread.sleep(10000);
		 for(int i=0;i<prices.size()-1;i++) {
		    	String previous = prices.get(i).getText().replaceAll(",","").replaceAll("₹", "");
		    	String next = prices.get(i+1).getText().replaceAll(",","").replaceAll("₹", "");
		    	int firstValue = Integer.parseInt(previous);
		    	int secondValue = Integer.parseInt(next);
		    	if(firstValue < secondValue) {
		    		System.out.println(firstValue);
		    	}
		    	else {
		    		flag = false;
		    	}
		 }
		if(flag = true) 
			System.out.println("Values are sorted in ascending order");
	    else
	    	System.out.println("sort option is not working");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
