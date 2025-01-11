import java.util.Random;
public class Cat {

    String name;
    int age;
    int health;
    int mood;
    int hunger;
    boolean actionDoneToday;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.health = randomValue();
        this.mood = randomValue();
        this.hunger = randomValue();
        this.actionDoneToday = false;
    }

    public double getAverageLevel() {
        return (health + mood + hunger) / 3.0;
    }

    private static int randomValue() {
        return new Random().nextInt(61) + 20;
    }

    private int[] getAgeDependentSteps() {
        if (age >= 1 && age <= 5) {
            return new int[] { 7, 3 };
        } else if (age >= 6 && age <= 10) {
            return new int[] { 5, 5 };
        } else {
            return new int[] { 4, 6 };
        }
    }

    public void feed() {
        if (actionDoneToday) {
            System.out.println(name + " уже был накормлен сегодня.");
            return;
        }

        int[] steps = getAgeDependentSteps();
        int increaseStep = steps[0];
        int decreaseStep = steps[1];

        if (new Random().nextInt(100) < 10) {
            health = Math.max(health - decreaseStep * 2, 0);
            mood = Math.max(mood - decreaseStep * 2, 0);
            System.out.println(name + " отравился после кормления!");
        } else {
            hunger = Math.min(hunger + increaseStep, 100);
            mood = Math.min(mood + decreaseStep, 100);
            System.out.println(name + " был накормлен.");
        }
        actionDoneToday = true;
    }

    public void play() {
        if (actionDoneToday) {
            System.out.println(name + " уже играл сегодня.");
            return;
        }

        int[] steps = getAgeDependentSteps();
        int increaseStep = steps[0];
        int decreaseStep = steps[1];

        if (new Random().nextInt(100) < 10) {
            health = Math.max(health - decreaseStep * 2, 0);
            mood = Math.max(mood - decreaseStep * 2, 0);
            hunger = Math.max(hunger - decreaseStep, 0);
            System.out.println(name + " получил травму во время игры!");
        } else {
            mood = Math.min(mood + increaseStep, 100);
            health = Math.min(health + increaseStep, 100);
            hunger = Math.max(hunger - decreaseStep, 0);
            System.out.println(name + " поиграл.");
        }
        actionDoneToday = true;
    }

    public void heal() {
        if (actionDoneToday) {
            System.out.println(name + " уже лечился сегодня.");
            return;
        }
        health = Math.min(health + 15, 100);
        mood = Math.max(mood - 10, 0);
        hunger = Math.max(hunger - 5, 0);
        actionDoneToday = true;
        System.out.println(name + " был вылечен.");
    }
    public void resetDailyAction() {
        actionDoneToday = false;
    }

    @Override
    public String toString() {
        return String.format("%s | %d | %d | %d | %d | %.2f", name, age, health, mood, hunger, getAverageLevel());
    }
}