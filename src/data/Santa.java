package data;

import Interface.SantaVisitorInterface;
import common.Constants;

import java.util.ArrayList;
import java.util.List;

public class Santa implements SantaVisitorInterface {
    Double santaBudget;
    List<Gift> giftsList;
    List<Double> averageScores;
    Double budgetUnit;

    public Santa(Double santaBudget, List<Gift> giftsList) {
        this.santaBudget = santaBudget;
        this.giftsList = giftsList;
        this.averageScores = new ArrayList<>(Database.getInstance().getChildren().size());
        for (int i = 0; i < Database.getInstance().getChildren().size(); i++){
            this.averageScores.add(0.0);
        }
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Gift> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(List<Gift> giftsList) {
        this.giftsList = giftsList;
    }

    public void updateBudgetUnit () {
        double sum = 0;
        for (Double averageScore : averageScores) {
            sum += averageScore;
        }
        budgetUnit = santaBudget / sum;
    }
    public void giveGifts() {
        List<Child> children = Database.getInstance().getChildren();
        updateBudgetUnit();
        for (Child child : children) {
            double budgetChild = budgetUnit * averageScores
                    .get(children.indexOf(child));
            double currBudget = 0.0;
            for (String giftPreferences : child.giftsPreferences) {
                for (Gift gift : giftsList) {
                    if(gift.category.equalsIgnoreCase(giftPreferences)){
                        if (Double.compare(gift.price + currBudget, budgetChild) < 0){
                            child.receivedGifts.add(gift);
                            currBudget += gift.price;
                            if (currBudget > budgetChild) {
                                break;
                            }
                        }
                    }
                }
                if (currBudget > budgetChild) {
                    break;
                }
            }
        }
    }
    @Override
    public void visit(Baby child) {
        averageScores.set(child.id, 10.0);
        child.age++;
    }

    @Override
    public void visit(Kid child) {
        if (child.previousNiceScores != null) {
            Double average = 0.0;
            for (Double score : child.previousNiceScores) {
                average += score;
            }
            average = average / child.previousNiceScores.size();
            averageScores.
                    set(child.id, average);
        }
        child.age++;
    }

    @Override
    public void visit(Teen child) {
        if (child.previousNiceScores != null) {
            double average = 0.0;
            double divider = 1.0;
            for (Double score : child.previousNiceScores) {
                average += score * ( child.previousNiceScores.indexOf(score) + 1);
                divider += child.previousNiceScores.indexOf(score);
            }
            average = average / divider;
            averageScores.
                    set(child.id, average);
        }
        child.age++;
    }
}
