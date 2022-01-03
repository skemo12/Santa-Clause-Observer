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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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


        File file = new File(Constants.INPUT_PATH);
        List<String> paths = new ArrayList<>();
        paths = Arrays.asList(file.list());


        for (String path : paths) {
            String input = Constants.INPUT_PATH + path;
            String customIN = Constants.INPUT_PATH + "test14.json";
            InputLoader inputLoader = new InputLoader(input);
            inputLoader.readData();
            String index = path.replaceAll("[^0-9]", "");
            String outputFilename = Constants.OUTPUT_PATH + index + Constants.JSON_EXTENSION;
            String customOUT = Constants.OUTPUT_PATH + 14 + Constants.JSON_EXTENSION;
            MyWriter writer = new MyWriter(outputFilename);
            AnnualChildren annualChildren = new AnnualChildren();
            for (int i = 0; i<=Database.getInstance().getNumberOfYears(); i++) {
                Database.getInstance().getSanta().giveGifts();
                ChildrenChanges childrenChanges = new ChildrenChanges(Database.getInstance().getChildren());
                annualChildren.addYear(childrenChanges);
                Utils.getInstance().increaseAge();
                if (i != Database.getInstance().getNumberOfYears()) {
                    Utils.getInstance().updateBudget(i);
                    Database.getInstance().updateDatabaseByYear(i);
                }

            }
            writer.closeJSON(annualChildren);
            Database.renewDatabase();
        }


        Checker.calculateScore();
    }
}
