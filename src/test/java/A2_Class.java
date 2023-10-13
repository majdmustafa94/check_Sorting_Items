import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class A2_Class {

    public WebDriver driver;

    @BeforeTest

    public void login(){

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();

        driver.get("https://saucedemo.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

    }

//    @Test()
    public void sort_item_low_to_high() {

        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]")).click();

        List<WebElement> PricesItems= driver.findElements(By.className("inventory_item_price"));

        List<Double> newList= new ArrayList<>();

        for (int i=0; i<PricesItems.size();i++){

            String Price= PricesItems.get(i).getText();
            String edirPrice= Price.replace("$"," ");
            double Val= Double.parseDouble(edirPrice.trim());
            newList.add(Val);
        }

        for (int k=0; k<newList.size();k++){
            boolean check= newList.get(0) < newList.get(newList.size()-1);
            Assert.assertEquals(check,true);
        }

    }

    @Test()
    public void sort_item_high_to_low(){
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[4]")).click();

        List<WebElement> PriceItems2= driver.findElements(By.className("inventory_item_price"));
        List<Double> newList2= new ArrayList<>();

        for (int i=0; i<PriceItems2.size();i++){
            String Price2= PriceItems2.get(i).getText();
            String editPrice2= Price2.replace("$"," ");
            Double Val2= Double.parseDouble(editPrice2.trim());
            newList2.add(Val2);
        }

        for (int k=0; k<newList2.size()-1;k++){
            boolean check2= newList2.get(k) > newList2.get(newList2.size()-1);
            Assert.assertEquals(check2,true);
        }

    }

}

