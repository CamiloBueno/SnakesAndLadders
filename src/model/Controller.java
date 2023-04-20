package model;

import java.util.ArrayList;

public class Controller {

    private static Board board;
    private static ArrayList<ScoreBoard> scoreBoard;
    private static Player[] players;

    public Controller(int height, int width) {
        board = new Board(height, width);
        scoreBoard = new ArrayList<>();
        players = new Player[3];
    }

    public void addNewPlayer(String name, char symbol) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = new Player(name, symbol);
                return;
            }
        }
    }

    public boolean hasSnake(int space) {
        return board.hasSnake(space);
    }

    public boolean hasLadder(int space) {
        return board.hasLadder(space);
    }

    public int getTailSnake(int space) {
        return board.getSnake(space);
    }

    public int getTopLadder(int space) {
        return board.getLadder(space);
    }

    public void newScore(String player, long time) {
        scoreBoard.add(new ScoreBoard(player, time));
    }

    public String getScoreBoard(int index) {
        return index < scoreBoard.size()? scoreBoard.get(index).toString() : "";
    }

    public String getInfoPlayer(int index) {
        String out = "";
        if (index < players.length && players[index] != null)
            out += "Player " + (index+1) + ": " + players[index].getName() + "  Symbol: " + players[index].getSymbol();
        return out;
    }

    public int getSizeBoard() {
        return board.getHeight();
    }

    public String getPlayer(int index) {
        return players[index].getName();
    }
}
