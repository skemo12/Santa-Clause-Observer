package main;

import Utils.Utils;
import checker.Checker;
import common.Constants;
import data.Child;
import data.Database;
import fileio.AnnualChildren;
import fileio.ChildrenChanges;
import fileio.InputLoader;
import fileio.MyWriter;

import javax.xml.crypto.Data;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        InputLoader inputLoader = new InputLoader("tests/test1.json");
        inputLoader.readData();

        String filename = Constants.OUTPUT_PATH + 1 + Constants.JSON_EXTENSION;
        MyWriter writer = new MyWriter(filename);
        AnnualChildren annualChildren = new AnnualChildren();
        for (int i = 0; i<=Database.getInstance().getNumberOfYears(); i++) {
            System.out.println(Integer.toString(i));
            Database.getInstance().getSanta().giveGifts();
            ChildrenChanges childrenChanges = new ChildrenChanges(Database.getInstance().getChildren());
            annualChildren.addYear(childrenChanges);
            Utils.getInstance().increaseAge();
            if (i != Database.getInstance().getNumberOfYears()) {
                Utils.getInstance().updateBudget(i);
            }
        }

        writer.closeJSON(annualChildren);
        Checker.calculateScore();
    }
}
