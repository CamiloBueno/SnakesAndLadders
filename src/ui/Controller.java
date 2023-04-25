package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    static Scanner sc;
    model.Controller cl;
    ArrayList<String> tempPlayers;

    public Controller() {
        sc = new Scanner(System.in);
        tempPlayers = new ArrayList<>();
    }

    public void display() {
        int op;
        do {
            Menu.showMenu();
            op = validateInput(0,2);
            clearConsole();
            option(op);
        } while (op != 0);
    }

    private void option(int op) {
        switch (op) {
            case 0 -> System.out.println("--Thanks for using app--");
            case 1 -> optionPlay();
            case 2 -> optionScoreBoard();
        }
    }

    private void optionScoreBoard() {
        ArrayList<String> tempScore = new ArrayList<>();
        int i = 0;
        boolean loop = true;
        do {
            if (cl.getScoreBoard(i).isEmpty()) loop = false;
            else tempScore.add(cl.getScoreBoard(i));
            i++;
        }while (loop);
        if (tempScore.size() == 0) {
            System.out.println(
                    "--- No scores yet ---\n"+
                            "press any key for continue"
            );
            String temp = sc.nextLine();
            sc.nextLine();
            clearConsole();
            return;
        }
        ShowScoreBoard(tempScore);
    }

    private void ShowScoreBoard(ArrayList<String> tempScore) {
        String out = "  ___ ++ ** ^^ SCOREBOARD ^^ ** ++ ___\n";
        for (int i = 0; i < tempScore.size(); i++) {
            out += ">Game " + (i+1) + ":  " + tempScore.get(i) + "\n";
        }
        System.out.println(out);
    }

    private void optionPlay() {
        Menu.showSettingsPlay();
        String temp = sc.nextLine();
        addPlayers();
        sizeBoard();
        clearConsole();
        savePlayers();
        startGame();
    }

    private void startGame() {
        ArrayList<Integer> tempSnakes = new ArrayList<>();
        ArrayList<Integer> tempLadders = new ArrayList<>();
        ArrayList<String> tempInfoPlayers = new ArrayList<>();
        ArrayList<Character> tempPlayers = new ArrayList<>();
        int size = cl.getSizeBoard();
        int availableSpaces = size*size;
        for (int i = 0; i < availableSpaces; i++) {
            if (cl.hasSnake(i)) {
                tempSnakes.add(i);
                tempSnakes.add(cl.getTailSnake(i));
            } else if (cl.hasLadder(i)) {
                tempLadders.add(cl.getTopLadder(i));
                tempLadders.add(i);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!cl.getInfoPlayer(i).equals("")) {
                tempInfoPlayers.add(cl.getInfoPlayer(i));
                tempPlayers.add(tempInfoPlayers.get(i).charAt(tempInfoPlayers.get(i).length()-1));
            }
        }
        long time = System.currentTimeMillis();
        int winPlayer = GridBoard.newGame(size, availableSpaces, tempSnakes, tempLadders, tempInfoPlayers, tempPlayers);
        cl.newScore(cl.getPlayer(winPlayer), System.currentTimeMillis()-time);
    }

    private void savePlayers() {
        for (int i = 0; i < tempPlayers.size(); i++) {
            cl.addNewPlayer(tempPlayers.get(i), i == 0? '*' : i == 1? '+' : '^');
        }
    }

    private void sizeBoard() {
        clearConsole();
        Menu.showOptionsSizeBoard();
        int op = validateInput(1,3);
        int size = 0;
        switch (op) {
            case 1 -> size = 3;
            case 2 -> size = 4;
            case 3 -> size = 5;
        }
        cl = new model.Controller(size, size);
    }

    private void addPlayers() {
        boolean loop = true;
        do {
            clearConsole();
            Menu.showSettingsAddPlayer();
            String name = sc.nextLine();
            tempPlayers.add(name);
            clearConsole();
            Menu.showSettingsNextPlayer();
            int op = validateInput(1,2);
            if (op == 2) loop = false;
            else if (tempPlayers.size() == 3) {
                System.out.println("Only three players are allowed");
                loop = false;
            }
        } while (loop);
    }

    public static int validateInput(int min, int max){
        boolean loop = false;
        int value = min -1;
        while (!loop){
            String aux = sc.nextLine();
            if (!aux.matches("[0-9]+")) System.err.println("only numeric values are allowed");
            else{
                value = Integer.parseInt(aux);
                loop = inRange(min,max,value);
                if (!loop) System.err.println("option not available");
            }
        }
        System.out.println();
        return value;
    }

    public static boolean inRange(int min, int max, int n){
        return n >= min && n <= max;
    }

    public static void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
