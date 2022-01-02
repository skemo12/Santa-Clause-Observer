package input;

import common.Constants;
import data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * The method reads the database
     */
    public void readData() {
        JSONParser jsonParser = new JSONParser();
        List<Child> children = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            JSONObject initialData = (JSONObject) jsonObject
                    .get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) initialData
                    .get(Constants.CHILDREN);
            Double santaBudget = ((Long) ((JSONObject) jsonObject)
                    .get(Constants.SANTA_BUDGET)).doubleValue();
            Integer numberOfYears = ((Long) ((JSONObject) jsonObject)
                    .get(Constants.NUMBER_OF_YEARS)).intValue();
            JSONArray jsonSantaGiftsList = (JSONArray) initialData.
                    get(Constants.SANTA_GIFTS_LIST);
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject
                    .get(Constants.ANNUAL_CHANGES);

            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    JSONArray jsonGiftsPreferences = (JSONArray)
                            ((JSONObject) jsonChild)
                            .get(Constants.GIFT_PREFERENCES);
                    List<String> giftPreferences = new ArrayList<>();
                    for (Object gift : jsonGiftsPreferences) {
                        giftPreferences.add(((String) gift));
                    }
                    children.add(new Child(
                            ((Long) ((JSONObject) jsonChild).get(Constants.ID)).intValue(),
                            ((Long) ((JSONObject) jsonChild).get(Constants.AGE)).intValue(),
                            ((Long) ((JSONObject) jsonChild).get(Constants.NICE_SCORE)).doubleValue(),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            giftPreferences));
                }
            } else {
                System.out.println("NU EXISTA COPII");}

            List<Gift> giftList = new ArrayList<>();
            if (jsonSantaGiftsList != null) {
                for (Object jsonGift : jsonSantaGiftsList) {
                    Gift gift = new Gift(
                            (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                            ((Long) ((JSONObject) jsonGift).get(Constants.PRICE)).doubleValue(),
                            (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                    );
                    giftList.add(gift);
                }
            }

            List<AnnualChange> annualChanges = new ArrayList<>();
            if (jsonAnnualChanges != null) {
                Double newSantaBudget = santaBudget;
                for (Object jsonAnnualChange : jsonAnnualChanges) {

                    if ( ((JSONObject) jsonAnnualChange)
                            .get(Constants.SANTA_BUDGET) != null) {
                        newSantaBudget =
                                ((Long) ((JSONObject) jsonAnnualChange)
                                        .get(Constants.NEW_SANTA_BUDGET))
                                        .doubleValue();
                    }



                    List<Gift> newGifts = new ArrayList<>();
                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonAnnualChange)
                            .get(Constants.NEW_GIFTS);
                    for (Object jsonGift : jsonNewGifts) {
                        Gift gift = new Gift(
                                (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                                (Double) ((JSONObject) jsonGift).get(Constants.PRICE),
                                (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                        );
                        newGifts.add(gift);
                    }

                    List<Child> newChildren = new ArrayList<>();
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonAnnualChange)
                            .get(Constants.NEW_CHILDREN);
                    for (Object jsonNewChild : jsonNewChildren) {

                        JSONArray jsonGiftsPreferences = (JSONArray)
                                ((JSONObject) jsonNewChild)
                                        .get(Constants.GIFT_PREFERENCES);
                        List<String> giftPreferences = new ArrayList<>();
                        for (Object gift : jsonGiftsPreferences) {
                            giftPreferences
                                    .add((String) ((JSONObject) gift)
                                            .toString());
                        }

                        newChildren.add(new Child(
                                ((Long) ((JSONObject) jsonNewChild).get(Constants.ID)).intValue(),
                                (Integer) ((JSONObject) jsonNewChild).get(Constants.AGE),
                                (Double) ((JSONObject) jsonNewChild).get(Constants.NICE_SCORE),
                                (String) ((JSONObject) jsonNewChild).get(Constants.FIRST_NAME),
                                (String) ((JSONObject) jsonNewChild).get(Constants.LAST_NAME),
                                (String) ((JSONObject) jsonNewChild).get(Constants.CITY),
                                giftPreferences));
                    }

                    List<ChildUpdate> childrenUpdates = new ArrayList<>();
                    JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonAnnualChange)
                            .get(Constants.CHILDREN_UPDATES);
                    for (Object jsonChildUpdate : jsonChildrenUpdates) {
                        Integer id = (Integer) ((JSONObject) jsonChildUpdate)
                                .get(Constants.ID);
                        Double niceScore = (Double) ((JSONObject) jsonChildUpdate)
                                .get(Constants.NICE_SCORE);
                        List<String> giftsPreferences = new ArrayList<>();
                        JSONArray jsonGiftsPreferences =  (JSONArray) ((JSONObject) jsonChildUpdate)
                                .get(Constants.GIFT_PREFERENCES);
                        for (Object jsonGiftPreferences : jsonGiftsPreferences) {
                            giftsPreferences
                                    .add((String) ((JSONObject)
                                            jsonGiftPreferences)
                                            .toString());
                        }
                        childrenUpdates.add(new ChildUpdate(id, niceScore,
                                giftsPreferences));
                    }
                    annualChanges.add(new AnnualChange(newSantaBudget, newGifts,
                            newChildren, childrenUpdates));
                }
            }
            Database.getInstance().setNumberOfYears(numberOfYears);
            Database.getInstance().setChildren(children);
            Database.getInstance().setAnnualChanges(annualChanges);
            Santa santa = new Santa(santaBudget, giftList);
            Database.getInstance().setSanta(santa);


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}