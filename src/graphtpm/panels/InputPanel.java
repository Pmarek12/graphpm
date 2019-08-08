package graphtpm.panels;

import graphtpm.Starter;
import graphtpm.buttons.actions.ProceedAction;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public ResultPanel rp = new ResultPanel();
    private Action proceed= new ProceedAction("Procced");
    public JTextField input=new JTextField(10); //pole na nazwe
    private JLabel label=new JLabel("Input:");
    public  Font font= new Font("TimesRoman", Font.PLAIN, 30);

    public InputPanel() {
        this.setLayout(new FlowLayout());
        this.setOpaque(true);
        label.setFont(font);
        this.add(label);
        input.setFont(font);
        this.add(input);
        JButton proceedTemp=new JButton(proceed);
        proceedTemp.setFont(font);
        this.add(proceedTemp);
        this.add(rp,BorderLayout.SOUTH);
        this.setBackground(Color.LIGHT_GRAY);
    }

/*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Starter.gp.repaint();
    } */

    public String getInput(){
        return input.getText();
    }

}
