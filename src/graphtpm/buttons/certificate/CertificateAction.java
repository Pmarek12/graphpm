package graphtpm.buttons.certificate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CertificateAction extends AbstractAction {
    public CertificateAction(String name){ super(name);}

    public void actionPerformed(ActionEvent e) {
        new CertificateWorker().execute();
    }
}