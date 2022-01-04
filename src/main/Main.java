package main;

import utils.Utils;
import checker.Checker;
import common.Constants;
import data.Database;
import fileio.AnnualChildren;
import fileio.ChildrenChanges;
import fileio.InputLoader;
import fileio.MyWriter;

import java.io.File;

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
        String[] paths = file.list();

        if (paths == null) {
            System.out.println("No input data");
            return;
        }


        for (String path : paths) {

            // Read input file
            String inputFilename = Constants.INPUT_PATH + path;
            InputLoader inputLoader = new InputLoader(inputFilename);
            inputLoader.readData();

            // Prepare output file
            String index = path.replaceAll("[^0-9]", "");
            String outputFilename = Constants.OUTPUT_PATH + index + Constants.JSON_EXTENSION;
            MyWriter writer = new MyWriter(outputFilename);

            AnnualChildren annualChildren = new AnnualChildren();
            for (int i = 0; i <= Database.getInstance().getNumberOfYears(); i++) {
                Database.getInstance().getSanta().giveGifts();
                ChildrenChanges childrenChanges = new ChildrenChanges(Database
                        .getInstance().getChildren());
                annualChildren.addYear(childrenChanges);
                Utils.getInstance().increaseAge();
                if (i != Database.getInstance().getNumberOfYears()) {
                    Database.getInstance().updateDatabaseByYear(i);
                }

            }
            writer.closeJSON(annualChildren);
            Database.renewDatabase();
        }


        Checker.calculateScore();
    }
}
