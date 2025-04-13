package proj.java;

import proj.java.controller.GameEngine;

public class Main {
    public static void main(String[] args) {
        var engine = new GameEngine();
        engine.newGame(20);
    }
}