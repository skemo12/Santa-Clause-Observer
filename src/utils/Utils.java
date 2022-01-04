package utils;

import data.Child;
import data.Database;

import java.util.Objects;

public final class Utils {
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

    /**
     * Returns the child who has the id from parameters.
     *
     * @param id wanted id to find
     */
    public Child getChildById(final Integer id) {
        for (Child child : Database.getInstance().getChildren()) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Increases the ages of all childs.
     */
    public void increaseAge() {
        for (Child child : Database.getInstance().getChildren()) {
            child.setAge(child.getAge() + 1);
        }
    }

    /**
     * Returns the index of a child in list, searched by id, not Child object.
     * @param child child whose id we want to find the index of
     */
    public int getIndexOfChild(final Child child) {
        for (int i = 0; i < Database.getInstance().getChildren().size(); i++) {
            Child childCurrent = Database.getInstance().getChildren().get(i);
            if (Objects.equals(childCurrent.getId(), child.getId())) {
                return i;
            }
        }
        return -1;
    }
}
