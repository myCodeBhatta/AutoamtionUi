package com.anand.bhat;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(

        publish = true,
        features = "classpath:feature",
        tags = "@test"
)
public class TestRunner {
/**
 *@auther: anand
 *@created: 18-01-2022
 **/


}
