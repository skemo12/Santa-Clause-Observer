package fileio;

import data.Child;
import data.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChildrenChanges {
    List<Child> children;

    public ChildrenChanges(List<Child> children) {
        this.children = new ArrayList<>();
        for (Child child : children) {
            Child newChild = new Child(child);
            this.children.add(newChild);
        }

    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
