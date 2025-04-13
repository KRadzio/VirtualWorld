package proj.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import proj.java.controller.GameEngine;
import proj.java.model.Organism;
import proj.java.model.World;

public class WorldPanel extends JPanel {
    private final GameEngine engine;

    public WorldPanel(GameEngine engine) {
        this.engine = engine;
        var world = engine.getWorld();
        var size = world.size();
        setLayout(new GridLayout(size, size));
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                add(createCell(world, r, c));
            }
        }
    }

    private JComponent createCell(World world, int row, int col) {
        var organism = world.getOrganism(row, col);
        var color = organism != null ? organism.getColor() : Color.WHITE;
        var icon = new RectangleIcon(color);
        var button = new JButton(icon);
        button.addActionListener(e -> cellClick(world, row, col, button.getX() + 7, button.getY() + 7));
        button.setMargin(new Insets(1, 1, 1, 1));
        return button;
    }

    private void cellClick(World world, int row, int col, int x, int y) {
        if (world.isCellEmpty(row, col)) {
            var popupMenu = new JPopupMenu("Actions");
            if (world.isCellNextToHuman(row, col)) {
                var menuItem = new JMenuItem("Move human here");
                // menuItem.addActionListener(null);
                popupMenu.add(menuItem);
                popupMenu.addSeparator();
            }
            for (var org : engine.getUniqueOrganismsToAdd()) {
                addOrganismMenuItem(world, row, col, popupMenu, org);
            }
            popupMenu.addSeparator();
            popupMenu.add(new JMenuItem("Cancel"));
            popupMenu.show(this, x, y);
        }
    }

    private void addOrganismMenuItem(World world, int row, int col, JPopupMenu popupMenu, Organism org) {
        var menuItem = new JMenuItem("Add " + org.getName() + " here");
        menuItem.addActionListener(e -> engine.addNewOrganism(row, col, org));
        popupMenu.add(menuItem);
    }

    public static class RectangleIcon implements Icon {
        private final Color color;
        private static final int SIZE = 15;

        public RectangleIcon(Color color) {
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, SIZE, SIZE);
        }

        @Override
        public int getIconWidth() {
            return SIZE;
        }

        @Override
        public int getIconHeight() {
            return SIZE;
        }
    }
}
