package graphtpm;

import graphtpm.panels.GraphPanel;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Starter {
    public static GraphPanel gp = new GraphPanel();
    public static int n = 0, r = 32, size = 1, speed = 2000, rat = 25;
    public static Random rnd = new Random();
    public static AreaPoint vertex, vertexDrag;
    public static volatile boolean nextB = false;
    public static boolean deleteB = false, directedB = false, clicked = false, animatingB = false, input=false;
    public static Vector<Integer> pruferList = new Vector<>();
    public static Vector<Integer> pruferListC = new Vector<>();
    public static Vector<Edge> edgesToRemove = new Vector<>();
    public static Vector<Edge> edges = new Vector<Edge>();
    public static Vector<AreaPoint> points = new Vector<>();
    public static Vector<AreaPoint> pointsToRemove = new Vector<>();
    public static Vector<AreaPoint> pointsTemp = new Vector<>();
    public static Vector<String> certificateList = new Vector<>();


    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("GraphPM");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.add(gp.control, BorderLayout.NORTH);
                f.add(new JScrollPane(gp), BorderLayout.CENTER);
                f.add(gp.ip, BorderLayout.SOUTH);
                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);
                f.setCursor(gp.cursorHand);
            }
        });
    }


    public static void threading() throws InterruptedException {
        if (animatingB) {
            Thread.sleep(speed);
        } else {
            while (!nextB) {
                Thread.sleep(200);
            }
        }
        gp.repaint();
        nextB = false;
    }

    public static AreaPoint getAreaPoint(int index, Vector<AreaPoint> pkt) {
        for (AreaPoint point : pkt) {
            if (point.getIndex() == index) {
                return point;
            }
        }
        return null;
    }
}