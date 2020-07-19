package hu.maximuseweb;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Actions {
    private static ArrayList<Player> list;

    private static ArrayList<Player> getList() {
        return list;
    }

    private static void setList(ArrayList<Player> list) {
        Actions.list = list;
    }

    static void fileToList(String fileName) {
        ArrayList<Player> list = new ArrayList<>();

        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            String row = raf.readLine();
            row = raf.readLine();
            String[] slice;

            while (row != null) {
                slice = new String(row.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).split(";");

                list.add(new Player(Integer.parseInt(slice[0]), slice[1], slice[2], Integer.parseInt(slice[3])));

                row = raf.readLine();
            }

            raf.close();

            setList(list);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static String task3() {
        return "3. feladat: A világranglistán " + Actions.getList().size() + " versenyző szerepel";
    }

    static String task4() {
        double avg = 0;

        for (int i = 0; i < Actions.getList().size(); i++) {
            avg += Actions.getList().get(i).getPrize();
        }

        avg = avg / Actions.getList().size();

        return "4. feladat: A versenyzők átlagosan " + String.format("%.2f", avg) + " fontot kerestek";
    }

    static String task5(String country) {
        String c = new String(country.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String result = "";
        int prize = 0;
        int index = 0;

        for (int i = 0; i < Actions.getList().size(); i++) {
            if (Actions.getList().get(i).getCountry().equals(c) && Actions.getList().get(i).getPrize() > prize) {
                prize = Actions.getList().get(i).getPrize();
                index = i;
            }
        }

        prize *= 380;

        result += "\n\tHelyezés: " + Actions.getList().get(index).getPlace() +
                "\n\tNév: " + Actions.getList().get(index).getName() +
                "\n\tOrszág: " + Actions.getList().get(index).getCountry() +
                "\n\tNyeremény összege: " + String.format("%,d", prize) + " Ft";

        return "5. feladat: A legjobban kereső " + country.toLowerCase() + "i versenyző:" + result;
    }

    static String task6() {
        String c = new String("Norvégia".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        boolean result = false;

        for (Player player : Actions.getList()) {
            if (player.getCountry().equals(c)) {
                result = true;
                break;
            }
        }

        return "6. feladat: A versenyzők között " + ((result) ? "van" : "nincs") + " norvég versenyző.";
    }

    static void task7(int min) {
        TreeSet<String> countries = new TreeSet<>();
        Map<String, Integer> stat = new HashMap<>();
        int counter;

        for (Player player : Actions.getList()) {
            countries.add(player.getCountry());
        }

        for (String country : countries) {
            counter = 0;

            for (Player player : Actions.getList()) {
                if (country.equals(player.getCountry())) {
                    counter++;
                }
            }

            stat.put(country, counter);
        }

        System.out.print("7. feladat: Statisztika");
        //stat.forEach((key, value) -> System.out.print((value > min) ? "\n\t" + key + " - " + value + " fő" : ""));
        stat.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEach((value) -> System.out.print((value.getValue() > min) ? "\n\t" + value.getKey() + " - " + value.getValue() + " fő" : ""));
    }
}