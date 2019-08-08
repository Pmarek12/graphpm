package graphtpm.buttons.dance;
import graphtpm.Starter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DanceWorker extends SwingWorker<Void, Void> {


    protected Void doInBackground() throws Exception {
        Random generator = new Random();
        while(Starter.animatingB){
            for (Point p : Starter.points) {
                p.translate(generator.nextInt(20)-10,generator.nextInt(20)-10);
                Starter.threading();
            }
            Starter.gp.repaint();
        }
        return null;
    }


    protected void done() {
        try {
            System.out.println("Certificate is done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}