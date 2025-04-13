package proj.java.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import proj.java.model.Organism;
import proj.java.model.World;
import proj.java.model.animal.Antelope;
import proj.java.model.animal.Fox;
import proj.java.model.animal.Human;
import proj.java.model.animal.Sheep;
import proj.java.model.animal.Turtle;
import proj.java.model.animal.Wolf;
import proj.java.model.plant.Dandelion;
import proj.java.model.plant.Grass;
import proj.java.model.plant.Guarana;
import proj.java.model.plant.NightShade;
import proj.java.model.plant.Pineborscht;
import proj.java.view.MainFrame;

public class GameEngine {
    private World world;
    private MainFrame frame;

    public World getWorld() {
        return world;
    }

    public void newGame(int size) {
        world = new World(size);
        populateWorld(world);
        if (frame == null) {
            frame = new MainFrame(this);
            frame.setVisible(true);
        } else {
            frame.updateView();
        }
    }

    private Organism createOrganism(int randomNumber) {
        Organism newOrg = null;
        switch (randomNumber) {
            case 0:
                newOrg = new Grass();
                break;
            case 1:
                newOrg = new Dandelion();
                break;
            case 2:
                newOrg = new Guarana();
                break;
            case 3:
                newOrg = new NightShade();
                break;
            case 4:
                newOrg = new Pineborscht();
                break;
            case 5:
                newOrg = new Sheep();
                break;
            case 6:
                newOrg = new Fox();
                break;
            case 7:
                newOrg = new Turtle();
                break;
            case 8:
                newOrg = new Antelope();
                break;
            case 9:
                newOrg = new Wolf();
                break;
            default:
                break;
        }
        return newOrg;
    }

    private void populateWorld(World world) {
        Human human = new Human();
        world.setOrganism(world.size() / 2, world.size() / 2, human);
        world.setHuman(human);
        for (int i = 0; i < world.size(); i++) {
            int rand = new Random().nextInt(10);

            boolean positionIsClear = false;
            int newRow = 0;
            int newCol = 0;
            while (positionIsClear == false) {
                newRow = new Random().nextInt(world.size());
                newCol = new Random().nextInt(world.size());
                if (world.getOrganism(newRow, newCol) == null)
                    positionIsClear = true;
            }
            world.setOrganism(newRow, newCol, createOrganism(rand));
        }
    }

    public List<Organism> getUniqueOrganismsToAdd() {
        return getOrganisms().stream()
                .filter(org -> !(org instanceof Human))
                .map(org -> org.getClass())
                .distinct()
                .map(this::newInstance)
                .toList();
    }

    private Organism newInstance(Class<? extends Organism> orgClass) {
        try {
            return orgClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public void addNewOrganism(int row, int col, Organism org) {
        world.setOrganism(row, col, org);
        frame.updateView();
    }

    public void loadGame(File file) {
        try {
            var lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            this.world = new World(lines.get(0));
            var emptyLineIdx = lines.indexOf("");
            for (int i = 1; i < emptyLineIdx; i++) {
                var line = lines.get(i);
                var fragments = line.split(" ");
                var org = createInstance(fragments[0]);
                org.fromString(line);
                world.setOrganism(org.getRow(), org.getCol(), org);
                if (org instanceof Human) {
                    world.setHuman((Human) org);
                }
            }
            for (int i = emptyLineIdx + 1; i < lines.size(); i++) {
                var msg = lines.get(i);
                world.addMessage(msg);
            }
            frame.updateView();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Organism createInstance(String name) {
        try {
            try {
                var clazz = Class.forName("proj.java.model.animal." + name);
                return (Organism) clazz.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                var clazz = Class.forName("proj.java.model.plant." + name);
                return (Organism) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void saveGame(File file) {
        try {
            StringBuilder buf = new StringBuilder();
            buf.append(world.writeString()).append("\n");

            var orgs = getOrganisms();
            orgs.forEach(org -> buf.append(org.writeString()).append("\n"));

            buf.append("\n");
            var messages = world.getMessages();
            messages.forEach(msg -> buf.append(msg).append("\n"));

            Files.writeString(file.toPath(), buf.toString(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void runTurn() {
        world.clearMessges();
        var orgs = getOrganisms();
        orgs.forEach(this::takeAction); // wykonuje tez ture dla organizmow martwych
        orgs.forEach(this::incrementAge);
        frame.updateView();
        world.incrementTurn();
    }

    public void activateSpecialAbility() {
        world.getHuman().activateAbility();
        frame.updateView();
    }

    public void selectDirection(int direction) {
        world.getHuman().setDirection(direction);
    }

    private List<Organism> getOrganisms() {
        var orgs = new ArrayList<Organism>();
        var size = world.size();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                var org = world.getOrganism(r, c);
                if (org != null) {
                    orgs.add(org);
                }
            }
        }
        return orgs.stream().sorted(this::compareOrganisms).toList();
    }

    private int compareOrganisms(Organism o1, Organism o2) {
        var result = o1.getInitiative() - o2.getInitiative();
        if (result == 0) {
            return -(o1.getAge() - o2.getAge());
        } else {
            return -result;
        }
    }

    private void takeAction(Organism org) {
        org.takeAction(world);
    }

    private void incrementAge(Organism org) {
        org.incrementAge();
    }
}