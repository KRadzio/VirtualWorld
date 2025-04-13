package proj.java.model.plant;

import java.awt.Color;
import java.util.Random;

import proj.java.model.Plant;
import proj.java.model.World;

public class Dandelion extends Plant {

    private static final int DANDELION_STRENGTH = 0;

    @Override
    protected void spread(int newRow, int newCol, World world) {
        world.setOrganism(newRow, newCol, new Dandelion());
    }

    @Override
    public int getStrength() {
        return DANDELION_STRENGTH;
    }

    @Override
    public void takeAction(World world) {
        for (int i = 0; i < 3; i++) {
            var random = new Random();
            if (random.nextInt(100) < 10) {
                trySpread(world);
            }
        }

    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}
