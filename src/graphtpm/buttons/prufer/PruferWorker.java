package graphtpm.buttons.prufer;

import graphtpm.algorithms.IsTreeAlgorithm;
import graphtpm.algorithms.PruferAlgorithm;
import graphtpm.Starter;

import javax.swing.*;

public class PruferWorker extends SwingWorker<Void, Void> {


    protected Void doInBackground() throws Exception {
        if(IsTreeAlgorithm.isTree()){
        PruferAlgorithm.prufer();
        return null;
        }
        Starter.gp.ip.rp.output.setText("It's not a tree!");
        return null;
    }

    protected void done() {
        try {
            System.out.println("Prufer is done!");
            Starter.gp.ip.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

