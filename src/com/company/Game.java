package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Project> availableProjects = new ArrayList<Project>();
    private ArrayList<Project> activeProjects = new ArrayList<Project>();
    private ArrayList<Project> finishedProjects = new ArrayList<Project>();
    private ArrayList<Employee> availableEmployees = new ArrayList<Employee>();
    private ArrayList<Employee> activeEmployees = new ArrayList<Employee>();
    private double cash = 500.0;
    private LocalDate date = LocalDate.parse("2020-01-01");
    private int daysOfSearchingClients = 0;
    private int billsDays = 0;

    public Game() {
        this.initClients();
        this.initProjects();
        this.initEmployees();
        this.showStartProjectView();
    }

    private void showMainView()
    {
        System.out.println("============================");
        System.out.println("Pieniądze: " + this.cash + " | Dzień: " + this.date.toString() + " (" + this.getDayOfWeek() + ")");
        System.out.println("1) Podpisz umowę na nowy projekt (dostępne: " + this.availableProjects.size() + ")");
        System.out.println("2) Szukaj klientów (" + this.daysOfSearchingClients + "/5)");
        System.out.println("3) Programuj");
        System.out.println("4) Testuj");
        System.out.println("5) Oddaj projekt");
        System.out.println("6) Zatrudnij pracownika");
        System.out.println("7) Zwolnij pracownika");
        System.out.println("8) Rozlicz się z urzędem (" + this.billsDays + "/2)");

        int option = this.getKey(1, 8);
        switch (option) {
            case 1:
                this.showAvailableProjectsView();
                break;
            case 2:
                this.showFindNewProjectView();
                break;
            case 3:
                this.showSelfProgrammingView();
                break;
            case 5:
                this.showFinishProjectView();
                break;
            case 6:
                this.showEmployeesView();
                break;
            case 7:
                this.showDismissEmployeesView();
                break;
            case 8:
                this.showOfficeHoursView();
                break;
        }
    }

    private void showDismissEmployeesView() {
        System.out.println("============================");
        if (this.activeEmployees.size() == 0 ) {
            System.out.println("Brak zatrudnionych pracowników");
            System.out.println("0) powrót");
            int option = this.getKey(0, 0);
            this.showMainView();
            return;
        }

        int index = 0;
        for (Employee employee : this.activeEmployees) {
            System.out.println(++index + ") " + employee);
            System.out.println("============================");
        }
        System.out.println("0) powrót");

        System.out.println("Podaj numer pracownika do zwolnienia: ");
        int option = getKey(0, this.activeEmployees.size());
        if (option != 0) {
            option--;
            this.availableEmployees.add(this.activeEmployees.get(option));
            this.activeEmployees.remove(this.activeEmployees.get(option));
        }
        this.showMainView();
    }

    private void showEmployeesView() {
        System.out.println("============================");
        if (this.availableEmployees.size() == 0 ) {
            System.out.println("Brak dostępnych pracowników");
            System.out.println("0) powrót");
            int option = this.getKey(0, 0);
            this.showMainView();
            return;
        }

        int index = 0;
        for (Employee employee : this.availableEmployees) {
            System.out.println(++index + ") " + employee);
            System.out.println("============================");
        }
        System.out.println("0) powrót");

        System.out.println("Podaj numer pracownika do zatrudnienia: ");
        int option = getKey(0, this.availableEmployees.size());
        if (option != 0) {
            option--;
            this.activeEmployees.add(this.availableEmployees.get(option));
            this.availableEmployees.remove(this.availableEmployees.get(option));
        }
        this.showMainView();
    }

    private void showOfficeHoursView() {
        if (this.billsDays < 2) {
            this.billsDays++;
            this.nextDay();
        }
        this.showMainView();
    }

    private void showFinishProjectView() {
        ArrayList<Project> projectToFinish = new ArrayList<Project>();
        int index = 0;
        for (Project project : this.activeProjects) {
            if (project.isFinished()) {
                projectToFinish.add(project);
            }
        }
        if (projectToFinish.size() == 0) {
            System.out.println("Brak dostępnych projektów");
        } else {
            System.out.println("Wybierz który projekt chcesz oddać:");
            for (Project project : projectToFinish) {
                System.out.println(++index + ") " + project.shortDescription());
            }
        }
        System.out.println("0) powrót");
        int option = this.getKey(0, projectToFinish.size());
        if (option > 0) {
            Project project = projectToFinish.get(--index);
            this.activeProjects.remove(project);
            this.finishedProjects.add(project);
            this.cash += project.reward;
        }
        this.showMainView();
    }

    private void showSelfProgrammingView() {
        System.out.println("============================");
        if (this.activeProjects.size() == 0 ) {
            System.out.println("Brak dostępnych projektów");
            System.out.println("0) powrót");
            int option = this.getKey(0, 0);
            this.showMainView();
            return;
        }

        int index = 0;
        for (Project project : this.activeProjects) {
            System.out.println(++index + ") " + project.shortDescription());
            System.out.println("============================");
        }
        System.out.println("0) powrót");

        System.out.println("Podaj numer projektu: ");
        int option = getKey(0, this.activeProjects.size());
        if (option != 0) {
            option--;
            this.activeProjects.get(option).selfProgramming();
            this.nextDay();
        }
        this.showMainView();
    }

    private void showFindNewProjectView() {
        this.daysOfSearchingClients++;
        if (this.daysOfSearchingClients == 5) {
            Random random = new Random();
            Project newProject = this.projects.get(random.nextInt(this.projects.size()));
            this.availableProjects.add(newProject);
            this.projects.remove(newProject);
            this.daysOfSearchingClients = 0;
        }
        this.nextDay();
        this.showMainView();
    }

    private void nextDay() {
        this.date = this.date.plusDays(1);
        if (this.date.getDayOfMonth() == 1) {
            if (this.billsDays < 2){
                System.out.println("ZUS Cię dojechał - uciekaj na Malediwy");
                System.exit(0);
            }
            this.billsDays = 0;
        }
        for (Project project : this.activeProjects) {
            project.dayilyAction();
        }
        if (this.date.getDayOfWeek().getValue() != 5 && this.date.getDayOfWeek().getValue() != 6) {
            for (Employee employee : this.activeEmployees) {
                this.cash -= employee.getDailyPayment();
            }
        }
        if (this.cash < 0) {
            System.out.println("Zbankrutowałeś!");
            System.exit(0);
        }
    }

    private void showAvailableProjectsView() {
        System.out.println("============================");
        if (this.availableProjects.size() == 0 ) {
            System.out.println("Brak dostępnych projektów");
            System.out.println("0) powrót");
            int option = this.getKey(0, 0);
            this.showMainView();
            return;
        }

        int index = 0;
        for (Project project : this.availableProjects) {
            System.out.println(++index + ") " + project);
            System.out.println("============================");
        }
        System.out.println("0) powrót");

        System.out.println("Podaj numer projektu: ");
        int option = getKey(0, this.availableProjects.size());
        if (option != 0) {
            option--;
            this.activeProjects.add(this.availableProjects.get(option));
            this.availableProjects.remove(this.availableProjects.get(option));
        }
        this.showMainView();
    }

    private void showStartProjectView()
    {
        System.out.println("Appstore - company simulator");
        System.out.println("Janusz Dereszewski");
        System.out.println("============================");

        ArrayList<Project> startProjects = new ArrayList<Project>();
        while (startProjects.size() < 3) {
            Random random = new Random();
            Project startProject = this.projects.get(random.nextInt(this.projects.size()));
            if (startProjects.contains(startProject)) {
                continue;
            }
            startProjects.add(startProject);
        }
        int index = 0;
        for (Project project : startProjects) {
            System.out.println(++index + ") " + project);
            System.out.println("============================");
        }

        System.out.println("Podaj numer projektu: ");
        int option = getKey(1, 3);
        option--;
        activeProjects.add(startProjects.get(option));
        projects.remove(startProjects.get(option));
        this.showMainView();
    }

    private void initClients() {
        clients.add(new Client("WSB", Client.Type.EASY));
        clients.add(new Client("ZUS", Client.Type.HARD));
        clients.add(new Client("Marcin Jemioła", Client.Type.MEDIUM));
        clients.add(new Client("Politechnika Gdańska", Client.Type.MEDIUM));
        clients.add(new Client("Sklep spożywczy Zosia", Client.Type.EASY));
        clients.add(new Client("Internal Global Bajerns", Client.Type.EASY));
        clients.add(new Client("Józek spod trójki", Client.Type.MEDIUM));
        clients.add(new Client("Medium Press", Client.Type.HARD));
        clients.add(new Client("Krzychu i spółka", Client.Type.EASY));
    }

    private Client getRandomClient()
    {
        Random random = new Random();
        return this.clients.get(random.nextInt(this.clients.size()));
    }

    private void initProjects() {
        Project project;

        project = new Project("Symulator firmy IT", this.getRandomClient(), 15, 500.0, 2000.0, 5, Project.Level.LOW);
        project.setBackendDays(15);
        projects.add(project);

        project = new Project("Sklep z zegarkami", this.getRandomClient(), 20, 700.0, 3000.0, 3, Project.Level.LOW);
        project.setPrestashopDays(20);
        projects.add(project);

        project = new Project("Baza danych kliniki weterynaryjnej", this.getRandomClient(), 5, 200.0, 1000.0, 4, Project.Level.LOW);
        project.setDatabaseDays(5);
        projects.add(project);

        project = new Project("Strona wordpress dla domu publicznego", this.getRandomClient(), 10, 500.0, 1800.0, 2, Project.Level.LOW);
        project.setWordpressDays(10);
        projects.add(project);

        project = new Project("Symulator farmy", this.getRandomClient(), 13, 1000.0, 2200.0, 7, Project.Level.LOW);
        project.setBackendDays(13);
        projects.add(project);

        project = new Project("Baza danych szkoły", this.getRandomClient(), 8, 400.0, 1600.0, 7, Project.Level.LOW);
        project.setDatabaseDays(8);
        projects.add(project);
    }

    private void initEmployees() {
        Employee employee;

        employee = new Employee("Adam Bober", 35.0);
        employee.backendSkill = true;
        employee.databaseSkill = true;
        this.availableEmployees.add(employee);

        employee = new Employee("Kamil Best", 150.0);
        employee.backendSkill = true;
        employee.databaseSkill = true;
        employee.mobileSkill = true;
        employee.frontendSkill = true;
        employee.wordpressSkill = true;
        employee.prestashopSkill = true;
        this.availableEmployees.add(employee);

        employee = new Employee("Martyna Kowalska", 50.0);
        employee.mobileSkill = true;
        employee.databaseSkill = true;
        this.availableEmployees.add(employee);

        employee = new Employee("Wojciech Lech", 75.0);
        employee.backendSkill = true;
        employee.databaseSkill = true;
        employee.wordpressSkill = true;
        employee.prestashopSkill = true;
        this.availableEmployees.add(employee);

        employee = new Employee("Mikołaj Otyłez", 90.0);
        employee.backendSkill = true;
        employee.databaseSkill = true;
        employee.frontendSkill = true;
        employee.wordpressSkill = true;
        employee.prestashopSkill = true;
        this.availableEmployees.add(employee);
    }

    public int getKey(int min, int max)
    {
        int option;
        String line;
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = br.readLine();
            } catch (IOException e) {
                continue;
            }
            try {
                option = Integer.parseInt(line);
            } catch(NumberFormatException e){
                continue;
            }

            if (option >= min && option <= max) {
                return option;
            }
        }
    }

    private String getDayOfWeek()
    {
        switch (this.date.getDayOfWeek().getValue()) {
            case 1:
                return "wtorek";
            case 2:
                return "środa";
            case 3:
                return "czwartek";
            case 4:
                return "piątek";
            case 5:
                return "sobota";
            case 6:
                return "niedziela";
            case 7:
                return "poniedziałek";
            default:
                return String.valueOf(this.date.getDayOfWeek().getValue());
        }
    }
}
