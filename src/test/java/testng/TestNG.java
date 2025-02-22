package testng;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.StaticProvider;

public class TestNG {

    String name = "AfterOffice12";

    @BeforeClass
    public void setUpBeforeClass(){
        System.out.println("> Ini untuk setup Class");

        // Scenario login
        // Set API, set Credential
        // Set URL website
    }

    @Test
    public void checkoutBarang(){
        // Checkout barang

        /*
         * login
         * checkout
         */
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("Before method");
    }

    @Test
    public void scenarioTest1(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 1");
    }

    @Test
    public void scenarioTest2(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 2");
    }

    @Test
    public void scenarioTest3(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 3");
    }

    @Test(dataProvider = "dataproviderPositive", dataProviderClass = StaticProvider.class)
    public void dataTestScenario(String name, int age){
        System.out.println("Nama : " + name + ", Umur : " + age);
    }

    @AfterMethod
    public void afterUp(){
        System.out.println("After Method");
    }

    @AfterClass
    public void setUpAfterClass(){
        System.out.println("> Ini adalah setup after class");
    }

    // @DataProvider(name="dataprovider")
    // public Object[][] dataTestPositiveCase(){
    //     return new Object[][]{
    //         {"Maissy", 10},
    //         {"Tsara", 20},
    //         {"Permata", 30}
    //     };
    // }

    // @DataProvider(name="dataprovider")
    // public Object[][] dataTestNegativeCase(){
    //     return new Object[][]{
    //         {"Rudy", 40},
    //         {"Sari", 50},
    //         {"Joko", 60}
    //     };
    // }

}