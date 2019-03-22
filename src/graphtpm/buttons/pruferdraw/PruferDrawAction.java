package graphtpm.buttons.pruferdraw;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PruferDrawAction extends AbstractAction {
    public PruferDrawAction(String name){ super(name);}

    public void actionPerformed(ActionEvent e) {
            new PruferDrawWorker().execute();
    }
}