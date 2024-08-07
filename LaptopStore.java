package Gb.Seminar1;

import java.util.*;

public class LaptopStore {

    // Весь код класса LaptopStore
    private Set<Laptop> laptops;
    private Random random;


    public LaptopStore() {
        laptops = new HashSet<>();
        random = new Random();
    }

    /**
     * Генерируем указанное количество ноутбуков и добавляем их в магазин.count Количество ноутбуков для генерации.
     */

    public void generateLaptops(int count) {
        String[] models = {"HP", "Lenovo", "Asus", "Huawei"};
        int[] ramOptions = {4, 8, 16, 32, 64};
        int[] hddOptions = {256, 512, 1024, 2048};
        String[] osOptions = {"Windows", "Linux"};
        String[] colorOptions = {"Black", "Silver", "White"};

        for (int i = 0; i < count; i++) {
            String model = models[random.nextInt(models.length)] + "-" + (i + 1);
            int ram = ramOptions[random.nextInt(ramOptions.length)];
            int hddMemory = hddOptions[random.nextInt(hddOptions.length)];
            String os = osOptions[random.nextInt(osOptions.length)];
            String color = colorOptions[random.nextInt(colorOptions.length)];

            laptops.add(new Laptop(model, ram, hddMemory, os, color));
        }

    }

    public void displayAllLaptops() {
        System.out.println("Все доступные ноутбуки:");
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }
        System.out.println("Всего ноутбуков: " + laptops.size());
    }

    /**
     * Фильтрация ноутбуков по критериям.
     */
    public void filterLaptops() {
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Вывести отфильтрованный список");

            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    filters.put("hddMemory", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    filters.put("os", scanner.next());
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    filters.put("color", scanner.next());
                    break;
            }
        }

        Set<Laptop> filteredLaptops = new HashSet<>(laptops);
        for (Laptop laptop : laptops) {
            if (filters.containsKey("ram") && laptop.getRam() < (int) filters.get("ram")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("hddMemory") && laptop.getHddMemory() < (int) filters.get("hddMemory")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) filters.get("os"))) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) filters.get("color"))) {
                filteredLaptops.remove(laptop);
            }
        }

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}
