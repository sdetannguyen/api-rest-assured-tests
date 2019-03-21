package apis.services;

import apis.AbstractBaseRequest;
import apis.endpoints.APIEndpoint;
import constants.RequestHeader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang.StringUtils;
import utilities.EmailValidator;
import utilities.RequestSpecBuilderUtil;

public class EnvironmentConfigService extends AbstractBaseRequest {

    private String userId;

    public EnvironmentConfigService(String userId) {
        this.userId = userId;
    }

    public Response getEnvironmentOfUser() {
        new EmailValidator().validate(userId);
        RequestSpecification spec = RequestSpecBuilderUtil.specWithJson(new RequestSpecBuilder()
                .setBaseUri(environment.getEnvConfigUrl().concat(StringUtils.replace(
                        APIEndpoint.DETECT_APP_ENVIRONMENT_OF_USER, "{userId}", userId)))
                .addHeader(RequestHeader.Key.CLIENT_SECRET, environment.getClientSecretToken())
                .addHeader(RequestHeader.Key.API_KEY, environment.getApikeyToken())
                .addHeader(RequestHeader.Key.SCOPE, RequestHeader.Value.PARENT_PORTAL_LITE)
                .build());
        return sendAGetRequest(spec);
    }
}
