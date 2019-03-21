package enumerations.dataflowenum;

public enum ParentPortalLiteDataSet {

    ALL("all"),
    DATA_POOL_1("dataForPPL-pool-1");

    private final String value;

    ParentPortalLiteDataSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
