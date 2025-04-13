package proj.java.view;

import javax.swing.JFrame;

import proj.java.controller.GameEngine;

public class MainFrame extends JFrame {
    private final GameEngine engine;

    public MainFrame(GameEngine engine) {
        this.engine = engine;
        //
        setTitle("Virtual world simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel(engine)); // ustawiamy zawartosc
        pack(); // pakujemy zawartosc
        setLocationRelativeTo(null); // centrum ekranu
    }

    public void updateView() {
        setContentPane(new MainPanel(engine)); 
        pack(); 
        setLocationRelativeTo(null); 
        revalidate();
    }
}
