package proj.java.model.animal;

import java.awt.Color;
import java.util.Random;

import proj.java.model.Animal;
import proj.java.model.Organism;
import proj.java.model.World;

public class Turtle extends Animal{

    private static final int TURTLE_STRENGTH = 2;
    private static final int TURTLE_INITIATIVE = 1;

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    @Override
    public int getInitiative() {
        return TURTLE_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return TURTLE_STRENGTH + getExtraStrength();
    }

    @Override
    protected void reproduce(World world, int row, int col) {
        world.setOrganism(row, col, new Turtle());
    }

    @Override
    public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {

        if(other.getStrength() >= 5)
        {
            specialAblityStopsMovement = false;
            return false;
        }        
        else
        {
            String message = String.format("%s odstrasza %s", this.getName(), other.getName());
            world.addMessage(message);
            specialAblityStopsMovement = true;
            return true;
        }              
    }

    @Override
    public void takeAction(World world) {
        var rand = new Random().nextInt(4);
        if(rand < 1)
        {
            rand = new Random().nextInt(4);
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
}
