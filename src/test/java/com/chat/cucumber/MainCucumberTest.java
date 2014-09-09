package com.chat.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, format = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"})
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class MainCucumberTest {
}
