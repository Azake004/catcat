import java.util.*;
import java.util.stream.*;

public class CatSimulator {

    private static List<Cat> cats = new ArrayList<>();

    private static void addCat(String name, int age) {
        cats.add(new Cat(name, age));
        System.out.println("Новый кот " + name + " добавлен.");
    }

    private static List<Cat> getSortedCats() {
        return cats.stream()
                .sorted(Comparator.comparingDouble(Cat::getAverageLevel).reversed())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        addCat("Барсик", 2);
        addCat("Мурка", 3);

        while (true) {

            System.out.println("\n# | Имя | Возраст | Здоровье | Настроение | Сытость | Средний уровень");
            List<Cat> sortedCats = getSortedCats();
            for (int i = 0; i < sortedCats.size(); i++) {
                System.out.println((i + 1) + ". " + sortedCats.get(i));
            }

            System.out.println("\nВыберите действие:");
            System.out.println("1. Покормить");
            System.out.println("2. Поиграть");
            System.out.println("3. Лечить");
            System.out.println("4. Добавить нового кота");
            System.out.println("5. Следующий день");
            System.out.println("0. Выход");

            int action = -1;

            try {
                action = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                continue;
            }

            switch (action) {
                case 1:
                case 2:
                case 3:

                    int catNumber = -1;
                    try {
                        System.out.println("Выберите кота (по номеру):");
                        catNumber = Integer.parseInt(scanner.nextLine()) - 1;
                        if (catNumber < 0 || catNumber >= cats.size()) {
                            System.out.println("Некорректный номер кота.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный ввод. Попробуйте снова.");
                        continue;
                    }

                    Cat selectedCat = sortedCats.get(catNumber);
                    if (action == 1) {
                        selectedCat.feed();
                    } else if (action == 2) {
                        selectedCat.play();
                    } else if (action == 3) {
                        selectedCat.heal();
                    }
                    break;

                case 4:

                    System.out.println("Введите имя нового кота:");
                    String name = scanner.nextLine();
                    int age = -1;
                    while (true) {
                        try {
                            System.out.println("Введите возраст нового кота (от 1 до 18):");
                            age = Integer.parseInt(scanner.nextLine());
                            if (age < 1 || age > 18) {
                                System.out.println("Возраст должен быть в пределах от 1 до 18.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Некорректный ввод. Попробуйте снова.");
                        }
                    }
                    addCat(name, age);
                    break;

                case 5:

                    for (Cat cat : cats) {
                        cat.resetDailyAction();
                    }
                    System.out.println("Наступил следующий день.");
                    break;

                case 0:

                    System.out.println("Выход.");
                    return;

                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }
}