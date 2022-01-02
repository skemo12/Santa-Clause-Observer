package data;

import common.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Child implements Comparable<Child> {
    public Integer id;
    public Integer age;
    public Double niceScore;
    public String firstName;
    public String lastName;
    public String city;
    public List<String> giftsPreferences;
    public List<Double> previousNiceScores;
    public List<Gift> receivedGifts;

    public Child(Child child) {
        this.id = child.id;
        this.age = child.age;
        this.niceScore = child.niceScore;
        this.firstName = child.firstName;
        this.lastName = child.lastName;
        this.city = child.city;
        this.giftsPreferences = child.giftsPreferences;
        this.previousNiceScores = child.previousNiceScores;
        this.receivedGifts = child.receivedGifts;
    }

    public Child(Integer id, Integer age, Double niceScore, String firstName,
                 String lastName, String city, List<String> giftsPreferences) {
        this.id = id;
        this.age = age;
        this.niceScore = niceScore;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.giftsPreferences = giftsPreferences;
        this.previousNiceScores = new ArrayList<>();
        this.receivedGifts = new ArrayList<>();
    }

    public void updateScoresAndAge() {

        List<Child> updatedChildren = new ArrayList<>();
        for (Child child : Database.getInstance().getChildren()) {
            if (child.age <= Constants.BABY) {
                updatedChildren.add(new Baby(child));
            } else if (child.age <= Constants.KID) {
                ((Kid) child).accept(Database.getInstance().getSanta());
            } else if (child.age <= Constants.TEEN) {
                ((Teen) child).accept(Database.getInstance().getSanta());}
        }
        ListIterator<Child> childListIterator = updatedChildren.listIterator();
        while (childListIterator.hasNext()){
            Child child = childListIterator.next();
            if (child.age <= Constants.BABY) {
                ((Baby) child).accept(Database.getInstance().getSanta());
            } else if (child.age <= Constants.KID) {
                ((Kid) child).accept(Database.getInstance().getSanta());
            } else if (child.age <= Constants.TEEN) {
                ((Teen) child).accept(Database.getInstance().getSanta());
            } else {
                childListIterator.remove();
            }
        }
    }

    @Override
    public int compareTo(Child o) {
        return id - o.id;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", age=" + age +
                ", niceScore=" + niceScore +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", giftsPreferences=" + giftsPreferences +
                ", previousNiceScores=" + previousNiceScores +
                ", receivedGifts=" + receivedGifts +
                '}';
    }
}
