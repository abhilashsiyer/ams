package com.simple.ams.common;

import com.simple.ams.core.Core;
import com.simple.ams.util.CustomRunner;
import com.simple.ams.util.JSONReader;
import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;
import java.nio.file.Paths;


public class CommonSteps {

    private Core core;

    public CommonSteps(Core core) {
        this.core = core;
    }

    protected JSONReader jsonReader;


    @Given("^Launch \"([^\"]*)\"$")
    public void launch(String url) {
        core.launchWebSite(url);
    }

    @When("^click by id \"([^\"]*)\"$")
    public void click(String id){
        core.element(By.id(id)).click();
    }

    @And("^click text \"([^\"]*)\"$")
    public void clickText(String text) {
        core.element(By.xpath("//*[text()='"+text+"']")).click();
    }

    @And("^click \"([^\"]*)\" from json \"([^\"]*)\"$")
    public void clickUsing(String key, String page){
        jsonReader = new JSONReader("support/pages/"+page);
        core.element(jsonReader,key).click();
    }


    @And("^enter value as \"([^\"]*)\" at \"([^\"]*)\" from json\"([^\"]*)\"$")
    public void enterValueAsAtFromJson(String value, String key, String page) {
        jsonReader = new JSONReader("support/pages/"+page);
        core.element(jsonReader,key).sendKeys(value);
    }

    @And("^scroll to \"([^\"]*)\" from json \"([^\"]*)\"$")
    public void scrollToInPage(String key, String page) {
        jsonReader = new JSONReader("support/pages/"+page);
        core.scrollUsingJS(jsonReader,key);
    }


    @When("^enter value as \"([^\"]*)\" at id \"([^\"]*)\"$")
    public void enterValueAsAtId(String value, String id) {
        core.element(By.id(id)).sendKeys(value);
    }

    private boolean verifyChart(String fileName, WebElement element) {

        Path path = Paths.get(fileName);

        OcularResult result = Ocular.snapshot()
                .from(path)
                .sample()
                .using(core.getDriver())
                .element(element)
                .compare();

        return result.isEqualsImages();
    }

    @Then("^Verify chart \"([^\"]*)\" at \"([^\"]*)\"$")
    public void verifyChartAt(String fileName, String chartId){
        Assert.assertTrue(verifyChart("expenses.png",core.element(By.id("canvas"))));
    }

    @And("^Setup ocular$")
    public void setupOcular() {
        Ocular.config()
                .snapshotPath(Paths.get(".", "src/test/resources/snap"))
                .resultPath(Paths.get(".", "src/test/resources/result"));
    }


    @Then("^verify \"([^\"]*)\" id is shown$")
    public void verifyIsShown(String id) {
        Assert.assertTrue(core.element(By.id(id)).isDisplayed());
    }

    @Then("^verify \"([^\"]*)\" text is shown$")
    public void verifyTextIsShown(String text) {
        Assert.assertTrue(core.element(By.xpath("//*[text()='"+text+"']")).isDisplayed());
    }

    @Then("^verify that at id \"([^\"]*)\" shows \"([^\"]*)\"$")
    public void shows(String id, String text) {
        Assert.assertTrue(core.element(By.id(id)).getText().startsWith(text));
    }

    @Given("^Run steps from tag\"([^\"]*)\"$")
    public void setupAppWithScenario(String tag) {
        CustomRunner.runFeature(tag);
    }

    @Then("^verify \"([^\"]*)\" in \"([^\"]*)\" json shows \"([^\"]*)\"$")
    public void verifyInPageShows(String key, String page, String value) {
        jsonReader = new JSONReader("support/pages/"+page);
        Assert.assertTrue(core.element(jsonReader,key).getText().startsWith(value));
    }

    @When("^A user wants to purchase a ticket$")
    public void aUserWantsToPurchaseATicket() {
        clickText("");
    }

    @When("^select visible text \"([^\"]*)\" in \"([^\"]*)\" dropdown from json \"([^\"]*)\"$")
    public void selectVisibleTextFromDropdownIn(String value, String key, String page) {
        jsonReader = new JSONReader("support/pages/"+page);
        core.selectFromDropDown(jsonReader,key,value);
    }

}
