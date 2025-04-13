package proj.java.model.plant;

import java.awt.Color;

import proj.java.model.Plant;
import proj.java.model.World;

public class Grass extends Plant{

    private static final int GRASS_STRENGTH = 0;

    @Override
    protected void spread(int newRow, int newCol, World world) {
        world.setOrganism(newRow, newCol, new Grass());
    }

    @Override
    public int getStrength() {
        return GRASS_STRENGTH;
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
