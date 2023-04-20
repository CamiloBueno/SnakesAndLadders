package model;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    //Attribute
    private final int height;
    private final int width;

    //Relationship
    private final Random rd;
    private final Space head;

    //Builder
    public Board(int height, int width) {
        rd = new Random();
        this.height = height;
        this.width = width;
        head = new Space(1);
        addSpaces(head,2,(height*width)+1);
        snakesAndLaddersGenerator();
    }

    public Space getHead() {
        return head;
    }

    private void addSpaces(Space current, int key, int totalSpaces) {
        if (key == totalSpaces) return;
        current.setNext(new Space(key));
        addSpaces(current.getNext(), ++key, totalSpaces);
    }

    private void snakesAndLaddersGenerator() {
        int totalEntity = searchTotalEntity();

        ArrayList<Integer> entity = new ArrayList<>();
        int high;
        int low;

        for (int i = 0; i < totalEntity; i++) {
            do {
                high = rd.nextInt(2, (height*width)-1);
                low = rd.nextInt(2, (height*width)-1);
            } while (entity.contains(high) || entity.contains(low) || high <= low || high - low == 1);
            entity.add(high);
            entity.add(low);
        }

        //Relationship between Snakes and Ladders of Spaces
        for (int i = 0; i < entity.size(); i += 2) {
            Space entities;
            if (i < entity.size() / 2) { //Snakes
                entities = searchSpace(head, entity.get(i));
                entities.setSnake(searchSpace(head, entity.get(i+1)));
            } else { //Ladders
                entities = searchSpace(head, entity.get(i+1));
                entities.setLadder(searchSpace(head, entity.get(i)));
            }
        }
    }

    public Space searchSpace(Space current, int goal) {
        if (current.getKey() == goal) return current;
        return searchSpace(current.getNext(),goal);
    }

    private int searchTotalEntity() {
        int availableSpaces = (height*width) - 2;
        if (availableSpaces%2 != 0) availableSpaces -= 1;

        int totalEntity = availableSpaces/2;

        return totalEntity%2 == 0? totalEntity: totalEntity-1;
    }

    public boolean hasSnake(int space) {
        Space temSpace = searchSpace(head, space);
        return temSpace.getSnake() != null;
    }

    public boolean hasLadder(int space) {
        Space temSpace = searchSpace(head, space);
        return temSpace.getLadder() != null;
    }

    public int getSnake(int space) {
        Space tempSpace = searchSpace(head, space);
        return tempSpace.getSnake().getKey();
    }

    public int getLadder(int space) {
        Space tempSpace = searchSpace(head, space);
        return tempSpace.getLadder().getKey();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
