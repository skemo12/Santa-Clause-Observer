package data;

import java.util.List;

public final class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private List<String> giftsPreferences;

    public ChildUpdate(final Integer id, final Double niceScore,
                       final List<String> giftsPreferences) {
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

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
