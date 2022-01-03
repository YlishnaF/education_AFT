import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    static String string = null;

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("shares.json");


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Companies object = gson.fromJson(fileReader, Companies.class);
        System.out.println(object.getCompanies().size());
        List<Company> companies = object.getCompanies();
        printNameAndFoundDate(companies);
        currencyOutOfDate(companies);
        companiesFoundedAfter(companies, localDateCreator("23/12/92"));
        useCurrency(companies, "EU");

        fileReader.close();

    }

    public static void getStr(String s) {
        Main.string = s;
    }

    public static void printNameAndFoundDate(List<Company> companies) {
        companies.forEach(c -> System.out.println("Название компании: " + c.getName() + "; Дата основание: " + c.getFounded()));
    }

    public static void currencyOutOfDate(List<Company> companies) {
        long x = 0;
        for (Company company : companies) {
            System.out.println("Просроченные акции для компании " + company.getName() + ":");
            company.getSecurities().stream().filter(s -> s.getDate().isBefore(LocalDate.now())).forEach(d -> System.out.println("Название акции: " + d.getName() + ", код: " + d.getCode() + " , дата истечения: " + d.getDate()));
            x += company.getSecurities().stream().filter(s -> s.getDate().isBefore(LocalDate.now())).count();
            System.out.println("******");
        }
        System.out.println("Обшее количество просроченных акций: " + x);

    }

    public static void companiesFoundedAfter(List<Company> companies, LocalDate localDate) {
        if (localDate == null) {
            System.out.println("Некорректнаяя дата");
            return;
        }
        System.out.println("После " + localDate + " образовались компании: ");
        companies.stream().filter(c -> c.getFounded().isAfter(localDate)).forEach(c -> System.out.println(c.getName()));
    }

    public static void useCurrency(List<Company> companies, String s) {
        System.out.printf("Валюту %s используют: ", s);
        System.out.println();
 //       AtomicInteger id = new AtomicInteger();

        companies.stream().peek(company -> System.out.println("id " + company.getId()))
                .map(Company::getSecurities)
                .flatMap(Collection::stream)
                .peek(securities -> getStr(securities.getCode()))
                .map(Securities::getCurrency).flatMap(Arrays::stream).filter(c -> c.toString().equals(s)).forEach(cur -> System.out.println("Code " + Main.string));


//        for (Company company : companies) {
//            System.out.println("id: " + company.getId());
//            for (Securities security : company.getSecurities()) {
//                for (Currency currency : security.getCurrency()) {
//                    if (currency.toString().equals(s)) {
//                        System.out.println("code: " + security.getCode());
//                    }
//                }
//            }
//        }
    }

    public static LocalDate localDateCreator(String s) {
        DateTimeFormatter dateTimeFormatter;
        LocalDate localDate = null;
        if (s.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            localDate = LocalDate.parse(s, dateTimeFormatter);
        } else if (s.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            localDate = LocalDate.parse(s, dateTimeFormatter);
        } else if (s.matches("\\d{2}[/]\\d{2}[/]\\d{2}")) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            localDate = LocalDate.parse(s, dateTimeFormatter).minusYears(100);
        } else {
            System.out.println("Дата некорректна!");
        }
        return localDate;
    }
}
