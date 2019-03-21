package apis.endpoints;

public final class JsonEndpoint {

    private JsonEndpoint() {
    }

    private static final String USER_DIRECTORY = System.getProperty("user.dir");

    public static final String SUCCESS_RESPONSE_DETECT_ENVIRONMENT_OF_USER = USER_DIRECTORY +
            "/src/main/resources/assets/apiresponseassets/success_response_detect_environment_of_user.json";

}
