package fileio;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChildren {
    private List<ChildrenChanges> annualChildren;

    public AnnualChildren() {
        this.setAnnualChildren(new ArrayList<>());
    }

    public List<ChildrenChanges> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<ChildrenChanges> annualChildren) {
        this.annualChildren = annualChildren;
    }

    /**
     * Adds the result of the year to the output Object.
     */
    public void addYear(final ChildrenChanges childrenChanges) {
        getAnnualChildren().add(childrenChanges);
    }
}
