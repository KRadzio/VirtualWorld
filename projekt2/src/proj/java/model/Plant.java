package proj.java.model;

import java.util.Random;

public abstract class Plant extends Organism {

    private static final int PLANT_INITIATIVE = 0;

    protected abstract void spread(int newRow, int newCol, World world);

    protected void trySpread(World world) {
        boolean spreadIsValid = false;
        int newRow = 0;
        int newCol = 0;

        Random random = new Random();
        while (spreadIsValid == false) {
            newRow = random.nextInt(world.size());
            newCol = random.nextInt(world.size());
            if (world.getOrganism(newRow, newCol) == null)
                spreadIsValid = true;
        }
        String message = String.format("%s rozprzestrzenil sie na pole (%d , %d)", this.getName(), newCol, newRow);
        world.addMessage(message);
        spread(newRow, newCol, world);
    }

    @Override
    public boolean isAnAnimal() {
        return false;
    }

    @Override
    public int getInitiative() {
        return PLANT_INITIATIVE;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void takeAction(World world) {
        var random = new Random();
        if (random.nextInt(100) < 10) {
            trySpread(world);
        }
    }

}
