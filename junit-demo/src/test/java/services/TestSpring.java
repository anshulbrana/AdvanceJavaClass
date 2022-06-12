package services;

import conf.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;


//Tells junit that we need spring to start our application
@ExtendWith(SpringExtension.class)

//Where we can find injection context
@ContextConfiguration(classes = {ApplicationConfiguration.class})

public class TestSpring {


    @Inject
    String userName;

    @Test
    public void testInjection(){
        Assertions.assertNotNull(userName);
        System.out.println(userName);

    }
}
