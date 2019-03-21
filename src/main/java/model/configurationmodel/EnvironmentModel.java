package model.configurationmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentModel {

    private String envConfigUrl;

    private String serviceUrl;

    private String mockUrl;

    private String clientSecretToken;

    private String apikeyToken;

}
