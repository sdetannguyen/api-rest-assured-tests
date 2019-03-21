package testscripts;

import apis.BaseTest;
import apis.endpoints.JsonEndpoint;
import apis.services.EnvironmentConfigService;
import dataflow.ParentPortalLiteDataFlow;
import enumerations.dataflowenum.ParentPortalLiteDataSet;
import io.restassured.response.Response;
import model.datamodel.EnvironmentOfUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import utilities.ConvertUtil;

public class PPLLoginSuccessfullyTest extends BaseTest {

    private EnvironmentConfigService environmentConfigService = new EnvironmentConfigService(getPreConditionData("userId"));

    @Test @ParentPortalLiteDataFlow(init = ParentPortalLiteDataSet.DATA_POOL_1)
    public void pplUserLoginSuccessfullyTest() {
        EnvironmentOfUserResponse expectedResponse =
                ConvertUtil.convertJsonFileToObject(JsonEndpoint.SUCCESS_RESPONSE_DETECT_ENVIRONMENT_OF_USER, EnvironmentOfUserResponse.class);
        Response response = environmentConfigService.getEnvironmentOfUser();
        Assert.assertEquals(200, response.getStatusCode(), "Verify status code of get environment config of user");
        ReflectionAssert.assertLenientEquals("Verify response of detect environment of user api",
                expectedResponse, response.getBody().as(EnvironmentOfUserResponse.class));
    }

    @Test
    public void secondTest() {
        System.out.println("Second test");
    }
}
