package constants;

public final class LogConstants {

    private LogConstants() {
    }

    //Data log
    public static final String DATA_SET_IS_NOT_SUPPORTED = "Data set %s is not supported!";

    public static final String INITIALIZING_TEST_DATA_BY_RESOURCE_FILE = "Initializing test data by resource file >>> ";

    public static final String FORCE_TO_DESTROY_DATA_PROCESS = "Forcing to destroy all data...";

    public static final String FORCE_TO_DESTROY_DATA_COMPLETED = "Completed destroying all data >>>> ";

    //Convert log
    public static final String CONVERT_FILE_PATH = "File path >>>> ";

    public static final String CONVERT_JSON_FILE_TO_OBJECT = "Failed to convert json file to object >>>> ";

    public static final String CONVERT_YML_FILE_TO_OBJECT = "Failed to convert yml file to object >>>> ";

    public static final String CONVERT_JSON_FILE_TO_MAP = "Failed to convert json file to map >>>> ";

    public static final String CONVERT_GENERIC_OBJECT_TO_OBJECT = "Failed to convert generic object to object >>>> ";

    //Environment configuration log
    public static final String ENVIRONMENT_INITIALIZATION_MESSAGE = "******** [KARROS TECH] INITIALIZING TEST ENVIRONMENT [%s] ********";

    public static final String FAILED_TO_INITIAL_TEST_ENVIRONMENT = "Failed to initializing test environment, please check your yaml config file!!!";

    public static final String TEST_ENVIRONMENT_DOES_NOT_EXISTS = "The test environment [%s] doesn't exists. Please input the correct one! Error >>>> ";
}
