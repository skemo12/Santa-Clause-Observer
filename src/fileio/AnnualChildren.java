package fileio;

import data.Child;
import data.Database;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildren {
    List<ChildrenChanges> annualChildren;

    public AnnualChildren() {
        this.annualChildren = new ArrayList<>();
    }

    public List<ChildrenChanges> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(List<ChildrenChanges> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public void addYear(ChildrenChanges childrenChanges) {
        annualChildren.add(childrenChanges);
    }
}
