package graphtpm.panels;

import graphtpm.Starter;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

public class GraphPanel extends JComponent {
    public ControlPanel control = new ControlPanel();
    public InputPanel ip = new InputPanel();
    private double phi;
    private int barb;
    private Color color = new Color(214, 0, 35);
    public Cursor cursorDelete = customCursor("/graphtpm/panels/cursor_images/garbage_cursor.png"),
            cursorHand = customCursor("/graphtpm/panels/cursor_images/default_cursor.png"),
            cursorGrab = customCursor("/graphtpm/panels/cursor_images/click_cursor.png");

    public GraphPanel() {
        this.setOpaque(true);
        MouseHandler mh = new MouseHandler(this);
        this.addMouseListener(mh);
        this.addMouseMotionListener(mh);
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(10, 15, 50));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.LIGHT_GRAY);
        drawLine(g2d);
        drawOval(g2d);
        if (Starter.vertexDrag == null) {
            if (Starter.deleteB) {
                setCursor(cursorDelete);
            }
        } else {
            setCursor(cursorGrab);
        }
    }

    private void drawOval(Graphics2D g2d) {
        int x, y;
        if (Starter.vertex != null) {
            g2d.setColor(color);
            g2d.fillOval(Starter.vertex.x - ((Starter.r / 2) + 2), Starter.vertex.y - ((Starter.r / 2) + 2), Starter.r + 4, Starter.r + 4);
        }
        for (int i = 0; i < Starter.points.size(); i++) {
            AreaPoint p = Starter.points.get(i);
            x = (int) p.getX();
            y = (int) p.getY();
            g2d.setColor(p.color);
            g2d.fillOval(x - (Starter.r / 2), y -(Starter.r / 2), Starter.r, Starter.r);
            g2d.setColor(Color.black);
            drawCenteredString(g2d, p.getIndex(), p, new Font("Computer Modern", Font.ROMAN_BASELINE, 20));
            if (p.certificate.length() != 0) {
                g2d.setColor(Color.white);
                drawCenteredString(g2d, p, new Font("Computer Modern", Font.ROMAN_BASELINE, 20));
            }
        }
        if (Starter.vertex != null) {
            g2d.setColor(color);
            drawCenteredString(g2d, Starter.vertex.getIndex(), Starter.vertex, new Font("Computer Modern", Font.ROMAN_BASELINE, 20));
        }
    }

    private void drawLine(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(Starter.size));
        for (int i = 0; i < Starter.edges.size(); i++) {
            Edge e = Starter.edges.get(i);
            if (e.getP2().neighbours.get(e.getP1().getIndex()) == 2) {
                drawElipse(g2d, e.getP1(), e.getP2());
            } else {
                g2d.drawLine(e.getP1().x, e.getP1().y, e.getP2().x, e.getP2().y);
                if (Starter.directedB) {
                    //chcemy żeby strzałka była w środku lini wiec tworzymy nowy punkt jako początek strzałki
                    drawArrowHead(g2d, new Point((e.getP1().x + e.getP2().x) / 2, (e.getP1().y + e.getP2().y) / 2), e.getP2());
                }
            }
        }
    }

    private void drawArrowHead(Graphics2D g2d, Point tip, Point tail) {
        phi = Math.toRadians(40);
        barb = 20;
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        for (int j = 0; j < 2; j++) {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2d.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
    }

    private void drawElipse(Graphics2D g2d, Point p1, Point p2) {
        int ratio = (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y)) / Starter.rat;
        if (Math.signum(p1.x - p2.x) == Math.signum(p1.y - p2.y)) {
            g2d.draw(new QuadCurve2D.Float(p1.x, p1.y, (p1.x + p2.x) / 2 - ratio, (p1.y + p2.y) / 2 + ratio, p2.x, p2.y));
            g2d.draw(new QuadCurve2D.Float(p1.x, p1.y, (p1.x + p2.x) / 2 + ratio, (p1.y + p2.y) / 2 - ratio, p2.x, p2.y));
            return;
        }
        g2d.draw(new QuadCurve2D.Float(p1.x, p1.y, (p1.x + p2.x) / 2 - ratio, (p1.y + p2.y) / 2 - ratio, p2.x, p2.y));
        g2d.draw(new QuadCurve2D.Float(p1.x, p1.y, (p1.x + p2.x) / 2 + ratio, (p1.y + p2.y) / 2 + ratio, p2.x, p2.y));
    }

    public Cursor customCursor(String url) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource(url));
        Point hotspot = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(image, hotspot, "pencil");
        return cursor;
    }

    public static void drawCenteredString(Graphics2D g, int n, AreaPoint point, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = point.x - (int) (Starter.r / 2) + (Starter.r - metrics.stringWidth(Integer.toString(n))) / 2;
        int y = point.y - (int) (Starter.r / 2) + ((Starter.r - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(Integer.toString(n), x, y);
    }

    public static void drawCenteredString(Graphics2D g, AreaPoint point, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = point.x - (int) (Starter.r) + (Starter.r - metrics.stringWidth(point.certificate) / 2);
        int y = point.y - (int) (Starter.r) + ((Starter.r - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(point.certificate, x, y - Starter.r / 2);
    }

}
