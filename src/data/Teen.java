package data;

import interfaces.SantaVisitorInterface;
import interfaces.Visitable;

import java.util.List;

public final class Teen extends Child implements Visitable {

    public Teen(final Integer id, final Integer age, final Double niceScore,
                final String firstName, final String lastName,
                final String city, final List<String> giftsPreferences) {
        super(id, age, niceScore, firstName, lastName, city, giftsPreferences);
    }

    public Teen(final Child child) {
        super(child);
    }

    @Override
    public void accept(final SantaVisitorInterface visitor) {
        visitor.visit(this);
    }
}
