package graphtpm.buttons.prufer;

import graphtpm.Starter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PruferAction extends AbstractAction {
    public PruferAction(String name){ super(name);}

    public void actionPerformed(ActionEvent e) {
        if(Starter.edges.size()>1){
            new PruferWorker().execute();
        }
    }
}