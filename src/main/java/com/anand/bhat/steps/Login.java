package com.anand.bhat.steps;

import com.anand.bhat.utility.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class Login extends CommonMethods {

    /**
     * @auther: anand
     * @created: 18-01-2022
     **/
    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Login.class.getName());
    Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        String userDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDir + "/src/main/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();

    }


    @Given("^login to the application$")
    public void loginToTheApplication() throws IOException {
        loadPropertyFile();
        driver.get(readPropertyFile("URL"));
        getScreenshot(driver, scenario);
        driver.manage().window().maximize();

    }


    @After
    public void after(Scenario scenario) {
        this.scenario = scenario;
        logger.info(scenario.getName());
        logger.info(scenario.getStatus());
        driver.close();
        driver.quit();

    }
}
