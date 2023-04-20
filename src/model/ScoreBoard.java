package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScoreBoard {

    String time;
    String player;
    String date;

    public ScoreBoard(String player, long time) {
        this.time = parseTime(time);
        this.player = player;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date = currentDate.format(formatter);
    }

    private String parseTime(long time) {
        int hours = (int) (time/3600000);
        int minutes = (int) ((time%3600000)/60000);
        int seconds = (int) ((time%3600000)%60000)/1000;
        return hours + " : " + minutes + " : " + seconds;
    }

    public String getTime() {
        return time;
    }

    public String getPlayer() {
        return player;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return player + "     " + time + "   " + date;
    }
}
