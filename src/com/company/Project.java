package com.company;

import java.util.Random;

public class Project {
    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    private final String name;
    private final Client client;
    public int daysToFinish;
    private int days;
    public double deadlinePenalty;
    public double reward;
    public int paymentDays;
    public Level complexityLevel;

    private int frontendDays = 0;
    private int backendDays = 0;
    private int databaseDays = 0;
    private int mobileDays = 0;
    private int wordpressDays = 0;
    private int prestashopDays = 0;

    public Project (String name, Client client, int daysToFinish, double deadlinePenalty, double reward, int paymentDays, Level complexityLevel)
    {
        this.name = name;
        this.client = client;
        this.daysToFinish = daysToFinish;
        this.days = daysToFinish;
        this.deadlinePenalty = deadlinePenalty;
        this.reward = reward;
        this.paymentDays = paymentDays;
        this.complexityLevel = complexityLevel;
    }

    public void selfProgramming() {
        if (this.backendDays > 0) {
            this.backendDays--;
            return;
        }
        if (this.databaseDays > 0) {
            this.databaseDays--;
            return;
        }
        if (this.frontendDays > 0) {
            this.frontendDays--;
            return;
        }
        if (this.wordpressDays > 0) {
            this.wordpressDays--;
            return;
        }
        if (this.prestashopDays > 0) {
            this.prestashopDays--;
            return;
        }
    }

    public void dayilyAction() {
        this.daysToFinish--;
        if (this.daysToFinish == 0) {
            Random random = new Random();
            switch (this.client.getType()) {
                case EASY:
                    if (random.nextInt(9) > 1) {
                        this.daysToFinish = 6;
                    } else {
                        this.reward -= this.deadlinePenalty;
                        this.deadlinePenalty = 0;
                    }
                    break;
                case MEDIUM:
                case HARD:
                    this.reward -= this.deadlinePenalty;
                    this.deadlinePenalty = 0;
                    break;
            }
        }
    }

    public boolean isFinished() {
        return this.frontendDays <= 0 &&
                this.backendDays <= 0 &&
                this.databaseDays <= 0 &&
                this.mobileDays <= 0 &&
                this.wordpressDays <= 0 &&
                this.prestashopDays <= 0;
    }

    public String shortDescription() {
        return this.getName() + " (ukończono: " + this.getCompletePercent() + ")";
    }

    public String getCompletePercent() {
        int completed = this.frontendDays + this.backendDays + this.databaseDays + this.mobileDays + this.wordpressDays + this.prestashopDays;
        completed = this.days - completed;
        return completed + "/" + this.days;
    }

    @Override
    public String toString() {
        return "Nazwa: " + this.getName() +
                "\nKlient: " + this.client.getName() +
                "\nCzas na wykonanie: " + this.daysToFinish +
                "\nKara za opóźnienie: " + this.deadlinePenalty +
                "\nWynagrodzenie: " + this.reward +
                "\nDni na wypłacenie wynagrodzenia: " + this.paymentDays;
    }

    public String getName() {
        return name;
    }

    public void setFrontendDays(int frontendDays) {
        this.frontendDays = frontendDays;
    }

    public void setBackendDays(int backendDays) {
        this.backendDays = backendDays;
    }

    public void setDatabaseDays(int databaseDays) {
        this.databaseDays = databaseDays;
    }

    public void setMobileDays(int mobileDays) {
        this.mobileDays = mobileDays;
    }

    public void setWordpressDays(int wordpressDays) {
        this.wordpressDays = wordpressDays;
    }

    public void setPrestashopDays(int prestashopDays) {
        this.prestashopDays = prestashopDays;
    }

    public int getFrontendDays() {
        return frontendDays;
    }

    public int getBackendDays() {
        return backendDays;
    }

    public int getDatabaseDays() {
        return databaseDays;
    }

    public int getMobileDays() {
        return mobileDays;
    }

    public int getWordpressDays() {
        return wordpressDays;
    }

    public int getPrestashopDays() {
        return prestashopDays;
    }
}
