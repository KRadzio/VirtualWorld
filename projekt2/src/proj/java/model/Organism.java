package proj.java.model;

import java.awt.Color;

public abstract class Organism {
    private int age = 1;
    private int row;
    private int col;
    private int extraStrength = 0;

    public int getExtraStrength() {
        return extraStrength;
    }

    public void setExtraStrength(int extraStrength) {
        this.extraStrength += extraStrength;
    }

    public abstract Color getColor();

    public String getName() {
        return getClass().getSimpleName();
    }

    public abstract int getInitiative();

    public abstract int getStrength();

    public abstract boolean isHuman();

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        this.age++;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public abstract boolean isAnAnimal();

    public abstract void takeAction(World world);

    public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {
        return false;
    }

    public String writeString() {
        return String.format("%s %d %d %d %d", getName(), age, row, col, extraStrength);
    }

    public void fromString(String line) {
        var fragments = line.split(" ");
        this.age = Integer.parseInt(fragments[1]);
        this.row = Integer.parseInt(fragments[2]);
        this.col = Integer.parseInt(fragments[3]);
        this.extraStrength = Integer.parseInt(fragments[4]);
    }
}
