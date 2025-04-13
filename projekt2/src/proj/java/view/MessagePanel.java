package proj.java.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import proj.java.controller.GameEngine;

public class MessagePanel extends JPanel {
    public MessagePanel(GameEngine engine) {
        var messagesAmount = engine.getWorld().getMessages().size();
        var messages = engine.getWorld().getMessages();
        var textArea = new JTextArea(String.format("Wydarzenia w turze %d:", engine.getWorld().getTurn()),1, 50);
        for (int i = 0; i < messagesAmount; i++) {
            textArea.append('\n' + messages.get(i));
        }
        add(textArea);
    }

}
