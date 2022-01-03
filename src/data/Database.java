package data;

import Utils.Utils;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    Integer numberOfYears;
    Santa santa;
    List<Child> children;
    List<AnnualChange> annualChanges;

    /**
     * Make it singleton
     */
    private static Database database = null;
    /**
     * Singleton function
     */
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Santa getSanta() {
        return santa;
    }

    public void setSanta(Santa santa) {
        this.santa = santa;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public void updateDatabaseByYear(int i) {
        AnnualChange annualChange = Database.getInstance().annualChanges.get(i);
        Database.getInstance().getSanta().setSantaBudget(annualChange.newSantaBudget);
        for (Gift gift :annualChange.getNewGifts()) {
            Database.getInstance().getSanta().getGiftsList().add(gift);
        }

        for (Child child : annualChange.getNewChildren()) {
            Database.getInstance().getChildren().add(child);
        }
        Collections.sort(Database.getInstance().getChildren());

        for (ChildUpdate childUpdate : annualChange.childrenUpdates) {
            Child child = Utils.getInstance().getChildById(childUpdate.id);
            if (child != null) {
                if (childUpdate.niceScore != null) {
                    child.niceScoreHistory.add(childUpdate.niceScore);
                    child.niceScore = childUpdate.niceScore;
                }
                List<String> giftPrefs = new ArrayList<>();
                for (String gift : childUpdate.giftsPreferences) {
                    if (!giftPrefs.contains(gift)) {
                        giftPrefs.add(gift);
                    }
                }
                for (String gift : child.giftsPreferences) {
                    if (!giftPrefs.contains(gift)) {
                        giftPrefs.add(gift);
                    }
                }
                child.giftsPreferences = giftPrefs;
            }

        }
    }
    public static void renewDatabase (){
        database = null;
        database = new Database();
    }
}
