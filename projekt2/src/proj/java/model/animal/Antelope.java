package proj.java.model.animal;

import java.awt.Color;
import java.util.Random;

import proj.java.model.Animal;
import proj.java.model.Organism;
import proj.java.model.World;

public class Antelope extends Animal {

    private static final int ANTELOPE_STRENGTH = 4;
    private static final int ANTELOPE_INITIATIVE = 4;
    private static final int ANTELOPE_MOVE_RANGE = 2;

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }

    @Override
    public int getInitiative() {
        return ANTELOPE_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return ANTELOPE_STRENGTH + getExtraStrength();
    }

    @Override
    protected void reproduce(World world, int row, int col) {
        world.setOrganism(row, col, new Antelope());
    }

    @Override
    public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {
        var rand = new Random().nextInt(2);
        if (rand == 1) {
            if (world.getOrganism(getRow() - 1, getCol()) == null)
                move(getRow(), getCol(), getRow() - 1, getCol(), world);
            else if (world.getOrganism(getRow(), getCol() + 1) == null)
                move(getRow(), getCol(), getRow(), getCol() + 1, world);
            else if (world.getOrganism(getRow() + 1, getCol()) == null)
                move(getRow(), getCol(), getRow() + 1, getCol(), world);
            else if (world.getOrganism(getRow(), getCol() - 1) == null)
                move(getRow(), getCol(), getRow(), getCol() - 1, world);
            String message = String.format("%s uciekla przed %s", this.getName(), other.getName());
            world.addMessage(message);
            specialAblityStopsMovement = false;
            return true;
        }
        specialAblityStopsMovement = false;
        world.removeOrganism(this.getRow(), this.getCol());
        return true;
    }

    @Override
    public void takeAction(World world) {
        var rand = new Random().nextInt(4);
        switch (rand) {
            case 0: // gora
                move(getRow(), getCol(), getRow() - ANTELOPE_MOVE_RANGE, getCol(), world);
                break;
            case 1: // prawo
                move(getRow(), getCol(), getRow(), getCol() + ANTELOPE_MOVE_RANGE, world);
                break;
            case 2: // lewo
                move(getRow(), getCol(), getRow() + ANTELOPE_MOVE_RANGE, getCol(), world);
                break;
            case 3: // dol
                move(getRow(), getCol(), getRow(), getCol() - ANTELOPE_MOVE_RANGE, world);
                break;
            default:
                break;
        }
    }

}
