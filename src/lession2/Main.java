package lession2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        Map<String, Integer> map = createMapWithWords(bufferedReader);

        bufferedReader.close();

        sortAndPrintWords(map);
//        printInformationAboutWords(map);
//        maxAmountOfWordsInFile(map);
    }

    public static Map<String, Integer> createMapWithWords(BufferedReader bufferedReader) throws IOException {
        Map<String, Integer> map = new HashMap<>();

        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.replaceAll("\\p{Punct}", "").replaceAll("\\t", "").toLowerCase();

            String[] mas = line.split(" ");
            for (String s : mas) {
                if (s.equals("")) {
                    continue;
                }
                if (map.containsKey(s)) {
                    map.replace(s, map.get(s) + 1);
                } else {
                    map.put(s, 1);
                }
            }
        }
        return map;
    }

    public static void sortAndPrintWords(Map<String, Integer> map) {
        System.out.println("Отсортированный список слов:");
        map.keySet().stream().sorted().forEach(System.out::println);
    }

    public static void printInformationAboutWords(Map<String, Integer> map) {
        System.out.println("Слова и количество повторений:");
        for (Map.Entry entry : map.entrySet()) {

            System.out.println("Число повторений у слова: " + entry.getKey() + " - " + entry.getValue());
        }

    }

    public static void maxAmountOfWordsInFile(Map<String, Integer> map) {
        int maxValue = Collections.max(map.values());
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(maxValue)) {
                System.out.println("Максимальное число повторений у слова: " + entry.getKey() + ", появляется в количестве: " + maxValue);
            }
        }
    }
}
