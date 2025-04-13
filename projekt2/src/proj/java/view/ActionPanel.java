package proj.java.view;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;

import proj.java.controller.GameEngine;

public class ActionPanel extends JToolBar {
    private final GameEngine engine;
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final String GAME_FILE_SUFFIX = ".wgf";

    public ActionPanel(GameEngine engine) {
        this.engine = engine;
        //
        setFloatable(false);
        var newGameButton = new JButton("New game");
        newGameButton.addActionListener(e -> newGame());
        add(newGameButton);
        addSeparator();
        var loadGameButton = new JButton("Load game");
        loadGameButton.addActionListener(e -> loadGame());
        add(loadGameButton);
        addSeparator();
        var saveGameButton = new JButton("Save game");
        saveGameButton.addActionListener(e -> saveGame());
        add(saveGameButton);
        addSeparator();
        var runTurnButton = new JButton("Run turn");
        runTurnButton.addActionListener(e -> runTurn());
        add(runTurnButton);
        addSeparator();
        if (engine.getWorld().getHuman().getAbilityCooldown() == 0) {
            var specialAbilityButton = new JButton("Special ability");
            specialAbilityButton.addActionListener(e -> activateSpecialAbility());
            add(specialAbilityButton);
            addSeparator();
        }
        var upButton = new JButton("Move up");
        upButton.addActionListener(e -> selectDirection(UP));
        add(upButton);
        addSeparator();
        var rightButton = new JButton("Move right");
        rightButton.addActionListener(e -> selectDirection(RIGHT));
        add(rightButton);
        addSeparator();
        var downButton = new JButton("Move down");
        downButton.addActionListener(e -> selectDirection(DOWN));
        add(downButton);
        addSeparator();
        var leftButton = new JButton("Move left");
        leftButton.addActionListener(e -> selectDirection(LEFT));
        add(leftButton);
    }

    private void newGame() {
        var sizeStr = JOptionPane.showInputDialog(this,
                "Specify size in range 20-40",
                "New game",
                JOptionPane.PLAIN_MESSAGE);
        try {
            var size = Integer.parseInt(sizeStr);
            if (size < 20 || size > 40) {
                throw new IllegalArgumentException("Out of range");
            }
            engine.newGame(size);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Incorrect size",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadGame() {
        var homeDir = new File(System.getProperty("user.home"));
        var fileChooser = new JFileChooser(homeDir);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new GameFileFilter());
        var returnVal = fileChooser.showDialog(this, "Load");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            engine.loadGame(fileChooser.getSelectedFile());
        }
    }

    private void saveGame() {
        var homeDir = new File(System.getProperty("user.home"));
        var fileChooser = new JFileChooser(homeDir);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new GameFileFilter());
        var returnVal = fileChooser.showDialog(this, "Save");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            var file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(GAME_FILE_SUFFIX)) {
                file = new File(file + GAME_FILE_SUFFIX);
            }
            engine.saveGame(file);
        }
    }

    private void runTurn() {
        engine.runTurn();
    }

    private void activateSpecialAbility() {
        engine.activateSpecialAbility();
    }

    private void selectDirection(int direction) {
        engine.selectDirection(direction);
    }

    private static class GameFileFilter extends FileFilter {
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                return f.getName().endsWith(GAME_FILE_SUFFIX);
            }
        }

        @Override
        public String getDescription() {
            return "World game files (" + GAME_FILE_SUFFIX + ")";
        }
    }
}
