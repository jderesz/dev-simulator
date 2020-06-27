package com.company;

public class Project {
    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public String name;
    public String client;
    public int daysToFinish;
    public double deadlinePenalty;
    public double reward;
    public int paymentDays;
    public Level complexityLevel;

    private int frontendHours = 0;
    private int backendHours = 0;
    private int databaseHours = 0;
    private int mobileHours = 0;
    private int wordpressHours = 0;
    private int prestashopHours = 0;

    public Project (String name, String client, int daysToFinish, double deadlinePenalty, double reward, int paymentDays, Level complexityLevel)
    {
        this.name = name;
        this.client = client;
        this.daysToFinish = daysToFinish;
        this.deadlinePenalty = deadlinePenalty;
        this.reward = reward;
        this.paymentDays = paymentDays;
        this.complexityLevel = complexityLevel;
    }


    public void setFrontendHours(int frontendHours) {
        this.frontendHours = frontendHours;
    }

    public void setBackendHours(int backendHours) {
        this.backendHours = backendHours;
    }

    public void setDatabaseHours(int databaseHours) {
        this.databaseHours = databaseHours;
    }

    public void setMobileHours(int mobileHours) {
        this.mobileHours = mobileHours;
    }

    public void setWordpressHours(int wordpressHours) {
        this.wordpressHours = wordpressHours;
    }

    public void setPrestashopHours(int prestashopHours) {
        this.prestashopHours = prestashopHours;
    }

    public int getFrontendHours() {
        return frontendHours;
    }

    public int getBackendHours() {
        return backendHours;
    }

    public int getDatabaseHours() {
        return databaseHours;
    }

    public int getMobileHours() {
        return mobileHours;
    }

    public int getWordpressHours() {
        return wordpressHours;
    }

    public int getPrestashopHours() {
        return prestashopHours;
    }
}
