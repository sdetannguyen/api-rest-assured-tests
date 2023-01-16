package utilities;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class CompareUtil {

    private CompareUtil() {
    }

    /**
     * Util to compare the real json response and mock json
     *
     * @param json     a json response from the server
     * @param mockJson mock json file need to compare
     * @param key      the value need to compare from both json file
     * @return result true or false when complete
     */
    public static boolean compareJsonResponse(JSONArray json, JSONArray mockJson, String key) {
        boolean result = true;
        for (int i = 0; i < json.size(); i++) {
            if (!StringUtils.equals(((LinkedHashMap) json.get(i)).get(key).toString(),
                    ((LinkedHashMap) mockJson.get(i)).get(key).toString())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean compareListWithKeys(ArrayList list1, ArrayList list2, List<String> listKey) {
        boolean result = true;
        if (list1.size() != list2.size()) {
            return false;
        }
        for (String key : listKey) {
            for (int i = 0; i < list1.size(); i++) {
                result = StringUtils.equals(((LinkedHashMap) list1.get(i)).get(key).toString(),
                        ((LinkedHashMap) list2.get(i)).get(key).toString());
            }
        }
        return result;
    }
}