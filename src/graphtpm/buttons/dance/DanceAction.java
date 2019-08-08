package graphtpm.buttons.dance;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class DanceAction extends AbstractAction {
    public DanceAction(String name){ super(name);}

    public void actionPerformed(ActionEvent e) {
        new DanceWorker().execute();
    }
}