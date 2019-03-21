package datafarm;

import lombok.Data;

/**
 * This class is to store testing data of Parent Portal application during run time for reuse purposes
 */
@Data
public class PPDataFarm {
    private PPDataFarm() {
    }

    private static PPDataFarm instance;

    public static PPDataFarm getInstance() {
        if(instance == null) {
            instance = new PPDataFarm();
        }
        return instance;
    }

    private String accessToken;
}
