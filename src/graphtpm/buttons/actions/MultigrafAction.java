package graphtpm.buttons.actions;

import graphtpm.Starter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MultigrafAction extends AbstractAction {

    public MultigrafAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        Starter.deleteB= false;
        Starter.gp.setCursor(Starter.gp.cursorHand);
        Starter.gp.repaint();
    }
}
