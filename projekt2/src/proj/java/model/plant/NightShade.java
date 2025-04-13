package proj.java.model.plant;

import java.awt.Color;

import proj.java.model.Organism;
import proj.java.model.Plant;
import proj.java.model.World;

public class NightShade extends Plant{

  private static final int NIGHTSHADE_STRENGTH = 99;

  @Override
  protected void spread(int newRow, int newCol, World world) {
      world.setOrganism(newRow, newCol, new NightShade());
  }

  @Override
  public int getStrength() {
      return NIGHTSHADE_STRENGTH;
  }

  @Override
  public Color getColor() {
      return Color.BLUE;
  }

  @Override
  public boolean specialAbility(Organism other, World world, boolean specialAblityStopsMovement) {
      world.removeOrganism(other.getRow(), other.getCol());
      world.removeOrganism(this.getRow(), this.getCol());
      String message = String.format("%s zjada wilcze jagody i umiera" , other.getName());
      world.addMessage(message);
      specialAblityStopsMovement = true;
      return true;
  }
}
