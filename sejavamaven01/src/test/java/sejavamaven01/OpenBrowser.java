package sejavamaven01;


import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dev.failsafe.internal.util.Assert;



public class OpenBrowser {

	public static void main(String[] args) {
		
		
		ChromeOptions options = new ChromeOptions();
		options.setPlatformName("Windows 10");
		options.setBrowserVersion("latest");

		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("username", System.getenv("oauth-sac090909-da1c8"));
		sauceOptions.put("accessKey", System.getenv("77496786-f9f8-46ad-8099-2fc1abeb05ad"));
		//sauceOptions.put("name", testInfo.getDisplayName());

		options.setCapability("sauce:options", sauceOptions);
		
		
		
       
		WebDriver driver = new ChromeDriver();
        driver.get("https://business.bofa.com/content/boaml/en_us/home.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
    

        //System.out.println(driver.getTitle());
        
        String parentWindow = driver.getWindowHandle();
		
        System.out.println(parentWindow);
        //driver.findElement(By.id("searchInput")).sendKeys("pens", Keys.ENTER);
        //driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
        
       
        
        //driver.findElement(By.tagName("h2")).getText();
        //System.out.println(driver.findElement(By.tagName("h2")).getText());  
        //driver.findElement(By.cssSelector("div.sc-1kcmyi2-2.hjDzAE")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
        //driver.quit();
        
        driver.findElement(By.xpath("//*[contains(text(),'Client Login')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'CashPro®')]")).click();
        Set <String> handles =driver.getWindowHandles();
        //System.out.println(handles.size());
        
       Iterator it = handles.iterator();
       
       while(it.hasNext()) {
    	   
    	   String childWindow = (String) it.next();
    	   
    	  if(!parentWindow.equals(childWindow)){
    		  
    		  
    		  break;
    		  
    	  }
    	  
    	  
    	  
    	  //System.out.println(childWindow);
    	  driver.switchTo().window(childWindow);
    	  String newPageText = driver.findElement(By.xpath("//*[contains(text(),'CashPro')]")).getText();
    	  System.out.println("New page" + newPageText);
    	  
    	   
    	   
       }
       
       driver.switchTo().window(parentWindow);
       String actual = driver.findElement(By.xpath("//*[contains(text(),'Businesses & Institutions')]")).getText();
       
       if(actual.contentEquals("Businesses & Institutions")) {
    	   
    	   System.out.println("Congrats! You did it");
       }
       
       
       
        
	}

}
