package fileio;

import data.Child;

import java.util.ArrayList;
import java.util.List;

public final class ChildrenChanges {
    private List<Child> children;

    public ChildrenChanges(final List<Child> children) {
        this.setChildren(new ArrayList<>());
        for (Child child : children) {
            Child newChild = new Child(child);
            this.getChildren().add(newChild);
        }

    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }
}
