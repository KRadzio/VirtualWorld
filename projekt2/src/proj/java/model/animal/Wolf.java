package proj.java.model.animal;

import java.awt.Color;

import proj.java.model.Animal;
import proj.java.model.World;

public class Wolf extends Animal{

    private static final int WOLF_STRENGTH = 9;
    private static final int WOLF_INITIATIVE = 5;

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }

    @Override
    public int getInitiative() {
        return WOLF_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return WOLF_STRENGTH + getExtraStrength();
    }

    @Override
    protected void reproduce(World world, int row, int col) {
        world.setOrganism(row, col, new Wolf());
    }
}
