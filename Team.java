/**
 * Create a Team class that holds an array of Players. Each team has a win/loss/tie record.
 *
 * Each Team has get/set methods for its respective instance variables.
 *
 * Teams have a current salary cap and a skill level that is determined
 * by the Players currently inside the Player array.
 *
 * A Team's skill level compared to another Team's skill level directly affects their chances of winning a game.
 * i.e A skill level of 45 vs. A skill level of 5 (Obviously the Team with 45 skill would almost always win)
 *
 * Teams can add/remove/list Players from the Player array.
 *
 * Teams can output their info with a toString() method.
 */

import java.text.NumberFormat;

public class Team {

    private NumberFormat money;
    private String name;
    private String acronym;
    private String region;
    private String record;
    private int wins;
    private int losses;
    private int tieGames;
    private double currentCap;
    private double skillLevel;
    private Player[] playerArray;
    private boolean fullTeam;

    public Team()
    {
        name = "";
        region = "";
        record = wins + "-" + losses + "-" + tieGames;
        currentCap = 0.0;
        skillLevel = 0.0;
        playerArray = new Player[5];
        money = NumberFormat.getCurrencyInstance();
        fullTeam = false;
    }

    public Team(String nm, String nickname, String country)
    {
        name = nm;
        acronym = nickname;
        region = country;
        record = wins + "-" + losses + "-" + tieGames;
        currentCap = 0.0;
        skillLevel = 0.0;
        playerArray = new Player[5];
        money = NumberFormat.getCurrencyInstance();
        fullTeam = false;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getRegion() {
        return region;
    }

    public String getRecord() {
        return record;
    }

    public double getCurrentCap() {
        return currentCap;
    }

    public double getSkillLevel() {
        return skillLevel;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTieGames() {
        return tieGames;
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public boolean isFullTeam() {
        return fullTeam;
    }

    public String listPlayers() {

        String str = "";

        for (int i = 0; i < playerArray.length; i++)
        {
            if (playerArray[i] != null && !playerArray[i].isPlayerSigned())
                playerArray[i] = null;

            if (playerArray[i] != null && playerArray[i].isPlayerSigned())
                str += playerArray[i].toString();
        }

        return str;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setAcronym(String nickname) {
        acronym = nickname;
    }

    public void setRegion(String country) {
        record = country;
    }

    public void setRecord(String score) {
        record = score;
    }

    public void setCurrentCap(double totalSalary) {
        currentCap = totalSalary;
    }

    public void setSkillLevel(double skill) {
        skillLevel = skill;
    }

    public void getUpdatedSkillLevel(Player[] p1) {
        skillLevel = 0;
        double temp = 0;

        for (int i = 0; i < p1.length; i++)
        {
            if (p1[i] != null)
                temp += p1[i].getRating();
        }

        skillLevel = temp;
    }

    public void setWins(int victories) {
        wins = victories;
        record = wins + "-" + losses + "-" + tieGames;
    }

    public void setLosses(int defeats) {
        losses = defeats;
        record = wins + "-" + losses + "-" + tieGames;
    }

    public void setTieGames(int deuce) {
        tieGames = deuce;
        record = wins + "-" + losses + "-" + tieGames;
    }

    public void setFullTeam(boolean max) {
        fullTeam = max;
    }

    public void addPlayer(Player newPlayer) {

        //If the Team is full, do not sign the Player
        if (playerArray[0] != null && playerArray[1] != null && playerArray[2] != null && playerArray[3] != null && playerArray[4] != null)
        {
            System.out.println("Signing failed. " + name + " already has the max amount of players.");
        }
        else
        {
            for (int i = 0; i < playerArray.length; i++)
            {
                if (playerArray[i] == null)
                {
                    playerArray[i] = newPlayer;

                    if (playerArray[0] != null && playerArray[1] != null && playerArray[2] != null && playerArray[3] != null && playerArray[4] != null)
                        fullTeam = true;

                    newPlayer.setPlayerSigned(true);
                    newPlayer.setCurrentTeam(name);
                    newPlayer.setTeamAcronym(acronym);
                    skillLevel += newPlayer.getRating();
                    currentCap += newPlayer.getSalary();
                    break;
                }
            }
        }
    }

    public void removePlayer(Player oldPlayer) {

        for (int i = 0; i < playerArray.length - 1; i++)
        {

            if (playerArray[4] == oldPlayer && playerArray[4] != null) //cannot check for playerArray[4] with 'i' due to the condition
            {
                playerArray[4] = null;
                oldPlayer.setPlayerSigned(false);
                oldPlayer.setCurrentTeam("Free Agent");
                oldPlayer.setTeamAcronym("N/A");
                fullTeam = false;
            }

            //Bump up the Players after one is removed
            if (playerArray[i] == oldPlayer)
            {
                playerArray[i] = null;
                playerArray[i] = playerArray[i+1];
                playerArray[i+1] = null;
                oldPlayer.setPlayerSigned(false);
                oldPlayer.setCurrentTeam("Free Agent");
                oldPlayer.setTeamAcronym("N/A");
                fullTeam = false;
            }

            if (playerArray[i] == null)
            {
                playerArray[i] = playerArray[i+1];
                playerArray[i+1] = null;
                fullTeam = false;
            }

        }

    }

    public String toString()
    {
        String str;

        str = "=====================================" +
                "\nName: " + name +
                "\nRegion: " + region +
                "\nRecord: " + record +
                "\nSalary Cap: " + money.format(currentCap) +
                "\nSkill Level: " + skillLevel +
                "\nFull Team?: " + fullTeam +
                "\n=====================================";

        return str;
    }
}
