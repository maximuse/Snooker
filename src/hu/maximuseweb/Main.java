package hu.maximuseweb;

public class Main {
    public static void main(String[] args) {
        Actions.fileToList("snooker.txt");
        System.out.println(Actions.task3());
        System.out.println(Actions.task4());
        System.out.println(Actions.task5("Kína"));
        System.out.println(Actions.task6());
        Actions.task7(5);
    }
}