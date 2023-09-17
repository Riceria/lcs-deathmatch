/**
 * Create a Player class that can join and be released from Teams.
 *
 * Each Player has get/set methods for its respective instance variables.
 *
 * Players have a rating and a salary that is added to a
 * Team's skill level and currentCap respectively.
 *
 * A players salary is determined by their rating.
 *
 * Players can output their info with a toString() method.
 */

import java.text.NumberFormat;

public class Player {

    private String name;
    private String gameName;
    private int age;
    private double salary;
    private char gender;
    private String region;
    private boolean playerSigned;
    private double rating;
    private String currentTeam;
    private String teamAcronym;
    private String position;
    private NumberFormat money;


    public Player()
    {
        name = "";
        gameName = "";
        age = 0;
        salary = 0.0;
        gender = ' ';
        region = "";
        playerSigned = false;
        rating = 0.0;
        currentTeam = "";
        teamAcronym = "";
        position = "";
        money = NumberFormat.getCurrencyInstance();
    }

    public Player(String nm, String gamerTag, int years, char sex, String country, double skill, String role)
    {
        name = nm;
        gameName = gamerTag;
        age = years;
        salary = skill * 10000;
        gender = sex;
        region = country;
        playerSigned = false;
        rating = skill;
        currentTeam = "Free Agent";
        teamAcronym = "N/A";
        position = role;
        money = NumberFormat.getCurrencyInstance();
    }

    /*public Player(Player clone)
    {
        name = clone.getName();
        gameName = clone.getGameName();
        age = clone.getAge();
        salary = clone.getRating() * 10000;
        gender = clone.getGender();
        region = clone.getRegion();
        playerSigned = clone.isPlayerSigned();
        rating = clone.getRating();
        currentTeam = clone.getCurrentTeam();
        position = clone.getPosition();
        money = NumberFormat.getCurrencyInstance();
    }*/
    //Thought we would need a cloning constructor for trading, but it is unused.

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public char getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    public boolean isPlayerSigned() {
        return playerSigned;
    }

    public double getRating() {
        return rating;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public String getTeamAcronym() {
        return teamAcronym;
    }

    public String getPosition() {
        return position;
    }

    public String getGameName() {
        return gameName;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setAge(int years) {
        age = years;
    }

    public void setSalary(double money) {
        salary = money;
    }

    public void setGender(char sex) {
        gender = sex;
    }

    public void setRegion(String country) {
        region = country;
    }

    public void setPlayerSigned(boolean signed) {
        playerSigned = signed;
    }

    public void setRating(double skill) {
        rating = skill;
    }

    public void setCurrentTeam(String newTeam) {
        currentTeam = newTeam;
    }

    public void setTeamAcronym(String teamNickname) {
        teamAcronym = teamNickname;
    }

    public void setPosition(String role) {
        position = role;
    }

    public void setGameName(String gamerTag) {
        gameName = gamerTag;
    }

    public String toString()
    {
        String str;

        str = "\n-------------------------------------" +
                "\nName: " + name +
                "\nIGN: " + gameName +
                "\nAge: " + age +
                "\nSex: " + gender +
                "\nSalary: " + money.format(salary) +
                "\nRegion: " + region  +
                "\nRating: " + rating +
                "\nPosition: " + position;

        if (!playerSigned)
        {
            str += "\n[This player is a free agent]";
        }
        else
            str += "\nTeam: " + currentTeam + " (" + teamAcronym + ")";

        str += "\n-------------------------------------";

        return str;
    }
}
