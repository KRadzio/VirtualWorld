package proj.java.model.animal;

import java.awt.Color;

import proj.java.model.Animal;
import proj.java.model.World;

public class Human extends Animal {
    private static final int STAY = -1;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int HUMAN_STRENGTH = 5;
    private static final int HUMAN_INITIATIVE = 4;
    private int abilityExtraStrength = 0;
    private int abilityCooldown = 0;
    private int direction = STAY;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void decrementAbilityExtraStrength() {
        if (abilityExtraStrength > 0)
            abilityExtraStrength--;
    }

    public void decrementCooldown() {
        if (abilityCooldown > 0)
            abilityCooldown--;
    }

    public void activateAbility() {
        if (abilityCooldown == 0 && abilityExtraStrength == 0) {
            abilityExtraStrength = 5;
            abilityCooldown = 10;
        }

    }

    public int getAbilityExtraStrength() {
        return abilityExtraStrength;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public Color getColor() {
        return Color.pink;
    }

    @Override
    public int getInitiative() {
        return HUMAN_INITIATIVE;
    }

    @Override
    public int getStrength() {
        return HUMAN_STRENGTH + getExtraStrength() + abilityExtraStrength;
    }

    @Override
    protected void reproduce(World world, int row, int col) { // czlowiek jest jeden
    }

    @Override
    public void takeAction(World world) {
        decrementAbilityExtraStrength();
        decrementCooldown();
        switch (direction) {
            case UP: // gora
                move(getRow(), getCol(), getRow() - 1, getCol(), world);
                break;
            case RIGHT: // prawo
                move(getRow(), getCol(), getRow(), getCol() + 1, world);
                break;
            case DOWN: // dol
                move(getRow(), getCol(), getRow() + 1, getCol(), world);
                break;
            case LEFT: // lewo
                move(getRow(), getCol(), getRow(), getCol() - 1, world);
                break;
            default:
                break;
        }
        direction = STAY;
    }

    @Override
    public String writeString() {
        return String.format("%s %d %d %d %d %d %d %d", getName(), getAge(), getRow(), getCol(),
                getExtraStrength(),
                abilityExtraStrength, abilityCooldown, direction);
    }

    @Override
    public void fromString(String line) {
        super.fromString(line);        
        var fragments = line.split(" ");
        this.abilityExtraStrength = Integer.parseInt(fragments[5]);
        this.abilityCooldown = Integer.parseInt(fragments[6]);
        this.direction = Integer.parseInt(fragments[7]);
    }
    /*
     * Magiczny Eliksir:
     * Siła Człowieka rośnie do 10 w pierwszej turze
     * działania umiejętności. W każdej kolejnej turze maleje
     * o „1”, aż wróci do stanu początkowego.
     */
}
