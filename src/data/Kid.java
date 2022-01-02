package data;

import Interface.SantaVisitorInterface;
import Interface.Visitable;

import java.util.List;

public class Kid extends Child implements Visitable {
    public Kid(Integer id, Integer age, Double niceScore, String firstName,
               String lastName, String city, List<String> giftsPreferences) {
        super(id, age, niceScore, firstName, lastName, city, giftsPreferences);
    }

    @Override
    public void accept(SantaVisitorInterface visitor) {
        visitor.visit(this);
    }
}
