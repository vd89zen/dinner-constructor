package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;
    static Random rnd;

    public static void main(String[] args) {
        rnd = new Random();
        scanner = new Scanner(System.in);
        dc = new DinnerConstructor(rnd);

        dc.generateTestMenuOfDish(); //создаём тестовое меню

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "0": //Показать Меню (добавил пункт для удобства)
                    dc.printAllMenu();
                    break;
                case "1": //Добавить новое блюдо
                    addNewDish();
                    break;
                case "2": //Сгенерировать комбинации блюд
                    generateDishCombo();
                    break;
                case "3": //Выход
                    System.out.println("Программа завершена. Всего доброго.");
                    return;
                default:
                    System.out.println("Введена неизвестная команда!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("0 - Показать Меню"); //for test and convenience
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine().toUpperCase();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine().toLowerCase();

        dc.addDishToMenu(dishType, dishName, false);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        System.out.print("В наличии: "); //for convenience
        dc.printTypesOfDishes(); //for convenience

        String nextItem = scanner.nextLine().toUpperCase();
        ArrayList<String> typesForCombo = new ArrayList<>();

        while (!nextItem.isEmpty()) {
            if (dc.checkTypeOfDish(nextItem)) {
                typesForCombo.add(nextItem);
            } else {
                System.out.println("Тип блюда - " + nextItem + " - отсутствует. Введите один из тех что в наличии.");
            }
            nextItem = scanner.nextLine().toUpperCase();
        }

        if (!typesForCombo.isEmpty()) {
            dc.generateCombo(numberOfCombos, typesForCombo);
        }
    }
}
