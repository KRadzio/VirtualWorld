package proj.java.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import proj.java.controller.GameEngine;

public class MainPanel extends JPanel {
    public MainPanel(GameEngine engine) {
        setLayout(new BorderLayout());
        add(new WorldPanel(engine), BorderLayout.CENTER);
        add(new ActionPanel(engine), BorderLayout.PAGE_END);
        add(new MessagePanel(engine), BorderLayout.LINE_END);
    }
}
