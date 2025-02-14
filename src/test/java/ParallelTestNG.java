import org.testng.Assert;
import org.testng.annotations.Test;

import example.StaticProvider;

public class ParallelTestNG {

    String name = "AfterOffice12";


    @Test
    public void scenarioTest1(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 1");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest2(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest3(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 3");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest4(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest5(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 3");
        System.out.println(Thread.currentThread().getId());
    }

    @Test(dataProvider = "dataproviderPositive", dataProviderClass = StaticProvider.class)
    public void dataTestScenario(String name, int age){
        System.out.println("Nama : " + name + ", Umur : " + age);
    }

}