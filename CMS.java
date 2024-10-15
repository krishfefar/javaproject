import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private int age;
    private String role;

    public Player(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}

class Team {
    private String name;
    private ArrayList<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        if (players.size() < 11) {
            players.add(player);
        } else {
            System.out.println("Team already has 11 players!");
        }
    }

    public void displayPlayers() {
        System.out.println("Team: " + name);
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public int getTeamStrength() {
        return players.size();
    }
}

class Match {
    private Team team1;
    private Team team2;
    private String date;

    public Match(Team team1, Team team2, String date) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }

    public void displayMatchDetails() {
        System.out.println("Match between " + team1.getName() + " and " + team2.getName() + " on " + date);
    }

    public void simulateMatch() {
        Random random = new Random();
        int team1Score = random.nextInt(201); // Random score between 0 and 200
        int team2Score = random.nextInt(201);
        System.out.println(team1.getName() + " scored: " + team1Score);
        System.out.println(team2.getName() + " scored: " + team2Score);

        if (team1Score > team2Score) {
            System.out.println(team1.getName() + " wins!");
        } else if (team2Score > team1Score) {
            System.out.println(team2.getName() + " wins!");
        } else {
            System.out.println("The match is a tie!");
        }
    }
}

public class CMS {
    private static ArrayList<Team> teams = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Cricket Management System ---");
            System.out.println("1. Create Team");
            System.out.println("2. Add Player to Team");
            System.out.println("3. Display Teams and Players");
            System.out.println("4. Schedule Match");
            System.out.println("5. Simulate Match");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    addPlayerToTeam();
                    break;
                case 3:
                    displayTeams();
                    break;
                case 4:
                    scheduleMatch();
                    break;
                case 5:
                    simulateMatch();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void createTeam() {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        Team team = new Team(teamName);
        teams.add(team);
        System.out.println("Team " + teamName + " created.");
    }

    private static void addPlayerToTeam() {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        Team team = findTeamByName(teamName);
        if (team == null) {
            System.out.println("Team not found!");
            return;
        }

        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        System.out.print("Enter player age: ");
        int playerAge = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter player role (Batsman/Bowler/All-rounder): ");
        String playerRole = scanner.nextLine();

        Player player = new Player(playerName, playerAge, playerRole);
        team.addPlayer(player);
        System.out.println("Player added to team " + teamName);
    }

    private static void displayTeams() {
        for (Team team : teams) {
            team.displayPlayers();
        }
    }

    private static void scheduleMatch() {
        System.out.print("Enter team 1 name: ");
        String team1Name = scanner.nextLine();
        Team team1 = findTeamByName(team1Name);

        System.out.print("Enter team 2 name: ");
        String team2Name = scanner.nextLine();
        Team team2 = findTeamByName(team2Name);

        if (team1 == null || team2 == null) {
            System.out.println("One or both teams not found!");
            return;
        }

        System.out.print("Enter match date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        Match match = new Match(team1, team2, date);
        match.displayMatchDetails();
    }

    private static void simulateMatch() {
        System.out.print("Enter team 1 name: ");
        String team1Name = scanner.nextLine();
        Team team1 = findTeamByName(team1Name);

        System.out.print("Enter team 2 name: ");
        String team2Name = scanner.nextLine();
        Team team2 = findTeamByName(team2Name);

        if (team1 == null || team2 == null) {
            System.out.println("One or both teams not found!");
            return;
        }

        System.out.print("Enter match date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        Match match = new Match(team1, team2, date);
        match.simulateMatch();
    }

    private static Team findTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }
}