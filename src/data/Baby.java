package data;

import Interface.SantaVisitorInterface;
import Interface.Visitable;

import java.util.List;

public class Baby extends Child implements Visitable {

    public Baby(Integer id, Integer age, Double niceScore, String firstName,
                String lastName, String city, List<String> giftsPreferences) {
        super(id, age, niceScore, firstName, lastName, city, giftsPreferences);
    }
    public Baby(Child child) {
        super(child);
    }
    @Override
    public void accept(SantaVisitorInterface visitor) {
        visitor.visit(this);
    }
}
