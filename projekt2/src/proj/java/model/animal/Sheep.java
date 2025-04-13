package proj.java.model.animal;

import java.awt.Color;

import proj.java.model.Animal;
import proj.java.model.World;

public class Sheep extends Animal{

    private static final int SHEEP_STRENGTH = 4;
    private static final int SHEEP_INITIATIVE = 4;

    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public int getInitiative() {
        return SHEEP_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return SHEEP_STRENGTH + getExtraStrength();
    }

    @Override
    protected void reproduce(World world, int row, int col) {
        world.setOrganism(row, col, new Sheep());
    }

}
