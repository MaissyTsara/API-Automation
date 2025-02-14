import org.testng.Assert;
import org.testng.annotations.Test;

import example.StaticProvider;

public class ParallelTestNG2 {

    String name = "AfterOffice12";


    @Test
    public void scenarioTest1(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 11");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest2(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 12");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest3(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 13");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest4(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 14");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest5(){
        Assert.assertEquals(name,"AfterOffice12");
        System.out.println("- Scenario 15");
        System.out.println(Thread.currentThread().getId());
    }

    @Test(dataProvider = "dataproviderNegative", dataProviderClass = StaticProvider.class)
    public void dataTestScenario(String name, int age){
        System.out.println("Nama : " + name + ", Umur : " + age);
    }

}