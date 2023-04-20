package ui;

import model.Board;
import model.Space;

import java.util.ArrayList;
import java.util.Arrays;

public class GridBoard {

    private static String[] screenLine;
    private static int gameSpace;
    private static ArrayList<Integer> snakes;
    private static ArrayList<Integer> ladders;
    private static ArrayList<String> players;
    private static ArrayList<Character> symbolPlayer;
    private static String[][] matrix;


    public static int newGame(int size, int availableSpaces, ArrayList<Integer> tempSnakes, ArrayList<Integer> tempLadders, ArrayList<String> tempInfoPlayers, ArrayList<Character> tempPlayers) {
        matrix = new String[size][size];
        screenLine = new String[size];
        gameSpace = availableSpaces;
        snakes = tempSnakes;
        ladders = tempLadders;
        players = tempInfoPlayers;
        symbolPlayer = tempPlayers;
        return controlGame();
    }

    private static int controlGame() {
        displayGrid();
        return 0;
    }

    public static void displayGrid(){
        int top = screenLine.length * screenLine.length;
        boolean r = top % 2 == 0;
        int aux = top;
        for (int i = 0; i < screenLine.length; i++) {
            int j = 0;
            if (r) {
                while (j < screenLine.length) {
                    matrix[i][j] = String.valueOf(aux--);
                    j++;
                }
                r = false;
            } else {
                aux -= screenLine.length - 1;
                int temp = aux;
                while (j < screenLine.length) {
                    matrix[i][j] = String.valueOf(aux++);
                    j++;
                }
                aux = temp - 1;
                r = true;
            }
            //System.out.println(martix);
        }
        for (Character c:symbolPlayer) {
            matrix[screenLine.length-1][0] += c;
        }
        for (int i = 0; i < snakes.size(); i+=2) {
            double a = Double.parseDouble(String.valueOf(snakes.get(i))) / Double.parseDouble(String.valueOf(screenLine.length));
            int x = (int) Math.floor(a - 0.1);
            x = screenLine.length - 1 - x;
            for (int j = 0; j < screenLine.length; j++) {
                if (matrix[x][j].contains(snakes.get(i).toString())) {
                    matrix[x][j] += "S"+snakes.get(i)+"H";
                    break;
                }
            }
            a = Double.parseDouble(String.valueOf(snakes.get(i+1))) / Double.parseDouble(String.valueOf(screenLine.length));
            x = (int) Math.floor(a - 0.1);
            x = screenLine.length - 1 - x;
            for (int j = 0; j < screenLine.length; j++) {
                if (matrix[x][j].contains(snakes.get(i+1).toString())) {
                    matrix[x][j] += "S"+snakes.get(i)+"T";
                    break;
                }
            }
        }
        for (int i = 0; i < ladders.size(); i+=2) {
            double a = Double.parseDouble(String.valueOf(ladders.get(i))) / Double.parseDouble(String.valueOf(screenLine.length));
            int x = (int) Math.floor(a - 0.1);
            x = screenLine.length - 1 - x;
            for (int j = 0; j < screenLine.length; j++) {
                if (matrix[x][j].contains(ladders.get(i).toString())) {
                    matrix[x][j] += "L"+ladders.get(i)+"T";
                    break;
                }
            }
            a = Double.parseDouble(String.valueOf(ladders.get(i+1))) / Double.parseDouble(String.valueOf(screenLine.length));
            x = (int) Math.floor(a - 0.1);
            x = screenLine.length - 1 - x;
            for (int j = 0; j < screenLine.length; j++) {
                if (matrix[x][j].contains(ladders.get(i+1).toString())) {
                    matrix[x][j] += "L"+ladders.get(i)+"B";
                    break;
                }
            }
        }
        soutMatrix();
    }

    private static void soutMatrix() {
        for (String[] row:matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
