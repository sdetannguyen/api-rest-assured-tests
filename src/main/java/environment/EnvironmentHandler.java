package environment;

import constants.LogConstants;
import enumerations.EnvironmentType;
import exceptions.AutomationTestRunException;
import exceptions.AutomationUtilException;
import model.configurationmodel.EnvironmentModel;
import org.apache.log4j.Logger;
import utilities.ConvertUtil;

public class EnvironmentHandler {
    private static final Logger LOGGER = Logger.getLogger(EnvironmentHandler.class);

    private static final String ENVIRONMENT_PARAM = "environment";

    private static EnvironmentHandler instance;
    private static EnvironmentModel environmentModel;

    private EnvironmentHandler(EnvironmentType environmentType) {
        String currentEnvParam = System.getProperty(ENVIRONMENT_PARAM);
        EnvironmentType environment;
        if (currentEnvParam != null) {
            environment = convertEnvironmentStringToEnvironmentType(currentEnvParam);
        } else {
            if (environmentType != null) {
                environment = environmentType;
            } else {
                //Default environment is STAGING
                environment = EnvironmentType.STAGING;
            }
        }
        LOGGER.info(String.format(LogConstants.ENVIRONMENT_INITIALIZATION_MESSAGE, environment.toString()));
        try {
            environmentModel = ConvertUtil.convertYmlFileToObject(environment.getUrl(), EnvironmentModel.class);
        } catch (AutomationUtilException e) {
            throw new AutomationTestRunException(LogConstants.FAILED_TO_INITIAL_TEST_ENVIRONMENT, e);
        }
    }

    public static EnvironmentHandler getInstance(EnvironmentType environmentType) {
        if (instance == null) {
            instance = new EnvironmentHandler(environmentType);
        }
        return instance;
    }

    public static EnvironmentHandler getInstance() {
        if (instance == null) {
            instance = new EnvironmentHandler(null);
        }
        return instance;
    }

    public EnvironmentModel getEnvironment() {
        return environmentModel;
    }

    private EnvironmentType convertEnvironmentStringToEnvironmentType(String s) {
        EnvironmentType environmentType = EnvironmentType.STAGING;
        try {
            environmentType = EnvironmentType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.error(String.format(LogConstants.TEST_ENVIRONMENT_DOES_NOT_EXISTS, s) + e);
        }
        return environmentType;
    }
}
