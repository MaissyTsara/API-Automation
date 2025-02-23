package apiengine;

import org.testng.Assert;

import com.apiautomation.model.ResponseItem;
import com.apiautomation.model.request.RequestItem;

public class Assertion {

    public void assertAddObject(ResponseItem responseItem, RequestItem requestItem){
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, requestItem.addName); //actual, expected
        Assert.assertNotNull(responseItem.dataCreatedAt);
        Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
        Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);
    }

    public void assertGetSingleObject(ResponseItem responseItem, RequestItem requestItem){
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, requestItem.addName);
        Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
        Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);
    }

}
