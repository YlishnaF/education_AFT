import java.time.LocalDate;

public class Securities {
    private String name;
    private String[] currency;
    private String code;
    private LocalDate date;

    public Securities() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCurrency() {
        return currency;
    }

    public void setCurrency(String[] currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate  date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Securities{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                '}';
    }
}
