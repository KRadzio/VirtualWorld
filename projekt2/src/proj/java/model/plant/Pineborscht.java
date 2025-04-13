package proj.java.model.plant;

import java.awt.Color;
import java.util.Random;

import proj.java.model.Organism;
import proj.java.model.Plant;
import proj.java.model.World;

public class Pineborscht extends Plant {
    private static final int PINEBORSCHT_STRENGTH = 10;

    @Override
    protected void spread(int newRow, int newCol, World world) {
        world.setOrganism(newRow, newCol, new Pineborscht());
    }

    @Override
    public int getStrength() {
        return PINEBORSCHT_STRENGTH;
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {
        world.removeOrganism(other.getRow(), other.getCol());
        world.removeOrganism(this.getRow(), this.getCol());
        String message = String.format("%s zjada barszcz sosnowskiego i umiera", other.getName());
        world.addMessage(message);
        specialAblityStopsMovement = true;
        return true;
    }

    private void removeNeighbour(int row, int col, World world) {
        if(world.isInsideWorld(row, col) == false)
            return;
        if (world.getOrganism(row, col) != null && world.getOrganism(row, col).isAnAnimal()) {
            String message = String.format("%s zostaje spalony przez barszcz sosnowskiego",
                    world.getOrganism(row, col).getName());
            world.addMessage(message);
            world.removeOrganism(row, col);
        }
    }

    @Override
    public void takeAction(World world) {
        removeNeighbour(getRow() - 1, getCol(), world); // gora
        removeNeighbour(getRow(), getCol() + 1, world); // prawo
        removeNeighbour(getRow() + 1, getCol(), world); // dol
        removeNeighbour(getRow(), getCol() - 1, world); // lewo
        var random = new Random();
        if (random.nextInt(100) < 5) {
            trySpread(world);
        }
    }
}
