
package model.datamodel;

import java.util.List;

@lombok.Data
class Data {

    private String envId;
    private String envName;
    private List<Gateway> gateways;
    private Boolean isDefault;
    private List<Object> tenants;

}
