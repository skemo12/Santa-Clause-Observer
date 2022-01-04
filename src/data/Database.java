package data;

import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Database {
    private Integer numberOfYears;
    private Santa santa;
    private List<Child> children;
    private List<AnnualChanges> annualChanges;

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

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Santa getSanta() {
        return santa;
    }

    public void setSanta(final Santa santa) {
        this.santa = santa;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }

    /**
     * Applies annual changes to database
     */
    public void updateDatabaseByYear(final int i) {
        AnnualChanges annualChange = Database.getInstance()
                .getAnnualChanges().get(i);

        if (annualChange.getNewSantaBudget() != null) {
            Database.getInstance().getSanta()
                    .setSantaBudget(annualChange.getNewSantaBudget());
        }
        for (Gift gift : annualChange.getNewGifts()) {
            Database.getInstance().getSanta().getGiftsList().add(gift);
        }

        for (Child child : annualChange.getNewChildren()) {
            Database.getInstance().getChildren().add(child);
        }
        Collections.sort(Database.getInstance().getChildren());

        for (ChildUpdate childUpdate : annualChange.getChildrenUpdates()) {
            Child child = Utils.getInstance().getChildById(childUpdate.getId());
            if (child != null) {
                if (childUpdate.getNiceScore() != null) {
                    child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                    child.setNiceScore(childUpdate.getNiceScore());
                }
                List<String> giftPrefs = new ArrayList<>();
                for (String gift : childUpdate.getGiftsPreferences()) {
                    if (!giftPrefs.contains(gift)) {
                        giftPrefs.add(gift);
                    }
                }
                for (String gift : child.getGiftsPreferences()) {
                    if (!giftPrefs.contains(gift)) {
                        giftPrefs.add(gift);
                    }
                }
                child.setGiftsPreferences(giftPrefs);
            }

        }
    }
    /**
     * Completely resets database to prepare for new input file.
     */
    public static void renewDatabase() {
        database = null;
        database = new Database();
    }
}
