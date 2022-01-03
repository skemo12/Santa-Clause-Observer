package data;

import Interface.SantaVisitorInterface;
import Utils.Utils;
import common.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Santa implements SantaVisitorInterface {
    Double santaBudget;
    List<Gift> giftsList;
    Double budgetUnit;

    public Santa(Double santaBudget, List<Gift> giftsList) {
        this.santaBudget = santaBudget;
        this.giftsList = giftsList;
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
        List<Child> children = Database.getInstance().getChildren();
        for (Child child : children) {
            sum += child.averageScore;
        }
        budgetUnit = santaBudget / sum;
    }
    public void giveGifts() {
        updateScoresAndAge();
        List<Child> children = Database.getInstance().getChildren();
        for (Child child : children) {
            child.receivedGifts.clear();
        }
        updateBudgetUnit();
        for (Child child : children) {
            double budgetChild = budgetUnit * child.averageScore;
            child.assignedBudget = budgetChild;
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

    public void updateScoresAndAge() {

        List<Child> updatedChildren = new ArrayList<>();
        ListIterator<Child> childListIterator = Database.getInstance().getChildren().listIterator();
        while (childListIterator.hasNext()){
            Child child = childListIterator.next();
            if (child.age <= Constants.BABY) {
                updatedChildren.add(new Baby(child));
            } else if (child.age <= Constants.KID) {
                updatedChildren.add(new Kid(child));
            } else if (child.age <= Constants.TEEN) {
                updatedChildren.add(new Teen(child));
            } else {
                childListIterator.remove();
            }
        }
        ListIterator<Child> updatedChildListIterator = updatedChildren.listIterator();
        while (updatedChildListIterator.hasNext()){
            Child child = updatedChildListIterator.next();
            if (child.age <= Constants.BABY) {
                ((Baby) child).accept(this);
            } else if (child.age <= Constants.KID) {
                ((Kid) child).accept(this);
            } else if (child.age <= Constants.TEEN) {
                ((Teen) child).accept(this);
            } else {
                updatedChildListIterator.remove();
            }
        }
        for (Child updatedChild : updatedChildren) {
            Child child = Utils.getInstance().getChildById(updatedChild.id);
            Database.getInstance().getChildren().set(updatedChild.id, updatedChild);
        }
    }
    @Override
    public void visit(Baby child) {
        child.averageScore = 10.0;
        child.niceScoreHistory.add(10.0);
    }

    @Override
    public void visit(Kid child) {
        if (child.niceScoreHistory != null) {
            Double average = 0.0;
            for (Double score : child.niceScoreHistory) {
                average += score;
            }
            average = average / child.niceScoreHistory.size();
            child.averageScore = average;
            child.niceScoreHistory.add(average);
        }
    }

    @Override
    public void visit(Teen child) {
        if (child.niceScoreHistory != null) {
            double average = 0.0;
            double divider = 1.0;
            for (Double score : child.niceScoreHistory) {
                average += score * ( child.niceScoreHistory.indexOf(score) + 1);
                divider += child.niceScoreHistory.indexOf(score);
            }
            average = average / divider;
            child.averageScore = average;
            child.niceScoreHistory.add(average);
        }
    }
}
