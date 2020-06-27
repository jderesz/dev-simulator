package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Project> activeProjects = new ArrayList<Project>();

    public Game() {
        this.initClients();
        this.initProjects();
        this.showStartProjectView();
    }

    private void showMainView()
    {
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
        int option = 0;
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                option = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (option > 0 && option < 4) {
                option--;
                activeProjects.add(startProjects.get(option));
                projects.remove(startProjects.get(option));
                break;
            }
        }
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
}
