package model;

public class Space {

    //Attribute
    private int key;


    //Relationship
    private Space next;
    private Space snake;
    private Space ladder;

    //Builder
    public Space(int key) {
        this.key = key;
    }



    //Getters and setters

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Space getNext() {
        return next;
    }

    public void setNext(Space next) {
        this.next = next;
    }

    public Space getSnake() {
        return snake;
    }

    public void setSnake(Space snake) {
        this.snake = snake;
    }

    public Space getLadder() {
        return ladder;
    }

    public void setLadder(Space ladder) {
        this.ladder = ladder;
    }
}