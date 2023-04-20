package ui;

public class Menu {

    public static void showMenu() {
        System.out.println(
                "|-----------------------------|\n"+
                "|Welcome to Snakes and ladders|\n"+
                "|-----------------------------|\n"+
                        "\n"+
                        "Options:\n"+
                        "(1) Play\n"+
                        "(2) ScoreBoard\n"+
                        "(0) Exit"
        );
    }

    public static void showSettingsPlay() {
        System.out.println(
                "Play settings:\n"+
                        "(1) Add players\n"+
                        "(2) Board size\n"+
                        "--Press any key for continue--"
        );
    }

    public static void showSettingsAddPlayer() {
        System.out.println(
                "**************************\n"+
                "*--Please type nickname--*\n"+
                "**************************"
        );
    }


    public static void showSettingsNextPlayer() {
        System.out.println(
                "---------------------------\n"+
                "You want add another player?\n"+
                "---------------------------\n"+
                        "(1) Yes / (2) No"
        );
    }

    public static void showOptionsSizeBoard() {
        System.out.println(
                "Size board:\n"+
                        "(1) 3x3\n"+
                        "(2) 4x4\n"+
                        "(3) 5x5"
        );
    }
}
