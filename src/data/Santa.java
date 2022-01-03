package data;

import Interface.SantaVisitorInterface;
import Utils.Utils;
import common.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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
                if (Double.compare(currBudget, budgetChild) == 0) {
                    break;
                }
                List<Gift> giftsPerCategory = new ArrayList<>();
                for (Gift gift : giftsList) {
                    if(gift.category.equalsIgnoreCase(giftPreferences)){
                        if (Double.compare(gift.price + currBudget, budgetChild) < 0){
                            giftsPerCategory.add(gift);

                            if (Double.compare(currBudget, budgetChild) == 0) {
                                break;
                            }
                        }
                    }
                }
                if (!giftsPerCategory.isEmpty()) {
                    Collections.sort(giftsPerCategory);
                    child.receivedGifts.add(giftsPerCategory.get(0));
                    currBudget += giftsPerCategory.get(0).price;
                }
                if (Double.compare(currBudget, budgetChild) == 0) {
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
            if (child.age < Constants.BABY) {
                updatedChildren.add(new Baby(child));
            } else if (child.age < Constants.KID) {
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
            if (child.age < Constants.BABY) {
                ((Baby) child).accept(this);
            } else if (child.age < Constants.KID) {
                ((Kid) child).accept(this);
            } else if (child.age <= Constants.TEEN) {
                ((Teen) child).accept(this);
            } else {
                updatedChildListIterator.remove();
            }
        }
        for (Child updatedChild : updatedChildren) {
            int index = Utils.getInstance().getIndexOfChild(updatedChild);
            Database.getInstance().getChildren().set(index, updatedChild);
        }
    }
    @Override
    public void visit(Baby child) {
        child.averageScore = 10.0;
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
        }
    }

    @Override
    public void visit(Teen child) {
        if (child.niceScoreHistory != null) {
            double average = 0.0;
            double divider = 0.0;
            for (Double score : child.niceScoreHistory) {
                average += score * ( child.niceScoreHistory.indexOf(score) + 1);
                divider += child.niceScoreHistory.indexOf(score) + 1.0;
            }
            average = average / divider;
            child.averageScore = average;
        }
    }
}
