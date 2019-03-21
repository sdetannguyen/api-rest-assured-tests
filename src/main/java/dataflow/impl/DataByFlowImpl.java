package dataflow.impl;

import constants.LogConstants;
import dataflow.DataByFlow;
import dataflow.JsonEndpoint;
import enumerations.dataflowenum.ParentPortalDataSet;
import enumerations.dataflowenum.ParentPortalLiteDataSet;
import model.GenericModel;
import org.apache.log4j.Logger;
import utilities.ConvertUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DataByFlowImpl implements DataByFlow {

    private static final Logger LOGGER = Logger.getLogger(DataByFlowImpl.class);

    private static final ThreadLocal<GenericModel> threadLocalAllParentPortalData = new ThreadLocal<>();
    private static final ThreadLocal<GenericModel> threadLocalAllParentPortalLiteData = new ThreadLocal<>();
    private static final ThreadLocal<GenericModel> threadLocalParentPortalData = new ThreadLocal<>();
    private static final ThreadLocal<GenericModel> threadLocalParentPortalLiteData = new ThreadLocal<>();

    @Override
    public GenericModel onParentPortalDataFlow() {
        if(threadLocalAllParentPortalData.get() == null) {
            LOGGER.info(LogConstants.INITIALIZING_TEST_DATA_BY_RESOURCE_FILE);
            threadLocalAllParentPortalData.set(ConvertUtil.convertJsonFileToObject(JsonEndpoint.PARENT_PORTAL_DATA_PATH, GenericModel.class));
        }
        return threadLocalAllParentPortalData.get();
     }

    @Override
    public GenericModel onParentPortalDataFlow(ParentPortalDataSet parentPortalDataSet) {
        if(!parentPortalSupplier.containsKey(parentPortalDataSet)) {
            throw new IllegalArgumentException(String.format(LogConstants.DATA_SET_IS_NOT_SUPPORTED, parentPortalDataSet.getValue()));
        }
        threadLocalParentPortalData.set(parentPortalSupplier.get(parentPortalDataSet).get());
        return threadLocalParentPortalData.get();
    }

    @Override
    public GenericModel onParentPortalLiteDataFlow() {
        LOGGER.info(LogConstants.INITIALIZING_TEST_DATA_BY_RESOURCE_FILE);
        if(threadLocalAllParentPortalLiteData.get() == null) {
            threadLocalAllParentPortalLiteData.set(ConvertUtil.convertJsonFileToObject(JsonEndpoint.PARENT_PORTAL_LITE_DATA_PATH, GenericModel.class));
        }
        return threadLocalAllParentPortalLiteData.get();
    }

    @Override
    public GenericModel onParentPortalLiteDataFlow(ParentPortalLiteDataSet parentPortalLiteDataSet) {
        if (!parentPortalLiteSupplier.containsKey(parentPortalLiteDataSet)) {
            throw new IllegalArgumentException(String.format(LogConstants.DATA_SET_IS_NOT_SUPPORTED, parentPortalLiteDataSet.getValue()));
        }
        threadLocalParentPortalLiteData.set(parentPortalLiteSupplier.get(parentPortalLiteDataSet).get());
        return threadLocalParentPortalLiteData.get();
    }

    @Override
    public void destroyAll() {
        LOGGER.info(LogConstants.FORCE_TO_DESTROY_DATA_PROCESS);
        threadLocalAllParentPortalData.remove();
        threadLocalAllParentPortalLiteData.remove();
        threadLocalParentPortalData.remove();
        threadLocalParentPortalLiteData.remove();
        LOGGER.info(LogConstants.FORCE_TO_DESTROY_DATA_COMPLETED);
    }

    private Map<ParentPortalDataSet, Supplier<GenericModel>> parentPortalSupplier = new HashMap<ParentPortalDataSet, Supplier<GenericModel>>() {
        {
            put(ParentPortalDataSet.ALL, () -> ConvertUtil.convertGenericObjectToObject(onParentPortalDataFlow(),
                    GenericModel.class));
            put(ParentPortalDataSet.DATA_POOL_1, () -> ConvertUtil.convertGenericObjectToObject(onParentPortalDataFlow()
                    .getProperties().get(ParentPortalDataSet.DATA_POOL_1.getValue()), GenericModel.class));
        }
    };

    private Map<ParentPortalLiteDataSet, Supplier<GenericModel>> parentPortalLiteSupplier = new HashMap<ParentPortalLiteDataSet, Supplier<GenericModel>>() {
        {
            put(ParentPortalLiteDataSet.ALL, () -> ConvertUtil.convertGenericObjectToObject(onParentPortalLiteDataFlow(),
                    GenericModel.class));
            put(ParentPortalLiteDataSet.DATA_POOL_1, () -> ConvertUtil.convertGenericObjectToObject(onParentPortalLiteDataFlow()
                    .getProperties().get(ParentPortalLiteDataSet.DATA_POOL_1.getValue()), GenericModel.class));
        }
    };
}
