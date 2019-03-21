package datafarm;

import lombok.Data;

/**
 * This class is to store testing data of Parent Portal Lite application during run time for reuse purposes
 */

@Data
public class PPLDataFarm {
    private PPLDataFarm() {
    }

    private static PPLDataFarm instance;

    public static PPLDataFarm getInstance() {
        if(instance == null) {
            instance = new PPLDataFarm();
        }
        return instance;
    }

    private String accessToken;

}
