package com.simple.ams.common;

import com.simple.ams.core.Core;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Setup {

    private Core core;

    public Setup(Core core) {
        this.core = core;
    }

    @After
    public void teardown(Scenario scenario){
        if(scenario.isFailed()) {
            core.takeScreenShot(scenario);
        }
        core.quit();
    }

}
