package proj.java.model.plant;

import java.awt.Color;

import proj.java.model.Organism;
import proj.java.model.Plant;
import proj.java.model.World;

public class Guarana extends Plant {

  private static final int GUARANA_STRENGTH = 0;
  private static final int STRENGTH_BONUS = 3;

  @Override
  protected void spread(int newRow, int newCol, World world) {
      world.setOrganism(newRow, newCol, new Guarana());
  }

  @Override
  public int getStrength() {
    return GUARANA_STRENGTH;
  }

  @Override
  public Color getColor() {
    return Color.RED;
  }

  @Override
  public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {
    other.setExtraStrength(STRENGTH_BONUS);
    String message = String.format("%s zjada guaranne jego siła wzrasta o %d" , other.getName(), STRENGTH_BONUS);
    world.addMessage(message);
    world.removeOrganism(this.getRow(), this.getCol());
    specialAblityStopsMovement = false;
    return true;
  }
}
