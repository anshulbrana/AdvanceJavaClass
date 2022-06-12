package services;

import org.junit.jupiter.api.Test;
import services.Configuration;


public class TestConfiguration {

 //static because It will be executed before everything
    static {
        System.setProperty("conf.file","src/test/resources/conf.properties");
    }


    @Test
    public void fetchTestValue(){
        Configuration configuration = Configuration.getInstance();


    }
}
