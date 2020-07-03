package com.company;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private Double dailyPayment;

    public boolean frontendSkill = false;
    public boolean backendSkill = false;
    public boolean databaseSkill = false;
    public boolean mobileSkill = false;
    public boolean wordpressSkill = false;
    public boolean prestashopSkill = false;

    public Employee(String name, Double dailyPayment) {
        this.name = name;
        this.dailyPayment = dailyPayment;
    }

    public Double getDailyPayment() {
        return dailyPayment;
    }

    @Override
    public String toString() {
        List<String> skills = new ArrayList<String>();
        if (this.frontendSkill) skills.add("frontend");
        if (this.backendSkill) skills.add("backend");
        if (this.databaseSkill) skills.add("database");
        if (this.mobileSkill) skills.add("mobile");
        if (this.wordpressSkill) skills.add("wordpress");
        if (this.prestashopSkill) skills.add("prestashop");
        return "Nazwa: " + this.name +
                "\nDzienna wypłata: " + this.dailyPayment +
                "\nUmiejętności: " + String.join(", ", skills);
    }
}
