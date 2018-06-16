package drools.spring.example.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportInput {
    private List<Visit> visits;
    private List<Long> hronicni;
    private List<Long> zavisnici;

    public ReportInput() {
        visits = new ArrayList<>();
        hronicni = new ArrayList<>();
        zavisnici = new ArrayList<>();
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Long> getHronicni() {
        return hronicni;
    }

    public void setHronicni(List<Long> hronicni) {
        this.hronicni = hronicni;
    }

    public List<Long> getZavisnici() {
        return zavisnici;
    }

    public void setZavisnici(List<Long> zavisnici) {
        this.zavisnici = zavisnici;
    }

    public static boolean inLastTwoYears(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 2);
        Date min = calendar.getTime();
        Date max = new Date();
        return date.after(min) && date.before(max);
    }

}
