package apis.endpoints;

/**
 * Define api's endpoint in here
 */
public final class APIEndpoint {

    private APIEndpoint() {
    }

    public static final String DETECT_APP_ENVIRONMENT_OF_USER = "envs/user/{userId}";

    public static final String SIGNIN = "api/v1/signin";

    public static final String SUBSCRIPTION = "api/v1/subscriptions";

    public static final String PERSONAL_INFORMATION = "api/v1/people/{personId}";

}
