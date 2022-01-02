package data;

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
}
