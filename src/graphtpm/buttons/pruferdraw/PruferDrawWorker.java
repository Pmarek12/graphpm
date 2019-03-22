package graphtpm.buttons.pruferdraw;

import graphtpm.algorithms.PruferDrawAlgorithm;
import graphtpm.Starter;
import graphtpm.panels.MouseHandler;

import javax.swing.*;

public class PruferDrawWorker extends SwingWorker<Void, Void> {

    protected Void doInBackground() throws Exception {
        MouseHandler.removeAll();
        for (Integer i : Starter.pruferListC) {
            if (i > Starter.pruferListC.size() + 2) {
                Starter.gp.ip.rp.output.setText("Numbers can by maximally n+2");
                return null;
            }
        }
        if(Starter.input){
            PruferDrawAlgorithm.pruferDraw();
        }
        return null;
    }

    protected void done() {
        try {
            System.out.println("PruferDraw is done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}