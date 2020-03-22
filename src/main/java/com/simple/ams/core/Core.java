package com.simple.ams.core;

import com.simple.ams.model.Elements;
import com.simple.ams.util.JSONReader;;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import cucumber.api.Scenario;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class Core {

    private WebDriver driver;

    public void launchWebSite(String url){
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        try {
            this.driver = new RemoteWebDriver(new URL("http://selenium__standalone-chrome:4444/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//
//        this.driver= new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize() ;
    }

    public void quit(){
        this.driver.quit();
    }

    private WebElement init(WebDriver driver, By by) {

        Wait wait = new FluentWait(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement element;
        element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver1 -> (WebElement) driver1.findElement(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public WebElement element(JSONReader jsonReader, String key){
        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);
        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        return init(this.driver, Identifier.valueOf(identifier.toUpperCase()).locator(element));
    }

    public WebElement element(By by) {

        Wait wait = new FluentWait(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement element;
        element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver1 -> (WebElement) driver1.findElement(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public void scrollUsingJS(JSONReader jsonReader, String key) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)getDriver();

        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);

        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",getDriver().
                findElement(Identifier.valueOf(identifier.toUpperCase()).locator(element)));
    }

    public void sendTextlUsingJS(JSONReader jsonReader, String key,String value) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)getDriver();

        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);

        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        javascriptExecutor.executeScript("arguments[0].value='"+value+"');",getDriver().
                findElement(Identifier.valueOf(identifier.toUpperCase()).locator(element)));
    }

    public void takeScreenShot(Scenario scenario){
        scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"image/png");
    }

    public void selectFromDropDown(JSONReader jsonReader, String key , String value){
        Elements elements = jsonReader.page.getElements().stream().filter(x->x.getName().equals(key)).
                collect(Collectors.toList()).get(0);
        String identifier = elements.getIdentifier();
        String element = elements.getValue();
        Select dropDown = new Select(getDriver().findElement(Identifier.valueOf(identifier.toUpperCase()).
                locator(element)));
        dropDown.selectByVisibleText(value);
    }
}
