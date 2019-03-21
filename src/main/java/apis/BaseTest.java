package apis;

import dataflow.DataByFlow;
import dataflow.ParentPortalDataFlow;
import dataflow.ParentPortalLiteDataFlow;
import dataflow.impl.DataByFlowImpl;
import enumerations.dataflowenum.ParentPortalDataSet;
import enumerations.dataflowenum.ParentPortalLiteDataSet;
import environment.DecepticonListener;
import exceptions.AutomationTestRunException;
import model.GenericModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Listeners(DecepticonListener.class)
public abstract class BaseTest extends AbstractBaseRequest {

    private DataByFlow dataByFlow = new DataByFlowImpl();

    @BeforeMethod
    public void setUpMethod() {
    }

    @AfterMethod
    public void tearDownMethod() {
        dataByFlow.destroyAll();
    }

    /**
     * Call this if you already initialized data for specific test not ParentPortalDataSet.ALL or ParentPortalLiteDataSet.ALL
     *
     * @param key key of data
     * @return value of data
     */
    protected String getPreConditionData(String key) {
        return getPreConditionData().getProperties().get(key).toString();
    }

    protected GenericModel getPreConditionData() {
        return dataSupplier.get(getAnnotateFromTestClass().annotationType().getName()).get();
    }

    private Annotation getAnnotateFromTestClass() {
        if(getClass().getDeclaredMethods().length > 1) {
            throw new AutomationTestRunException("Except one test method per test scripts only. Please remove the additional one!!!");
        }
        if (isMoreThan2DataAnnotations()) {
            throw new AutomationTestRunException("Please add one data annotation for one test only!!!");
        }
        return Arrays.stream(getTestMethod().getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().getName().contains("DataFlow"))
                .findAny()
                .orElseThrow(() -> new AutomationTestRunException("Please add data annotation to initialize data for your test!!!"));
    }

    private boolean isMoreThan2DataAnnotations() {
        final Method method = getTestMethod();
        return method.isAnnotationPresent(ParentPortalDataFlow.class)
                && method.isAnnotationPresent(ParentPortalLiteDataFlow.class);
    }

    private Method getTestMethod() {
        return Arrays.stream(getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .findAny().orElseThrow(() -> new AutomationTestRunException("No test method with @Test was found. Please check your test!!!"));
    }

    private Map<ParentPortalDataSet, Supplier<GenericModel>> parentPortalDataSupplier = new HashMap<ParentPortalDataSet, Supplier<GenericModel>>() {
        {
            put(ParentPortalDataSet.ALL, () -> dataByFlow.onParentPortalDataFlow());
            put(ParentPortalDataSet.DATA_POOL_1, () -> dataByFlow.onParentPortalDataFlow(ParentPortalDataSet.DATA_POOL_1));
        }
    };

    private Map<ParentPortalLiteDataSet, Supplier<GenericModel>> parentPortalLiteDataSupplier = new HashMap<ParentPortalLiteDataSet, Supplier<GenericModel>>() {
        {
            put(ParentPortalLiteDataSet.ALL, () -> dataByFlow.onParentPortalLiteDataFlow());
            put(ParentPortalLiteDataSet.DATA_POOL_1, () -> dataByFlow.onParentPortalLiteDataFlow(ParentPortalLiteDataSet.DATA_POOL_1));
        }
    };

    private Map<String, Supplier<GenericModel>> dataSupplier = new HashMap<String, Supplier<GenericModel>>() {
        {
            put("dataflow.ParentPortalDataFlow", () -> parentPortalDataSupplier.get(((ParentPortalDataFlow) getAnnotateFromTestClass()).init()).get());
            put("dataflow.ParentPortalLiteDataFlow", () -> parentPortalLiteDataSupplier.get(((ParentPortalLiteDataFlow) getAnnotateFromTestClass()).init()).get());
        }
    };

}
