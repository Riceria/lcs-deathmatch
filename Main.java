/**
 * Brendan Mercado & Peter Koo
 * Friday January 24, 2020
 * ICS 4U - Database ISU
 *
 * The following program simulates the LCS (League of Legends Championship Series).
 *
 * You can create/remove/list teams, players, and have them compete against each other.
 *
 * Teams must have 5 players. The game will not run with Teams that have less than 5 players.
 *
 * The actual "gameplay" is a simulation of events (only kills in this case) that occur in a normal League of Legends match.
 *
 * The team with the most kills at the end of the match is the winner.
 */

import java.util.*;

public class Main {

    //Global variables to be accessed by multiple methods.
    private static Team[] league = new Team[10]; //10 teams in real LCS
    private static Player[] gamers = new Player[50]; //5 players * 10 teams = 50 players
    private static int blueKills = 0, redKills = 0;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int menuChoice;
        boolean teamsSetup = false;
        int blueChoice = 0, redChoice = 0; //variables for deciding which 2 teams play each other

        //Real Esports teams that will be prepopulated
        league[0] = new Team("Team SoloMid", "TSM", "NA");
        league[1] = new Team("SK Telecom T1", "SKT", "KR");
        league[2] = new Team("FlyQuest", "FLY", "NA");
        league[3] = new Team("FunPlus Phoenix", "FPX", "NA");
        league[4] = new Team("G2 Esports", "G2", "EU");

        //Real players on TSM
        gamers[0] = new Player("Soren Bjerg", "Bjergsen", 23, 'M', "NA", 10, "Mid");
        gamers[1] = new Player("Sergen Celik", "Broken Blade", 20, 'M', "TR", 8.5, "Top");
        gamers[2] = new Player("Joshua Hartnett", "Dardoch", 21, 'M', "NA", 8.5, "Jungle");
        gamers[3] = new Player("Kasper Kobberup", "Kobbe", 23, 'M', "EU", 8.5, "ADC");
        gamers[4] = new Player("Vincent Wang", "Biofrost", 23, 'M', "NA", 9.5, "Support");
        prepopulateTeam(league[0], gamers[0], gamers[1], gamers[2], gamers[3], gamers[4]);

        //Real players on SKT
        gamers[5] = new Player("Lee Sang-hyeok", "Faker", 23, 'M', "KR", 10, "Mid");
        gamers[6] = new Player("Kim Dong-ha", "Khan", 24, 'M', "KR", 10, "Top");
        gamers[7] = new Player("Kim Tae-min", "Clid", 20, 'M', "KR", 10, "Jungle");
        gamers[8] = new Player("Park Jin-seong", "Teddy", 21, 'M', "KR", 10, "ADC");
        gamers[9] = new Player("Lee Sang-ho", "Effort", 19, 'M', "KR", 10, "Support");
        prepopulateTeam(league[1], gamers[5], gamers[6], gamers[7], gamers[8], gamers[9]);

        //Real players on FQ
        gamers[10] = new Player("Tristan Schrage", "PowerOfEvil", 22, 'M', "EU", 8, "Mid");
        gamers[11] = new Player("Omran Shoura", "V1per", 21, 'M', "NA", 8, "Top");
        gamers[12] = new Player("Lucas Tao Kilmer Larsen", "Santorin", 22, 'M', "NA", 8, "Jungle");
        gamers[13] = new Player("Jason Tran", "WildTurtle", 24, 'M', "NA", 9, "ADC");
        gamers[14] = new Player("Lee Dong-geun", "IgNar", 23, 'M', "KR", 8, "Support");
        prepopulateTeam(league[2], gamers[10], gamers[11], gamers[12], gamers[13], gamers[14]);

        //Real players on FPX
        gamers[15] = new Player("Kim Tae-sang", "Doinb", 23, 'M', "CN", 10, "Mid");
        gamers[16] = new Player("Kim Han-saem", "GimGoon", 24, 'M', "KR", 10, "Top");
        gamers[17] = new Player("Gao Tian-Liang", "Tian", 19, 'M', "CN", 10, "Jungle");
        gamers[18] = new Player("Lin Wei-Xiang", "Lwx", 21, 'M', "CN", 10, "ADC");
        gamers[19] = new Player("Liu Qing-Song", "Crisp", 21, 'M', "CN", 10, "Support");
        prepopulateTeam(league[3], gamers[15], gamers[16], gamers[17], gamers[18], gamers[19]);

        //Real Players on G2
        gamers[20] = new Player("Rasmus Winther", "Caps", 20, 'M', "EU", 9.5, "Mid");
        gamers[21] = new Player("Martin Hansen", "Wunder", 21, 'M', "EU", 8.5, "Top");
        gamers[22] = new Player("Marcin Jankowski", "Jankos", 24, 'M', "EU", 8.5, "Jungle");
        gamers[23] = new Player("Luka Perkovic", "Perkz", 21, 'M', "EU", 9.5, "ADC");
        gamers[24] = new Player("Mihael Mehle", "Mikyx", 21, 'M', "EU", 9, "Support");
        prepopulateTeam(league[4], gamers[20], gamers[21], gamers[22], gamers[23], gamers[24]);

        //gamers[48] = new Player("Brendan Mercado", "Iconix", 17, 'M', "NA", 10, "ADC");
        //gamers[49] = new Player("Peter Koo", "godkiller99", 17, 'M', "KR", 9.9, "Mid");

        System.out.println("\nCopyright © 2020 by Brendan Mercado & Peter Koo. All Rights Reserved. ");
        System.out.println("\nThrice & UI present to you...\n");
        System.out.println("  _____       ______   ______            ______   ________       _     _________  ____  ____  ____    ____       _     _________    ______  ____  ____  \n" +
                " |_   _|    .' ___  |.' ____ \\          |_   _ `.|_   __  |     / \\   |  _   _  ||_   ||   _||_   \\  /   _|     / \\   |  _   _  | .' ___  ||_   ||   _| \n" +
                "   | |     / .'   \\_|| (___ \\_|           | | `. \\ | |_ \\_|    / _ \\  |_/ | | \\_|  | |__| |    |   \\/   |      / _ \\  |_/ | | \\_|/ .'   \\_|  | |__| |   \n" +
                "   | |   _ | |        _.____`.            | |  | | |  _| _    / ___ \\     | |      |  __  |    | |\\  /| |     / ___ \\     | |    | |         |  __  |   \n" +
                "  _| |__/ |\\ `.___.'\\| \\____) |          _| |_.' /_| |__/ | _/ /   \\ \\_  _| |_    _| |  | |_  _| |_\\/_| |_  _/ /   \\ \\_  _| |_   \\ `.___.'\\ _| |  | |_  \n" +
                " |________| `.____ .' \\______.'         |______.'|________||____| |____||_____|  |____||____||_____||_____||____| |____||_____|   `.____ .'|____||____| \n" +
                "                                                                                                                                                           ");

        while (true)
        {


            System.out.println("\n======================================");
            System.out.println("|\t 1. Play                         |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 2. Create Team                  |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 3. Create/Manage Player         |"); // create/sign/release players
            System.out.println("--------------------------------------");
            System.out.println("|\t 4. Remove Team                  |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 5. Remove Player                |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 6. Team Info                    |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 7. Player Info                  |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 8. Trade Players                |");
            System.out.println("--------------------------------------");
            System.out.println("|\t 9. Sort Teams by Wins           |");
            System.out.println("--------------------------------------");
            System.out.println("|\t10. Exit                         |");
            System.out.println("======================================");

            System.out.print("\nEnter your choice: ");
            menuChoice = input.nextInt();

            if (menuChoice == 1)
            {
                int playChoice;
                System.out.println("======================");
                System.out.println("1. Start Match");
                System.out.println("2. Set/Change Teams");
                System.out.println("======================");

                System.out.println("\n*SETTING THE TEAMS AFTER EVERY MATCH IS HIGHLY RECOMMENDED*");
                System.out.print("\nEnter your choice: ");
                playChoice = input.nextInt();

                if (playChoice == 1)
                {
                    System.out.println("\n\t\t=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
                    System.out.println("\t\t|                                                                                                           |");
                    System.out.println("\t\t|   CAUTION: If you have removed a team/a team is not eligible to play, the current teams may be invalid.   |");
                    System.out.println("\t\t|                        Please choose teams that are eligible (full team).                                 |");
                    System.out.println("\t\t|                                                                                                           |");
                    System.out.println("\t\t=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");

                    //Only execute the game if both teams are a full team
                    if (league[blueChoice] != null && league[redChoice] != null)
                    {
                        if (!league[blueChoice].isFullTeam() && !league[redChoice].isFullTeam())
                        {
                            System.out.println("\nThe selected teams are no longer full teams.");
                            System.out.println("Please sign players to have them compete.");
                        }
                        else if (!league[blueChoice].isFullTeam())
                        {
                            System.out.println("\n" + league[blueChoice].getName() + " is no longer a full team (5 Players).");
                            System.out.println("Please sign players to have them compete.");
                        }
                        else if (!league[redChoice].isFullTeam())
                        {
                            System.out.println("\n" + league[redChoice].getName() + " is no longer a full team (5 Players).");
                            System.out.println("Please sign players to have them compete.");
                        }
                        else if (blueChoice != 0 || redChoice != 0) //even if league[0] is selected, the game will still run
                            gameStart(league[blueChoice], league[redChoice]);
                        else
                            System.out.println("\nPlease setup the teams first. Play > Set/Change Teams");
                    }
                    else
                        System.out.println("\nIf you have sorted the teams by ranking, You must reselect teams.");

                }
                else if (playChoice == 2)
                {
                    System.out.println("---------------------------------------------------------");
                    for (int i = 0; i < league.length; i++)
                    {
                        if (league[i] != null)
                            System.out.println(i+1 + ". " + league[i].getName() + " (" + league[i].getAcronym() + ")"
                                    + " (" + league[i].getSkillLevel() + ")" + " (" + league[i].isFullTeam() + ")");
                    }

                    if (teamsSetup)
                    {
                        if (!(blueChoice == 0 && redChoice == 0))
                        {
                            System.out.println("\n\t\t=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
                            System.out.println("\t\t|                                                                                                           |");
                            System.out.println("\t\t|   CAUTION: If you have removed a team/a team is not eligible to play, the current teams may be invalid.   |");
                            System.out.println("\t\t|                        Please choose teams that are eligible (full team).                                 |");
                            System.out.println("\t\t|                                                                                                           |");
                            System.out.println("\t\t=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");

                            System.out.println("\n---------------------------------------------------------");

                            if (league[blueChoice] != null)
                                System.out.println("Current Blue Team: " + league[blueChoice].getName());
                            else
                                System.out.println("Current Blue Team: [Needs to be reselected]");

                            if (league[redChoice] != null)
                                System.out.println("Current Red Team: " + league[redChoice].getName());
                            else
                                System.out.println("Current Red Team: [Needs to be reselected]");

                            System.out.println("---------------------------------------------------------");
                        }
                        else
                        {
                                System.out.println("\nNo teams have been setup.");
                                teamsSetup = false;
                        }

                    }

                    System.out.println("---------------------------------------------------------");
                    System.out.println("[Team Name] (Team Acronym) (Team Skill) (Eligible to play)");
                    System.out.println("\nChoose Blue Team:");
                    blueChoice = input.nextInt() - 1;

                    if (!(blueChoice < 0 || blueChoice >= league.length)) //This line and similar lines throughout the code to prevent ArrayOutOfBoundsException
                    {
                        if (league[blueChoice] != null) //This line and similar lines throughout the code to prevent NullPointerException
                        {
                            if (league[blueChoice].isFullTeam()) //Checks if a Team has 5 Players.
                            {
                                System.out.println("Blue Team - " + league[blueChoice].getName());
                                System.out.println("---------------------------------------------------------");
                                System.out.println("Choose Red Team:");
                                redChoice = input.nextInt() - 1;

                                if (!(redChoice < 0 || redChoice >= league.length))
                                {
                                    if (league[redChoice] != null)
                                    {
                                        if (league[redChoice].isFullTeam())
                                        {
                                            if (blueChoice == redChoice)
                                            {
                                                System.out.println("\nInvalid input: Both teams are the same.");
                                            }
                                            else
                                            {
                                                System.out.println("Red Team - " + league[redChoice].getName());
                                                System.out.println("---------------------------------------------------------");
                                                System.out.println("\nThe teams have been selected, please choose Play > Start Match.");
                                                teamsSetup = true;
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("\nError: " + league[redChoice].getName() + " is not a full team (5 Players).");
                                            System.out.println("Please sign players to have them compete.");
                                        }
                                    }
                                    else
                                        System.out.println("\nThat team does not exist.");

                                }
                                else
                                {
                                    System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");
                                    redChoice = -1;
                                }

                            }
                            else
                            {
                                if (league[blueChoice] != null)
                                {
                                    System.out.println("\nError: " + league[blueChoice].getName() + " is not a full team (5 Players).");
                                    System.out.println("Please sign players to have them compete.");
                                }
                                else
                                    System.out.println("\nThat team does not exist.");

                                }
                        }
                        else
                        {
                                System.out.println("\nThat team does not exist.");
                        }
                    }
                    else
                    {
                        System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");
                        blueChoice = -1;
                    }
                }
                else
                    System.out.println("Please enter a number from 1-2 next time.");
            }
            else if (menuChoice == 2)
            {
                int newTeam = 0;

                //Check for the first empty slot in the Team array.
                for (int i = 0; i < league.length; i++)
                {
                    if (league[i] == null)
                    {
                        newTeam = i;
                        break;
                    }
                }

                //Team creator
                if (newTeam < league.length && league[newTeam] == null)
                {
                    input.nextLine();
                    System.out.println("What is the name of your team?");
                    String newName = input.nextLine();

                    System.out.println("What is your team's acronym? (TSM, SKT, FQ, etc)");
                    String newNickname = input.nextLine();

                    System.out.println("What region is your team from? (NA, EU, KR, etc)");
                    String newRegion = input.nextLine();

                    league[newTeam] = new Team(newName, newNickname, newRegion);
                    System.out.println(league[newTeam].getName() + " (" + league[newTeam].getAcronym() + ")" +" has joined the league.");
                }
                else
                    System.out.println("\nThe max amount of teams has been reached. Please remove one first.");
            }
            else if (menuChoice == 3)
            {
                System.out.println("======================");
                System.out.println("1. Create Player");
                System.out.println("2. Sign Player");
                System.out.println("3. Release Player");
                System.out.println("======================");

                System.out.print("\nEnter your choice: ");
                int choice3 = input.nextInt();

                if (choice3 == 1)
                {
                    int newPlayer = 0;

                    //Check for the first empty slot in the Player array.
                    for (int i = 0; i < gamers.length; i++)
                    {
                        if (gamers[i] == null)
                        {
                            newPlayer = i;
                            break;
                        }
                    }

                    //Player creator
                    if (newPlayer < gamers.length && gamers[newPlayer] == null)
                    {
                        input.nextLine();
                        System.out.println("What is the real name of your player?");
                        String newName = input.nextLine();

                        System.out.println("What is your player's gamer tag?");
                        String gamerTag = input.nextLine();

                        System.out.println("How old is your player?");
                        int newAge = input.nextInt();

                        System.out.println("What gender is your player? (M/F)");
                        input.nextLine();
                        char newGender = input.next().charAt(0);

                        input.nextLine();
                        System.out.println("Where is your player from? (NA, EU, KR, etc)");
                        String newRegion = input.nextLine();

                        System.out.println("How good is your player from 1-10? (decimals accepted)");
                        double newSkill = input.nextDouble();
                        //Users can set a value greater than 10 to make their team overpowered.
                        //Negative numbers might affect the winRate calculations in the gameProgress() method.

                        if (newSkill >= 1)
                        {
                            System.out.println("What position does your player play? (Top, Jungle, Mid, ADC, Support)");
                            input.nextLine();
                            String newPosition = input.nextLine();

                            gamers[newPlayer] = new Player(newName, gamerTag, newAge, newGender, newRegion, newSkill, newPosition);
                            System.out.println(gamers[newPlayer].getName() + " (" + gamers[newPlayer].getGameName() + ") has joined the league.");
                        }
                        else
                            System.out.println("\nPlease enter a number from 1-10 next time.");
                    }
                    else
                        System.out.println("\nThe max amount of players has been reached. Please remove one first.");

                }
                else if (choice3 == 2)
                {
                    allPlayerDisplay();
                    System.out.println("\nPlease choose the player you want to sign: ");
                    int playerChoice = input.nextInt() - 1; //arrays start at 0

                    if (!(playerChoice < 0 || playerChoice >= gamers.length))
                    {
                        if (gamers[playerChoice] != null)
                        {
                            allTeamDisplay();
                            System.out.println("\nPlease choose which team to assign them to: ");
                            int teamChoice = input.nextInt() - 1;

                            if (!(teamChoice < 0 || teamChoice >= league.length))
                            {
                                //Checks if a Player is already on a Team or is trying to be traded to their current Team.
                                if (league[teamChoice] != null)
                                {
                                    if (league[teamChoice].getName().equals(gamers[playerChoice].getCurrentTeam()) && gamers[playerChoice].isPlayerSigned())
                                    {
                                        System.out.println(gamers[playerChoice].getGameName() + " is already on " + gamers[playerChoice].getCurrentTeam() + ".");
                                    }
                                    else if (gamers[playerChoice].isPlayerSigned())
                                    {
                                        System.out.println("\nSigning failed. Please release " + gamers[playerChoice].getGameName() + " from their contract first.");
                                    }
                                    else
                                    {
                                        league[teamChoice].addPlayer(gamers[playerChoice]);

                                        if (gamers[playerChoice].isPlayerSigned())
                                            System.out.println("\n" + gamers[playerChoice].getGameName() + " has joined " + gamers[playerChoice].getCurrentTeam() + ".");
                                    }
                                }
                                else
                                    System.out.println("\nThat team does not exist.");
                            }
                            else
                                System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");
                        }
                        else
                            System.out.println("\nThat player does not exist.");
                    }
                    else
                        System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");

                }
                else if (choice3 == 3)
                {
                    allPlayerDisplay();
                    System.out.println("\nPlease choose the player you want to release: ");
                    int playerChoice = input.nextInt() - 1;

                    //Release Player
                    if (!(playerChoice < 0 || playerChoice >= gamers.length))
                    {
                        if (gamers[playerChoice] != null)
                        {
                            if (gamers[playerChoice].isPlayerSigned())
                            {
                                System.out.println(gamers[playerChoice].getGameName() + " has been released from " + gamers[playerChoice].getCurrentTeam() + ".");
                                gamers[playerChoice].setPlayerSigned(false);
                                gamers[playerChoice].setCurrentTeam("Free Agent");
                                gamers[playerChoice].setTeamAcronym("N/A");

                            }
                            else
                                System.out.println(gamers[playerChoice].getGameName() + " is already a free agent.");

                            for (int i = 0; i < league[i].getPlayerArray().length - 1; i++)
                            {
                                if (gamers[playerChoice].getCurrentTeam().equals(league[i].getName()))
                                    league[i].removePlayer(gamers[playerChoice]);
                            }
                        }
                        else
                            System.out.println("\nThat player does not exist.");
                    }
                    else
                        System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");

                }
                else
                    System.out.println("Please enter a number from 1-3 next time.");
            }
            else if (menuChoice == 4)
            {
                allTeamDisplay();
                System.out.println("\nWhich team would you like to remove?");
                int choice4 = input.nextInt() - 1;

                //Remove Team
                if (!(choice4 < 0 || choice4 >= league.length))
                {
                    if (league[choice4] != null)
                    {
                        System.out.println("You have removed " + league[choice4].getName() + " from the league.");

                        for (int i = 0; i < league[choice4].getPlayerArray().length; i++)
                        {
                            if (league[choice4].getPlayerArray()[i] != null)
                            {
                                league[choice4].getPlayerArray()[i].setPlayerSigned(false);
                                league[choice4].getPlayerArray()[i].setCurrentTeam("Free Agent");
                                league[choice4].getPlayerArray()[i].setTeamAcronym("N/A");
                                league[choice4].getPlayerArray()[i] = null;
                            }
                        }

                        league[choice4] = null;

                        for (int i = choice4; i < league.length - 1; i++)
                        {
                            if (league[i] == null)
                            {
                                league[i] = league[i+1];
                                league[i+1] = null;
                            }
                        }
                    }
                    else
                        System.out.println("\nThat team does not exist.");
                }
                else
                    System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");

            }
            else if (menuChoice == 5)
            {
                allPlayerDisplay();
                System.out.println("\nWhich player would you like to remove?");
                int choice5 = input.nextInt() - 1;

                //Remove Player
                if (!(choice5 < 0 || choice5 >= gamers.length))
                {
                    for (int i = 0; i < league.length; i++)
                    {
                        if (league[i] != null)
                        {
                            for (int j = 0; j < league[i].getPlayerArray().length; j++) //almost certain a NullPointerException cannot happen due to protecting statements
                            {
                                if (league[j] != null)
                                {
                                    if (league[j].getPlayerArray()[j] != null)
                                    {
                                        league[j].removePlayer(gamers[choice5]);
                                    }
                                }
                            }
                        }
                    }

                    if (gamers[choice5] != null)
                    {
                        System.out.println("\nYou have banned " + gamers[choice5].getGameName() + " from competitive play.");
                        gamers[choice5].setPlayerSigned(false);
                        gamers[choice5] = null;

                        for (int i = choice5; i < gamers.length - 1; i++)
                        {
                            if (gamers[i] == null)
                            {
                                gamers[i] = gamers[i+1];
                                gamers[i+1] = null;
                            }
                        }
                    }
                    else
                        System.out.println("\nThat player does not exist.");
                }
                else
                    System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");

            }
            else if (menuChoice == 6)
            {
                System.out.println("===================================");
                System.out.println("1. List Teams");
                System.out.println("2. List All Players on a Team");
                System.out.println("3. View a Team's Info");
                System.out.println("===================================");

                System.out.print("\nEnter your choice: ");
                int choice6 = input.nextInt();

                if (choice6 == 1)
                {
                    allTeamDisplay();
                }
                else if (choice6 == 2)
                {
                    allTeamDisplay();
                    System.out.println("\nWhich team's players would you like to view?");
                    choice6 = input.nextInt() - 1;

                    if (!(choice6 < 0 || choice6 >= league.length))
                    {
                        if (league[choice6] != null)
                        {
                            System.out.println(league[choice6].listPlayers());
                        }
                        else
                            System.out.println("\nThat team does not exist.");
                    }
                    else
                        System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");
                }
                else if (choice6 == 3)
                {
                    allTeamDisplay();
                    System.out.println("\nWhich team's information would you like to view?");
                    choice6 = input.nextInt() - 1;

                    if (!(choice6 < 0 || choice6 >= league.length))
                    {
                        if (league[choice6] != null)
                        {
                            league[choice6].getUpdatedSkillLevel(league[choice6].getPlayerArray());
                            System.out.println(league[choice6].toString());
                        }
                        else
                            System.out.println("\nThat team does not exist.");
                    }
                    else
                        System.out.println("\nError: Only " + league.length + " teams are allowed to compete (1-" + league.length + ").");
                }
                else
                    System.out.println("\nPlease enter a number from 1-3 next time.");
            }
            else if (menuChoice == 7)
            {
                System.out.println("============================");
                System.out.println("1. List Players");
                System.out.println("2. View a Player's Info");
                System.out.println("============================");

                System.out.print("\nEnter your choice: ");
                int choice7 = input.nextInt();

                if (choice7 == 1)
                {
                    allPlayerDisplay();
                }
                else if (choice7 == 2)
                {
                    allPlayerDisplay();
                    System.out.println("\nWhich player's information would you like to view?");
                    choice7 = input.nextInt() - 1;

                    if (!(choice7 < 0 || choice7 >= gamers.length))
                    {
                        if (gamers[choice7] != null)
                        {
                            System.out.println(gamers[choice7].toString());
                        }
                        else
                            System.out.println("\nThat player does not exist.");
                    }
                    else
                        System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");
                }
                else
                    System.out.println("Please enter a number from 1-2 next time.");
            }
            else if (menuChoice == 8)
            {
                int trade1, trade2;

                allPlayerDisplay();
                System.out.println("\nPlease enter the first person to be traded: ");
                trade1 = input.nextInt() - 1;

                if (!(trade1 < 0 || trade1 >= gamers.length))
                {
                    if (gamers[trade1] != null)
                    {
                        System.out.println("Please enter the person they will be traded with: ");
                        trade2 = input.nextInt() - 1;

                        if (!(trade2 < 0 || trade2 >= gamers.length))
                        {
                            if (gamers[trade2] != null)
                            {
                                if (gamers[trade1].isPlayerSigned() && gamers[trade2].isPlayerSigned())
                                {
                                    if (!(gamers[trade1].getCurrentTeam().equalsIgnoreCase(gamers[trade2].getCurrentTeam())))
                                    {
                                        String team1Name = gamers[trade1].getCurrentTeam(); //save the name of their currentTeam for use later
                                        String team2Name = gamers[trade2].getCurrentTeam();

                                        System.out.println("\n" + gamers[trade1].getGameName() + " has been traded to " + gamers[trade2].getCurrentTeam() + ".");
                                        System.out.println(gamers[trade2].getGameName() + " has been traded to " + gamers[trade1].getCurrentTeam() + ".");

                                        //Release both players from their respective playerArray[] located within their team.
                                        for (int i = 0; i < league.length; i++)
                                        {
                                            if (league[i] != null)
                                            {
                                                if (gamers[trade1].getCurrentTeam().equals(league[i].getName()))
                                                    league[i].removePlayer(gamers[trade1]);

                                                if (gamers[trade2].getCurrentTeam().equals(league[i].getName()))
                                                    league[i].removePlayer(gamers[trade2]);
                                            }
                                            else
                                                break;
                                        }

                                        //Add both players to their new Team
                                        for (int i = 0; i < league.length; i++)
                                        {
                                            if (league[i] != null)
                                            {
                                                if (team2Name.equals(league[i].getName()))
                                                    league[i].addPlayer(gamers[trade1]);

                                                if (team1Name.equals(league[i].getName()))
                                                    league[i].addPlayer(gamers[trade2]);
                                            }
                                            else
                                                break;
                                        }

                                    }
                                    else
                                        System.out.println("\nTrade Invalid: Both players are on the same team.");
                                }
                                else
                                    System.out.println("\nTrading is only available to players that are currently on a team.");
                            }
                            else
                                System.out.println("\nThat player does not exist.");
                        }
                        else
                            System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");
                    }
                    else
                        System.out.println("\nThat player does not exist.");
                }
                else
                    System.out.println("\nError: Only " + gamers.length + " players are allowed to compete (1-" + gamers.length + ").");
            }
            else if (menuChoice == 9)
            {
                //Sort Teams in descending order based on their amount of wins.
                for (int i = 0; i < league.length; i++)
                {
                    for (int j = i+1; j < league.length; j++)
                    {
                        if (league[i] != null && league[j] != null)
                        {
                            if (league[i].getWins() < league[j].getWins())
                            {
                                Team temp = league[i];
                                league[i] = league[j];
                                league[j] = temp;
                            }
                        }

                    }
                }

                teamRankings();
            }
            else if (menuChoice == 10)
            {
                System.out.println("\n\t==========================");
                System.out.println("\t| Thank you for playing! |");
                System.out.println("\t==========================");
                break;
            }
            else
                System.out.println("\nPlease enter a number from 1-10.");
        }

    }

    private static void allTeamDisplay() {

        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < league.length; i++)
        {
            if (league[i] != null)
                System.out.println(i+1 + ". " + league[i].getName() + " (" + league[i].getAcronym() + ")");
        }
    }

    private static void teamRankings() {

        //This also reorders the team list when selecting a team.
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < league.length; i++)
        {
            if (league[i] != null)
                System.out.println(i+1 + ". " + league[i].getName() + " - " + league[i].getRecord());
        }
    }

    private static void allPlayerDisplay() {

        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < gamers.length; i++)
        {
            if (gamers[i] != null)
                System.out.println(i+1 + ". " + gamers[i].getTeamAcronym() + " | " + gamers[i].getGameName() + " (" + gamers[i].getName() + ")");
        }
    }


    private static void prepopulateTeam(Team tempTeam, Player p1, Player p2, Player p3, Player p4, Player p5) {

        tempTeam.addPlayer(p1);
        tempTeam.addPlayer(p2);
        tempTeam.addPlayer(p3);
        tempTeam.addPlayer(p4);
        tempTeam.addPlayer(p5);
        tempTeam.setFullTeam(true);
    }

    private static void gameStart(Team blueSide, Team redSide) {

        Random len = new Random();
        blueKills = 0;
        redKills = 0;

        System.out.println("\n\n\n\t\t" + blueSide.getName() + " (" + blueSide.getAcronym() + ")" + " VS. " + redSide.getName() + " (" + redSide.getAcronym() + ")");
        System.out.println("\t================================================");
        System.out.println("           /\\                                                 /\\\n" +
                " _         )( ______________________   ______________________ )(         _\n" +
                "(_)///////(**)______________________> <______________________(**)\\\\\\\\\\\\\\(_)\n" +
                "           )(                                                 )(\n" +
                "           \\/                                                 \\/");
        System.out.println("\n\nFirst 5 minutes");

        /*
        Our game is a random time between 20-40 minutes (just like real League of Legends games)
        Every 5 "minutes", the score is updated; 1 minute in-game = 1 second real time.
         */
        for (int i = 1; i < len.nextInt(21) + 20; i++)
        {

            if (i <= 5)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==5)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n5 minutes to 10 minutes");
            }

            if (i > 5 && i <= 10)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==10)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n10 minutes to 15 minutes");
            }

            if (i > 10 && i <= 15)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==15)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n15 minutes to 20 minutes");
            }

            if (i > 15 && i <= 20)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==20)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n20 minutes to 25 minutes");
            }

            if (i > 20 && i <= 25)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==25)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n25 minutes to 30 minutes");
            }

            if (i > 25 && i <= 30)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }

            if (i==30)
            {
                System.out.println("Current Score: \t" + blueSide.getName() + " - " + blueKills + "\t" + redSide.getName() + " - " + redKills);
                System.out.println("\n\n30 minutes to 40 minutes");
            }

            if (i > 30 && i <= 40)
            {
                System.out.println("==========================================================");
                gameProgress(blueSide, redSide);
            }
        }

        //Whichever team has a higher amount of kills wins, and each Team's records gets updated.
        if (blueKills > redKills)
        {
            System.out.println("\nThe game has ended. " + blueSide.getName() + " wins!");
            blueSide.setWins(blueSide.getWins() + 1);
            redSide.setLosses(redSide.getLosses() + 1);
        }
        else if (redKills > blueKills)
        {
            System.out.println("\nThe game has ended. " + redSide.getName() + " wins!");
            redSide.setWins(redSide.getWins() + 1);
            blueSide.setLosses(blueSide.getLosses() + 1);
        }
        else
        {
            System.out.println("\nThe game has ended. Tie game.");
            blueSide.setTieGames(blueSide.getTieGames() + 1);
            redSide.setTieGames(redSide.getTieGames() + 1);
        }

        //Print the final score and team records
        System.out.println("---------------------------------------------------------");
        System.out.println("\nFinal Score:");
        System.out.println(blueSide.getName() + ": " + blueKills + "\t|\t" + redSide.getName() + ": " + redKills);
        System.out.println("             ___________\n" +
                "            '._==_==_=_.'\n" +
                "            .-\\:      /-.\n" +
                "           | (|:.     |) |\n" +
                "            '-|:.     |-'\n" +
                "              \\::.    /\n" +
                "               '::. .'\n" +
                "                 ) (\n" +
                "               _.' '._\n" +
                "              `\"\"\"\"\"\"\"`");
        System.out.println("\nTeam Records:");
        System.out.println(blueSide.getName() + ": " + blueSide.getRecord() + "\t|\t" + redSide.getName() + ": " + redSide.getRecord());
    }

    private static void gameProgress(Team blueSide, Team redSide) {

        /*
        Every "minute", each team has a certain chance to get a kill.
        The chance is determined by a formula:
        (Team skill) / (Total skill among both teams) * 100

        With numbers to make it easier. Lets say BlueTeam has a skill of 45 and RedTeam has skill level of 5.
        BlueTeam kill rate = 45 / (45 + 5) = 45/50 * 100 = 90%
        RedTeam kill rate = 5 / (45 + 5) = 5/50 * 100 = 10%

        This means that every "minute", BlueTeam has a 90% chance to get a kill.
                                        RedTeam has a 10& chance to get a kill.

        This shows that skill level of Teams directly influences the outcome of a game, and it's not just random.
        Teams with the exact same skill level will each have a 50% chance to get a kill.
         */

        Random gen = new Random();
        Random jen = new Random();
        int killRateBlue, killRateRed;
        killRateBlue = (int)((blueSide.getSkillLevel() / (blueSide.getSkillLevel() + redSide.getSkillLevel()) * 100));
        killRateRed = (int)((redSide.getSkillLevel() / (blueSide.getSkillLevel() + redSide.getSkillLevel()) * 100));

        int randomPlayer1 = 0, randomPlayer2 = 0;

        //Chooses a random player to kill another random player on the opposing team.
        //Makes the game feel more immersive rather than just having "Blue Team kills Red Team".
        if (killRateBlue >= gen.nextInt(100) + 1)
        {
            for (int j = 0; j < blueSide.getPlayerArray().length; j++)
            {
                randomPlayer1 = gen.nextInt(5);
            }

            System.out.println("\t" + blueSide.getPlayerArray()[randomPlayer1].getGameName() +  " (Blue) has slain " + redSide.getPlayerArray()[randomPlayer2].getGameName() + " (Red).");
            blueKills++;
        }
        if (killRateRed >= jen.nextInt(100) + 1)
        {
            for (int j = 0; j < redSide.getPlayerArray().length; j++)
            {
                randomPlayer2 = gen.nextInt(5);
            }

            System.out.println("\t" + redSide.getPlayerArray()[randomPlayer2].getGameName() +  " (Red) has slain " + blueSide.getPlayerArray()[randomPlayer1].getGameName() + " (Blue).");
            redKills++;
        }
        System.out.println("==========================================================");

        //Wait 1 second before the next iteration to make it feel like time is passing.
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
