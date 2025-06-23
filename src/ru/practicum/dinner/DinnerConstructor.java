package ru.practicum.dinner;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class DinnerConstructor {
    private ArrayList<String> namesOfDish;
    private HashMap<String, ArrayList<String>> menuOfDishes;
    private Random random;

    DinnerConstructor(Random rnd) {
        menuOfDishes = new HashMap<>();
        random = rnd;
    }

    void addDishToMenu(String dishType, String dishName, boolean isTestData) {
        if (!isTestData) {
            if (checkTypeOfDish(dishType)) {
                namesOfDish = menuOfDishes.get(dishType);
                if (!checkNameOfDish(dishName)) {
                    namesOfDish.add(dishName);
                    System.out.println("Блюдо - " + dishName + " (тип " + dishType + ") - добавлено в Меню.");
                } else {
                    System.out.println("Блюдо - " + dishName + " (тип " + dishType + ") - уже есть в Меню.");
                }
            } else {
                namesOfDish = new ArrayList<>();
                namesOfDish.add(dishName);
                menuOfDishes.put(dishType, namesOfDish);
                System.out.println("Блюдо - " + dishName + " (тип " + dishType + ") - добавлено в Меню.");
            }
            System.out.println();
        } else { //part for generate test menu
            if (checkTypeOfDish(dishType)) {
                namesOfDish = menuOfDishes.get(dishType);
                namesOfDish.add(dishName);
            } else {
                namesOfDish = new ArrayList<>();
                namesOfDish.add(dishName);
                menuOfDishes.put(dishType, namesOfDish);
            }
        }
    }

    boolean checkTypeOfDish(String type) {
        return menuOfDishes.containsKey(type);
    }

    boolean checkNameOfDish(String name) {
        return namesOfDish.contains(name);
    }

    void generateCombo(int numberOfCombos, ArrayList<String> typesForCombo)  {
        ArrayList<String> namesTypeOfDish;
        ArrayList<String> comboMenu;

        for (int i = 1; i <= numberOfCombos; i++) {
            comboMenu = new ArrayList<>();
            for (String nextTypes : typesForCombo) {
                namesTypeOfDish = menuOfDishes.get(nextTypes);
                int borderForRandom = namesTypeOfDish.size();
                int randomItem = random.nextInt(borderForRandom);
                comboMenu.add(namesTypeOfDish.get(randomItem));
            }
            System.out.println("Комбо " + i);
            System.out.println(comboMenu);
        }
        System.out.println();
    }

    //for test
    void generateTestMenuOfDish() {
        addDishToMenu("НАПИТОК", "компот", true);
        addDishToMenu("НАПИТОК", "сок", true);
        addDishToMenu("НАПИТОК", "алкоголь легкий", true);
        addDishToMenu("ПЕРВОЕ", "гаспачо", true);
        addDishToMenu("ПЕРВОЕ", "окрошка на квасе", true);
        addDishToMenu("ПЕРВОЕ", "борщ", true);

        addDishToMenu("ВТОРОЕ", "гуляш вкусный", true);
        addDishToMenu("ВТОРОЕ", "омлет пышный", true);
        addDishToMenu("ВТОРОЕ", "каша манная", true);

        addDishToMenu("ДЕСЕРТ", "пирожное", true);
        addDishToMenu("ДЕСЕРТ", "мороженное", true);
        addDishToMenu("ДЕСЕРТ", "фрукты", true);
    }

    //for test and convenience
    void printAllMenu() {
        for (String typeDish : menuOfDishes.keySet()) {
            System.out.println("Тип блюда: " + typeDish);
            System.out.println("Названия блюд: " + menuOfDishes.get(typeDish));
        }
        System.out.println();
    }

    //for convenience
    void printTypesOfDishes() {
        System.out.println(menuOfDishes.keySet());
    }
}

