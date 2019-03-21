package dataflow;

import enumerations.dataflowenum.ParentPortalDataSet;
import enumerations.dataflowenum.ParentPortalLiteDataSet;
import model.GenericModel;

public interface DataByFlow {

    GenericModel onParentPortalDataFlow();

    GenericModel onParentPortalDataFlow(ParentPortalDataSet parentPortalDataSet);

    GenericModel onParentPortalLiteDataFlow();

    GenericModel onParentPortalLiteDataFlow(ParentPortalLiteDataSet parentPortalLiteDataSet);

    void destroyAll();

}
