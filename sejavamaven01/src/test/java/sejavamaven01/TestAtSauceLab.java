package sejavamaven01;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAtSauceLab {
	
	
	@Test	
	public void testAtSauceLab() throws MalformedURLException {
		
		
		ChromeOptions options = new ChromeOptions();
		options.setPlatformName("Windows 10");
		options.setBrowserVersion("latest");

		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("username", System.getenv("xxx"));
		sauceOptions.put("accessKey", System.getenv("xxx"));
		//sauceOptions.put("name", testInfo.getDisplayName());

		options.setCapability("sauce:options", sauceOptions);
		
		URL url = new URL("xxx");

		RemoteWebDriver driver = new RemoteWebDriver(url, options);
		
       
		
        driver.get("https://business.bofa.com/content/boaml/en_us/home.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
    

     
        
        String parentWindow = driver.getWindowHandle();
		
        
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
    	  
  
    	  driver.switchTo().window(childWindow);
    	  //String newPageText = driver.findElement(By.xpath("//*[contains(text(),'CashPro')]")).getText();
    	  	   
    	   
       }
       
       driver.switchTo().window(parentWindow);
       String actual = driver.findElement(By.xpath("//*[contains(text(),'Businesses & Institutions')]")).getText();
       
       
       Assert.assertEquals(actual,"Businesses & Institutions" );
    
	}

	

}
