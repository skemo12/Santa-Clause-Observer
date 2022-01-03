package Utils;


import common.Constants;
import data.Child;
import data.Database;
import data.Santa;
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

    public Child getChildById (Integer id) {
        for (Child child : Database.getInstance().getChildren()) {
            if (child.id.equals(id)) {
                return child;
            }
        }
        return null;
    }

    public void increaseAge () {
        for (Child child : Database.getInstance().getChildren()) {
            child.age++;
        }
    }
    public void updateBudget (int year) {
        Santa santa = Database.getInstance().getSanta();
        Double newBudget = Database.getInstance().getAnnualChanges().get(year).getNewSantaBudget();
        santa.setSantaBudget(newBudget);
    }
    public int getIndexOfChild(Child child) {
        for (int i = 0; i < Database.getInstance().getChildren().size(); i++) {
            Child childCurrent = Database.getInstance().getChildren().get(i);
            if (childCurrent.id == child.id) {
                return i;
            }
        }
        return -1;
    }
}
