package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Driver;

public class Hooks {
    @After
    public void teardown() {
        Driver.quitDriver();
    }
}
