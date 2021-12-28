package lession2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get(args[0]);
        String file =null;

        if(path.isAbsolute()){
            file=path.toString();
        } else{
            file = path.toAbsolutePath().toString();
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Map<String, Integer> map = createMapWithWords(bufferedReader);

        bufferedReader.close();

        sortAndPrintWords(map);
        printInformationAboutWords(map);
        maxAmountOfWordsInFile(map);
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
        System.out.println("Максимальное число повторений у слов:");
        map.entrySet().stream().filter(m->m.getValue()==maxValue).forEach(m-> System.out.println(m.getKey()));
        System.out.println("появляются в количестве: " + maxValue);
    }
}
