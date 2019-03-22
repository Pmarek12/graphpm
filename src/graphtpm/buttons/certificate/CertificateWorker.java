package graphtpm.buttons.certificate;

import graphtpm.algorithms.CertificateAlgorithm;
import graphtpm.Starter;
import graphtpm.algorithms.IsTreeAlgorithm;

import javax.swing.*;

public class CertificateWorker extends SwingWorker<Void, Void> {


    protected Void doInBackground() throws Exception {
        if(IsTreeAlgorithm.isTree()){
            Starter.gp.ip.rp.output.setText("");
            Starter.gp.ip.rp.output.setText(CertificateAlgorithm.certificate());
            return null;
        }
        Starter.gp.ip.rp.output.setText("It's not a tree!");
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