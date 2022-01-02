package main;

import checker.Checker;
import common.Constants;
import input.InputLoader;

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
        InputLoader inputLoader = new InputLoader("tests/test1.json");
        inputLoader.readData();
        Checker.calculateScore();
    }
}
