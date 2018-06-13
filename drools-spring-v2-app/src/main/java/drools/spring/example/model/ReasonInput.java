package drools.spring.example.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReasonInput {

    private List<Symptom> symptoms;

    private List<Visit> previousVisits;

    public ReasonInput() {
        symptoms = new ArrayList<>();
        previousVisits = new ArrayList<>();
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<Visit> getPreviousVisits() {
        return previousVisits;
    }

    public void setPreviousVisits(List<Visit> previousVisits) {
        this.previousVisits = previousVisits;
    }

    public static boolean inLastSixMonths(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
        Date min = calendar.getTime();
        Date max = new Date();
        return date.after(min) && date.before(max);
    }

    public static boolean inLast21days(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 21);
        Date min = calendar.getTime();
        Date max = new Date();
        return date.after(min) && date.before(max);
    }

    public static boolean inLast14days(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 14);
        Date min = calendar.getTime();
        Date max = new Date();
        return date.after(min) && date.before(max);
    }
}
