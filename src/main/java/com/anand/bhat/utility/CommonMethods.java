package com.anand.bhat.utility;


import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;




public class CommonMethods {

    /**
     * @auther: anand
     * @created: 18-01-2022
     **/

    private static Logger logger = LogManager.getLogger(CommonMethods.class.getName());
    private Properties properties;

    public void loadPropertyFile() throws IOException {

        String userDir = System.getProperty("user.dir");
        File envProperty = new File(userDir + "/src/test/resources/Env/Environment.properties");

        properties = new Properties();
        InputStream inputStream = new FileInputStream(envProperty);
        properties.load(inputStream);
    }

    public String readPropertyFile(String key) {
        return properties.getProperty(key);
    }

    public static void getScreenshot(WebDriver driver,Scenario scenario) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy-HH_mm_ss");
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        String userDir = System.getProperty("user.dir");
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenshotLocation = new File(userDir + "/src/main/resources/ScreenShot/" + simpleDateFormat.format(timestamp).concat(".png"));
        FileUtils.copyFile(screenshot, screenshotLocation);
        byte[] data = Files.readAllBytes(screenshotLocation.toPath());
        scenario.attach(data, "image/png", "My screenshot");
    }


}
