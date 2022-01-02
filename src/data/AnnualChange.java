package data;

import java.util.List;

public class AnnualChange {
    Double newSantaBudget;
    List<Gift> newGifts;
    List<Child> newChildren;
    List<ChildUpdate> childrenUpdates;

    public AnnualChange(Double newSantaBudget, List<Gift> newGifts,
                        List<Child> newChildren, List<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    @Override
    public String toString() {
        return "AnnualChange{" +
                "newSantaBudget=" + newSantaBudget +
                ", newGifts=" + newGifts +
                ", newChildren=" + newChildren +
                ", childrenUpdates=" + childrenUpdates +
                '}';
    }
}
