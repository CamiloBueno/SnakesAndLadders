package model;

public class Player {

    //Attributes
    private String name;
    private char symbol;

    //Builder
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}