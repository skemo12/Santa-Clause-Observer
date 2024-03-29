package utils;

import child.Child;
import data.Database;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Utils {
    /**
     * Make it singleton
     */
    private static Utils utils = null;

    /**
     * Singleton function
     */
    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    /**
     * Returns the child who has the id from parameters.
     *
     * @param id wanted id to find
     */
    public Child getChildById(final Integer id) {
        for (Child child : Database.getInstance().getChildren()) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }
    /**
     * Returns the index of a child in list, searched by id, not Child object.
     * @param child child whose id we want to find the index of
     */
    public int getIndexOfChild(final Child child) {
        for (int i = 0; i < Database.getInstance().getChildren().size(); i++) {
            Child childCurrent = Database.getInstance().getChildren().get(i);
            if (Objects.equals(childCurrent.getId(), child.getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts string to Category ENUM.
     * @param category string to be converted
     */
    public Category stringToCategory(final String category) {
        return switch (category) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Converts string to Cities ENUM.
     * @param city string to be converted
     */
    public Cities stringToCity(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * Converts List to List Category ENUM.
     * @param list list to be converted
     */
    public List<Category> stringListToCategoryList(final List<String> list) {
        List<Category> categories = new ArrayList<>();
        for (String element : list) {
            categories.add(Utils.getInstance().stringToCategory(element));
        }
        return categories;
    }
}
