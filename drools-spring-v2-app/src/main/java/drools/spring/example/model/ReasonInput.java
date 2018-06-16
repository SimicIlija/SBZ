package drools.spring.example.model;

import drools.spring.example.model.dto.DiseaseDto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReasonInput {

    private List<Symptom> symptoms;

    private List<Visit> previousVisits;

    private Disease firstDisease;
    private Disease thirdDisease;
    private List<Disease> secondDiseases;

    private List<DiseaseDto> connectedDisease;

    private Number prehladaSym;

    private Number groznicaSym;

    private Number upalaKrSym;

    private Number sinfSym;

    private Number hipertenzijaSym;

    private Number dijabetesSym;

    private Number hbbSymGen;

    private Number hbbSymSpec;

    private Number abbSymGen;

    private Number abbSymSpec;

    public ReasonInput() {
        symptoms = new ArrayList<>();
        previousVisits = new ArrayList<>();
        secondDiseases = new ArrayList<>();
        connectedDisease = new ArrayList<>();
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

    public Disease getFirstDisease() {
        return firstDisease;
    }

    public void setFirstDisease(Disease firstDisease) {
        this.firstDisease = firstDisease;
    }

    public Disease getThirdDisease() {
        return thirdDisease;
    }

    public void setThirdDisease(Disease thirdDisease) {
        this.thirdDisease = thirdDisease;
    }

    public List<Disease> getSecondDiseases() {
        return secondDiseases;
    }

    public void setSecondDiseases(List<Disease> secondDiseases) {
        this.secondDiseases = secondDiseases;
    }

    public Number getPrehladaSym() {
        return prehladaSym;
    }

    public void setPrehladaSym(Number prehladaSym) {
        this.prehladaSym = prehladaSym;
    }

    public Number getGroznicaSym() {
        return groznicaSym;
    }

    public void setGroznicaSym(Number groznicaSym) {
        this.groznicaSym = groznicaSym;
    }

    public Number getUpalaKrSym() {
        return upalaKrSym;
    }

    public void setUpalaKrSym(Number upalaKrSym) {
        this.upalaKrSym = upalaKrSym;
    }

    public Number getSinfSym() {
        return sinfSym;
    }

    public void setSinfSym(Number sinfSym) {
        this.sinfSym = sinfSym;
    }

    public Number getHipertenzijaSym() {
        return hipertenzijaSym;
    }

    public void setHipertenzijaSym(Number hipertenzijaSym) {
        this.hipertenzijaSym = hipertenzijaSym;
    }

    public Number getDijabetesSym() {
        return dijabetesSym;
    }

    public void setDijabetesSym(Number dijabetesSym) {
        this.dijabetesSym = dijabetesSym;
    }

    public Number getHbbSymGen() {
        return hbbSymGen;
    }

    public void setHbbSymGen(Number hbbSymGen) {
        this.hbbSymGen = hbbSymGen;
    }

    public Number getAbbSymGen() {
        return abbSymGen;
    }

    public void setAbbSymGen(Number abbSymGen) {
        this.abbSymGen = abbSymGen;
    }

    public Number getHbbSymSpec() {
        return hbbSymSpec;
    }

    public void setHbbSymSpec(Number hbbSymSpec) {
        this.hbbSymSpec = hbbSymSpec;
    }

    public Number getAbbSymSpec() {
        return abbSymSpec;
    }

    public void setAbbSymSpec(Number abbSymSpec) {
        this.abbSymSpec = abbSymSpec;
    }

    public List<DiseaseDto> getConnectedDisease() {
        return connectedDisease;
    }

    public void setConnectedDisease(List<DiseaseDto> connectedDisease) {
        this.connectedDisease = connectedDisease;
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

    public void addNewDiseaseDto(String name, Number number, Number specific) {
        DiseaseDto diseaseDto = new DiseaseDto(name, number, specific);
        this.connectedDisease.add(diseaseDto);
    }

    public void addFirstDisease(String name) {
        Disease disease = new Disease();
        disease.setName(name);
        firstDisease = disease;
    }

    public void addSecondDisease(String name) {
        Disease disease = new Disease();
        disease.setName(name);
        secondDiseases.add(disease);
    }

    public void addThirdDisease(String name) {
        Disease disease = new Disease();
        disease.setName(name);
        thirdDisease = disease;
    }
}
