package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import data.Child;
import data.Database;
import input.InputLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println(Database.getInstance().getAnnualChanges().get(0));
        for (Child child : Database.getInstance().getChildren()) {
            child.updateScoresAndAge();
        }
        Database.getInstance().getSanta().giveGifts();
        for (Child child : Database.getInstance().getChildren()) {
            System.out.println(child.receivedGifts);
        }
        File output = new File(Constants.OUTPUT_DIR);
        boolean check;
        if (output.isDirectory()) {
            check = true;
        } else {
             check = output.mkdir();
        }
        if (check) {
            System.out.println("Check");
            try {
                String file = Constants.OUTPUT_PATH + 1 + Constants.JSON_EXTENSION;
                FileWriter myWriter = new FileWriter(file);
                JSONObject outputJSON = new JSONObject();
                JSONArray annualChildrenJSON = new JSONArray();
                for (int i = 0; i <= Database.getInstance().getNumberOfYears(); i++){
                    for (Child child : Database.getInstance().getChildren()) {
                        JSONArray childrenArray = new JSONArray();
                        JSONObject children = new JSONObject();
                        JSONObject characteristics = new JSONObject();

                        characteristics.put(Constants.ID, child.id);
                        characteristics.put(Constants.AGE, child.age);

                        childrenArray.add(characteristics);
                        children.put("children", childrenArray);
                        annualChildrenJSON.add(children);
                    }
                }
                outputJSON.put("annualChildren", annualChildrenJSON);
                ObjectMapper mapper = new ObjectMapper();

                myWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputJSON));
                myWriter.flush();
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Checker.calculateScore();
    }
}
