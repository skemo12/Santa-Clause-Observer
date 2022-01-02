package data;

import java.util.List;

public class ChildUpdate {
    Integer id;
    Double niceScore;
    List<String> giftsPreferences;

    public ChildUpdate(Integer id, Double niceScore,
                       List<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }
}
