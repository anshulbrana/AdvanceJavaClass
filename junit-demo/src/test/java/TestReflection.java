import datamodel.Passenger;

import java.lang.reflect.Field;

public class TestReflection {
    public void testReflection() throws NoSuchFieldException, IllegalAccessException {

        Passenger passenger = new Passenger("test", 22, 10, true, 1 );

        //To get all the field of the class where getClass() is in build function of java maven
        //Setting up name or changing them directly used when we are passing field dynamically by our self

        Field name = passenger.getClass().getField("name");
        name.setAccessible(true);
        name.set(passenger,"newName");

        Field trueValue = Boolean.class.getField("TRUE");
        trueValue.setAccessible(true);
        trueValue.setBoolean(null, false);
    }
}
