package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Child implements Comparable<Child> {
    private Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private List<String> giftsPreferences;
    @JsonIgnore
    private Double niceScore;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gift> receivedGifts;

    public Child(final Child child) {
        this.setId(child.getId());
        this.setAge(child.getAge());
        this.setNiceScore(child.getNiceScore());
        this.setFirstName(child.getFirstName());
        this.setLastName(child.getLastName());
        this.setCity(child.getCity());
        this.setGiftsPreferences(new ArrayList<>(child.getGiftsPreferences()));
        this.setReceivedGifts(new ArrayList<>(child.getReceivedGifts()));
        this.setAverageScore(child.getAverageScore());
        this.setAssignedBudget(child.getAssignedBudget());
        this.setNiceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()));
    }

    public Child(final Integer id, final Integer age, final Double niceScore,
                 final String firstName, final String lastName,
                 final String city, final List<String> giftsPreferences) {
        this.setId(id);
        this.setAge(age);
        this.setNiceScore(niceScore);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCity(city);
        this.setGiftsPreferences(giftsPreferences);
        this.setNiceScoreHistory(new ArrayList<>());
        this.getNiceScoreHistory().add(niceScore);
        this.setReceivedGifts(new ArrayList<>());
    }

    /**
     * Getter for id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Setter for id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Getter for lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Setter for lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    /**
     * Getter for firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Setter for firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    /**
     * Getter for city
     */
    public String getCity() {
        return city;
    }
    /**
     * Setter for city
     */
    public void setCity(final String city) {
        this.city = city;
    }
    /**
     * Getter for age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * Setter for age
     */
    public void setAge(final Integer age) {
        this.age = age;
    }
    /**
     * Getter for giftsPreferences
     */
    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }
    /**
     * Setter for giftsPreferences
     */
    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
    /**
     * Getter for niceScore
     */
    public Double getNiceScore() {
        return niceScore;
    }
    /**
     * Setter for niceScore
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }
    /**
     * Getter for averageScore
     */
    public Double getAverageScore() {
        return averageScore;
    }
    /**
     * Setter for averageScore
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }
    /**
     * Getter for niceScoreHistory
     */
    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }
    /**
     * Setter for niceScoreHistory
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }
    /**
     * Getter for assignedBudget
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }
    /**
     * Setter for assignedBudget
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }
    /**
     * Getter for receivedGifts
     */
    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }
    /**
     * Setter for receivedGifts
     */
    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * compareTo method from Comparable interface in order to sort Child list
     * by id.
     */
    @Override
    public int compareTo(final Child o) {
        return getId() - o.getId();
    }


}
