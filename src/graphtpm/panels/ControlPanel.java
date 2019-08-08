package graphtpm.panels;

import graphtpm.Starter;
import graphtpm.buttons.actions.DeleteAction;
import graphtpm.buttons.actions.MultigrafAction;
import graphtpm.buttons.actions.NextAction;
import graphtpm.buttons.certificate.CertificateAction;
import graphtpm.buttons.dance.DanceAction;
import graphtpm.buttons.prufer.PruferAction;
import graphtpm.buttons.pruferdraw.PruferDrawAction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JToolBar {
    private Action delete = new DeleteAction("Delete");
    private Action multigraf = new MultigrafAction("Add");
    private Action prufer = new PruferAction("Prufer");
    private Action pruferDraw = new PruferDrawAction("Prufer Draw");
    private Action certificate= new CertificateAction("Certificate");
    private Action next = new NextAction("Next");
    private Action dance = new DanceAction("Dance");

    ControlPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);
        this.add(new JButton(delete));
        this.add(new JButton(multigraf));
        this.add(new JButton(prufer));
        this.add(new JButton(pruferDraw));
        this.add(new JButton(certificate));
        this.add(new JButton(next));
        this.add(new JButton(dance));
        JSpinner js = new JSpinner();
        js.setModel(new SpinnerNumberModel(2000, 10, 10000, 10));
        js.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                Starter.speed = (Integer) s.getValue();

            }
        });

        this.add(new JLabel("Speed:"));
        this.add(js);
        JSpinner js2 = new JSpinner();
        js2.setModel(new SpinnerNumberModel(20, 1, 100, 3));
        js2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                Starter.rat = (Integer) s.getValue();
                Starter.gp.repaint();
            }
        });

        JCheckBox jcb2 = new JCheckBox("Animating");
        jcb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcb2.isSelected()) {
                    Starter.animatingB= true;

                }
                if (!jcb2.isSelected()) {
                    Starter.animatingB= false;
                }
            }
        });
        this.add(jcb2);
        this.add(new JLabel("Curvature:"));
        this.add(js2);
        JCheckBox jcb = new JCheckBox("Directed");
        jcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcb.isSelected()) {
                    Starter.directedB= true;

                }
                if (!jcb.isSelected()) {
                    Starter.directedB= false;
                }
                repaint();
                Starter.gp.repaint();
            }
        });
        this.add(jcb);
    }
}