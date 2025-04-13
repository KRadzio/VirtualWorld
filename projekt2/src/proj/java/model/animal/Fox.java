package proj.java.model.animal;

import java.awt.Color;
import java.util.Random;

import proj.java.model.Animal;
import proj.java.model.World;

public class Fox extends Animal {

    private static final int FOX_STRENGTH = 3;
    private static final int FOX_INITIATIVE = 7;

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public int getInitiative() {
        return FOX_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return FOX_STRENGTH + getExtraStrength();
    }

    @Override
    protected void reproduce(World world, int row, int col) {
        world.setOrganism(row, col, new Fox());
    }

    @Override
    public void takeAction(World world) {
        var rand = new Random().nextInt(4);
        switch (rand) {
            case 0: // gora
                if (world.isInsideWorld(getRow() - 1, getCol())) {
                    if (world.getOrganism(getRow() - 1, getCol()) != null) {
                        if (world.getOrganism(getRow() - 1, getCol()).getExtraStrength() < this.getStrength())
                            move(getRow(), getCol(), getRow() - 1, getCol(), world);
                    } else
                        move(getRow(), getCol(), getRow() - 1, getCol(), world);
                }
                break;
            case 1: // prawo
                if (world.isInsideWorld(getRow(), getCol() + 1)) {
                    if (world.getOrganism(getRow(), getCol() + 1) != null) {
                        if (world.getOrganism(getRow(), getCol() + 1).getExtraStrength() < this.getStrength())
                            move(getRow(), getCol(), getRow(), getCol() + 1, world);
                    } else
                        move(getRow(), getCol(), getRow(), getCol() + 1, world);
                }
                break;
            case 2: // dol
                if (world.isInsideWorld(getRow() + 1, getCol())) {
                    if (world.getOrganism(getRow() + 1, getCol()) != null) {
                        if (world.getOrganism(getRow() + 1, getCol()).getExtraStrength() < this.getStrength())
                            move(getRow(), getCol(), getRow() + 1, getCol(), world);
                    } else
                        move(getRow(), getCol(), getRow() + 1, getCol(), world);
                }
                break;
            case 3: // lewo
                if (world.isInsideWorld(getRow(), getCol() - 1)) {
                    if (world.getOrganism(getRow(), getCol() - 1) != null) {
                        if (world.getOrganism(getRow(), getCol() - 1).getExtraStrength() < this.getStrength())
                            move(getRow(), getCol(), getRow(), getCol() - 1, world);
                    } else
                        move(getRow(), getCol(), getRow(), getCol() - 1, world);
                }
                break;
            default:
                break;
        }
    }

}
