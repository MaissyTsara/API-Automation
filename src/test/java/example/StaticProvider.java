package example;

import org.testng.annotations.DataProvider;

public class StaticProvider {
    
    @DataProvider(name="dataproviderPositive")
    public Object[][] dataTestPositiveCase(){
        return new Object[][]{
            {"Maissy", 10},
            {"Tsara", 20},
            {"Permata", 30}
        };
    }

    @DataProvider(name="dataproviderNegative")
    public Object[][] dataTestNegativeCase(){
        return new Object[][]{
            {"Rudy", 40},
            {"Sari", 50},
            {"Joko", 60}
        };
    }
}
