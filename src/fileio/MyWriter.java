package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.Constants;
import data.Child;
import data.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class MyWriter {

    FileWriter myWriter;
    String filename;
    ObjectMapper mapper;
    JSONObject outputJSON;
    JSONArray annualChildrenJSON;

    public MyWriter(String filename) {
        try {

        this.myWriter = new FileWriter(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.filename = filename;
        this.mapper = new ObjectMapper();
        this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        this.outputJSON = new JSONObject();
        this.annualChildrenJSON = new JSONArray();
    }

//    public void write() {
//        File output = new File(Constants.OUTPUT_DIR);
//        boolean check;
//        if (output.isDirectory()) {
//            check = true;
//        } else {
//            check = output.mkdir();
//        }
//        if (check) {
//
//
//                for (Child child : Database.getInstance().getChildren()) {
//                    JSONArray childrenArray = new JSONArray();
//                    JSONObject children = new JSONObject();
//                    LinkedHashMap characteristics = new LinkedHashMap();
//
//                    characteristics.put(Constants.ID, child.id);
//                    characteristics.put(Constants.LAST_NAME, child.lastName);
//                    characteristics.put(Constants.FIRST_NAME, child.firstName);
//                    characteristics.put(Constants.CITY, child.city);
//                    characteristics.put(Constants.AGE, child.age);
//                    characteristics.put(Constants.GIFT_PREFERENCES, child.giftsPreferences);
//                    characteristics.put(Constants.AVERAGE_SCORE, averageScore);
//                    characteristics.put(Constants.NICE_SCORE_HISTORY, child.niceScoreHistory);
//                    characteristics.put(Constants.ASSIGNED_BUDGET, child.assignedBudget);
//                    characteristics.put(Constants.RECEIVED_GIFTS, child.receivedGifts);
//
//
//                    childrenArray.add(characteristics);
//                    children.put("children", childrenArray);
//                    annualChildrenJSON.add(children);
//                }
//
//
//        }
//    }

    public void closeJSON(AnnualChildren annualChildren){
        try {
           //outputJSON.put("annualChildren", annualChildrenJSON);
            //myWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputJSON));
            this.mapper.writeValue(new File(filename), annualChildren);
            //myWriter.flush();
            //myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
