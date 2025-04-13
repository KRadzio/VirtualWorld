package proj.java.model;

import java.util.Random;


public abstract class Animal extends Organism {

    public boolean collision(World world, Organism other) {
        boolean specialAblityStopsMovement = true;
        if (isTheSameSpecies(other)) {
            if (this.getAge() > 4 && other.getAge() > 4)
                findPlace(world, this.getRow(), this.getCol());
            return false;
        } else if (other.specialAbility(this, world, specialAblityStopsMovement))
            if (specialAblityStopsMovement)
                return false;
            else
                return true;
        else {
            if (this.getStrength() > other.getStrength()) {
                world.removeOrganism(other.getRow(), other.getCol());
                String message = String.format("%s zabija %s", this.getName(), other.getName());
                world.addMessage(message);
                return true;
            } else if (other.getStrength() > this.getStrength()) {
                world.removeOrganism(this.getRow(), this.getCol());
                String message = String.format("%s zabija %s", other.getName(), this.getName());
                world.addMessage(message);
                return false;
            } else {
                return false;
            }
        }
    }

    private void findPlace(World world, int row, int col) {
        int newRow = -1;
        int newCol = -1;

        if (world.getOrganism(row - 1, col) == null) {
            reproduce(world, row - 1, col);
            newRow = row - 1;
            newCol = col;
        } else if (world.getOrganism(row, col + 1) == null) {
            reproduce(world, row, col + 1);
            newRow = row;
            newCol = col + 1;
        } else if (world.getOrganism(row + 1, col) == null) {
            reproduce(world, row + 1, col);
            newRow = row + 1;
            newCol = col;
        } else if (world.getOrganism(row, col - 1) == null) {
            reproduce(world, row, col - 1);
            newRow = row;
            newCol = col - 1;
        }
        if (newRow != -1 && newCol != -1) {
            String message = String.format("Pojawil sie nowy %s na polu (%d , %d)", this.getName(), newCol, newRow);
            world.addMessage(message);
        }

    }

    protected abstract void reproduce(World world, int row, int col);

    public boolean isTheSameSpecies(Organism other) {
        if (this.getName() == other.getName())
            return true;
        else
            return false;
    }

    protected void move(int prevRow, int prevCol, int newRow, int newCol, World world) {
        if (world.isInsideWorld(newRow, newCol) == false)
            return;
        boolean isMovePossible = true;
        if (world.getOrganism(newRow, newCol) != null) {
            isMovePossible = collision(world, world.getOrganism(newRow, newCol));
        }
        if (isMovePossible) {
            world.setOrganism(newRow, newCol, this);
            this.setCol(newCol);
            this.setRow(newRow);
            world.removeOrganism(prevRow, prevCol);
        }
    }

    @Override
    public boolean isAnAnimal() {
        return true;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void takeAction(World world) {
        var rand = new Random().nextInt(4);
        switch (rand) {
            case 0: // gora
                move(getRow(), getCol(), getRow() - 1, getCol(), world);
                break;
            case 1: // prawo
                move(getRow(), getCol(), getRow(), getCol() + 1, world);
                break;
            case 2: // lewo
                move(getRow(), getCol(), getRow() + 1, getCol(), world);
                break;
            case 3: // dol
                move(getRow(), getCol(), getRow(), getCol() - 1, world);
                break;
            default:
                break;
        }
    }

}