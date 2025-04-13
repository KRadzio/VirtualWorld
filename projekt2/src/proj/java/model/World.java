package proj.java.model;

import java.util.ArrayList;
import java.util.List;

import proj.java.model.animal.Human;

public class World {
    private final int size;
    private final Organism[][] cells;
    private final List<String> messages;
    private int turn = 1;
    Human human = null;

    public World(int size) {
        this.size = size;
        this.cells = new Organism[size][size];
        for (var r = 0; r < size; r++) {
            var row = new Organism[size];
            this.cells[r] = row;
        }
        this.messages = new ArrayList<>();
    }

    public World(String line) {
        var fragments = line.split(" ");
        this.size = Integer.parseInt(fragments[0]);
        this.cells = new Organism[size][size];
        for (var r = 0; r < size; r++) {
            var row = new Organism[size];
            this.cells[r] = row;
        }
        this.messages = new ArrayList<>();
        this.turn = Integer.parseInt(fragments[1]);

    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public boolean isCellNextToHuman(int row, int col) {
        return col == human.getCol() && row == human.getRow() - 1
                || col == human.getCol() && row == human.getRow() + 1
                || col == human.getCol() - 1 && row == human.getRow()
                || col == human.getCol() + 1 && row == human.getRow();
    }

    public boolean isInsideWorld(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size)
            return false;
        else
            return true;
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        turn++;
    }

    public int size() {
        return size;
    }

    public boolean isCellEmpty(int row, int col) {
        return getOrganism(row, col) == null;
    }

    public Organism getOrganism(int row, int col) {
        return cells[row][col];
    }

    public void removeOrganism(int row, int col) {
        cells[row][col] = null;
    }

    public void setOrganism(int row, int col, Organism org) {
        org.setRow(row);
        org.setCol(col);
        cells[row][col] = org;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void clearMessges() {
        messages.clear();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String writeString() {
        return String.format("%d %d", size, turn);
    }
}
