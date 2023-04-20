package ui;

public class Main {

    private final Controller cl;

    public Main() {
        cl = new Controller();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.start();
    }

    private void start() {
        cl.display();
    }
}