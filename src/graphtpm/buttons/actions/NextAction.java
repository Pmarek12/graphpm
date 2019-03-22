package graphtpm.buttons.actions;

import graphtpm.Starter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NextAction extends AbstractAction {

    public NextAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        Starter.nextB^= true;
    }
}
