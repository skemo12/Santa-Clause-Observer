package annualchanges;

import enums.Category;

import java.util.List;

/**
 * Class that stores the data of a child update from input
 */
public final class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private List<Category> giftsPreferences;

    public ChildUpdate(final Integer id, final Double niceScore,
                       final List<Category> giftsPreferences) {
        this.setId(id);
        this.setNiceScore(niceScore);
        this.setGiftsPreferences(giftsPreferences);
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
