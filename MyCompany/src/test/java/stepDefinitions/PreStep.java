package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import properties.RunSettings;
import wandui.util.DataLoad;

public class PreStep {
	
	 private Scenario scenario;
	    @Before
	    public void before(Scenario scenario) {
	        this.scenario =scenario;
	        RunSettings.scenarioName= scenario.getName();
	        DataLoad.initialization();



	    }

}
