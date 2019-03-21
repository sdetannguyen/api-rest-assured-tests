package enumerations.dataflowenum;

public enum ParentPortalDataSet {

    ALL("all"),
    DATA_POOL_1("dataForPP-pool-1");

    private final String value;

    ParentPortalDataSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
