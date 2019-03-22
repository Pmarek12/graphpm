package graphtpm.buttons.actions;

import graphtpm.Starter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {
    public DeleteAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        Starter.deleteB= true;
        Starter.gp.setCursor(Starter.gp.cursorDelete);
        Starter.gp.repaint();
    }
}
