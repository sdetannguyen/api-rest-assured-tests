package dataflow;

public final class JsonEndpoint {

    private JsonEndpoint() {
    }

    private static final String USER_DIRECTORY = System.getProperty("user.dir");

    public static final String PARENT_PORTAL_DATA_PATH = USER_DIRECTORY + "/src/main/resources/assets/testdataassets/parent_portal_data.json";

    public static final String PARENT_PORTAL_LITE_DATA_PATH = USER_DIRECTORY + "/src/main/resources/assets/testdataassets/parent_portal_lite_data.json";
}
