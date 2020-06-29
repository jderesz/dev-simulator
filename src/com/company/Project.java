package com.company;

public class Project {
    public enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    private final String name;
    private final Client client;
    public int daysToFinish;
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
            //kara za opoznienie
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
