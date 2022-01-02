package Utils;


import common.Constants;
import data.Child;
import data.Database;
import org.json.simple.JSONObject;

public class Utils {
    /**
     * Make it singleton
     */
    private static Utils utils = null;
    /**
     * Singleton function
     */
    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }


}
