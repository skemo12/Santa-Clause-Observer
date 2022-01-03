package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Child implements Comparable<Child> {
    public Integer id;
    public String lastName;
    public String firstName;
    public String city;
    public Integer age;
    public List<String> giftsPreferences;
    public Double averageScore;
    public List<Double> niceScoreHistory;
    @JsonIgnore
    public Double niceScore;
    public Double assignedBudget;
    public List<Gift> receivedGifts;

    public Child(Child child) {
        this.id = child.id;
        this.age = child.age;
        this.niceScore = child.niceScore;
        this.firstName = child.firstName;
        this.lastName = child.lastName;
        this.city = child.city;
        this.giftsPreferences = child.giftsPreferences;
        this.receivedGifts = child.receivedGifts;
        this.averageScore = child.averageScore;
        this.assignedBudget = child.assignedBudget;
        this.niceScoreHistory = new ArrayList<>(child.niceScoreHistory);
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
        this.niceScoreHistory = new ArrayList<>();
        this.receivedGifts = new ArrayList<>();
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
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
                ", previousNiceScores=" + niceScoreHistory +
                ", receivedGifts=" + receivedGifts +
                '}';
    }

}
