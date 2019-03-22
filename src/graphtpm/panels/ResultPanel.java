package graphtpm.panels;

import graphtpm.Starter;
import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JLabel result=new JLabel("Result:");
    public JLabel output= new JLabel("");
    public  Font font= new Font("TimesRoman", Font.PLAIN, 30);

    public ResultPanel() {
        this.setLayout(new FlowLayout());
        this.setOpaque(true);
        result.setFont(font);
        this.add(result);
        output.setFont(font);
        this.add(output);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Starter.gp.ip.repaint();
    }

}

