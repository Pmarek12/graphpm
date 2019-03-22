package graphtpm.panels;

import graphtpm.Starter;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;

import java.awt.event.*;


public class MouseHandler extends MouseAdapter {
    private GraphPanel graphPanel;
    MouseHandler(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }
    private int x, y,korekta;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (AreaPoint p : Starter.points) {
                if (p.inArea(e.getX(), e.getY())) {
                    if (!Starter.deleteB) {
                        Starter.vertexDrag = p;
                        return;
                    }
                    for (Edge edge : Starter.edges) {
                        if ((edge.getP1() == p) || (edge.getP2() == p)) {
                            Starter.edgesToRemove.add(edge);
                            edge.getP1().removeNeighbour(edge.getP2());
                        }
                    }
                    for (Edge edge : Starter.edgesToRemove) {
                        Starter.edges.remove(edge);
                    }
                    Starter.edgesToRemove.clear();
                    Starter.clicked = false;
                    Starter.n--;
                    if ((Starter.n > 0) && (p.getIndex() != Starter.points.get(Starter.points.size() - 1).getIndex())) {
                        for (int i = p.getIndex(); Starter.points.get(Starter.points.size() - 1).getIndex() != Starter.n; i++) {
                            Starter.points.get(i).setIndex(i);
                        }
                        korekta=p.getIndex();
                        for(AreaPoint point: Starter.points)
                        {
                            for(int i = korekta; i<= Starter.n; i++){
                                if(point.neighbours.containsKey(i+1)){
                                    point.neighbours.put(i,point.neighbours.get(i+1));
                                    point.neighbours.remove(i+1);
                                }
                            }
                        }
                    }
                    Starter.points.remove(p);
                    Starter.vertex = null;
                    Starter.gp.repaint();
                    return;
                }

            }
            if(!Starter.deleteB) {
                addAreaPoint(e);
            }
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            removeAll();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            x = e.getX();
            y = e.getY();
            if (!Starter.clicked) {
                for (AreaPoint p : Starter.points) {
                    if (p.inArea(x, y)) {
                        Starter.vertex = p;
                        Starter.clicked = true;
                        Starter.gp.repaint();
                        break;
                    }
                }
            } else {
                for (AreaPoint p : Starter.points) {
                    if (p.inArea(x, y)) {
                        Starter.clicked = false;
                        if (p == Starter.vertex) {
                            Starter.vertex = null;
                            Starter.gp.repaint();
                            return;
                        }
                        if (!Starter.deleteB) {
                            Edge.addEdge(p);
                            return;
                        }
                        deleteEdge(p);
                    }

                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Starter.vertexDrag = null;
        Starter.gp.setCursor(Starter.gp.cursorHand);
        Starter.gp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!(Starter.vertexDrag == null)) {
            Starter.vertexDrag.x = e.getX();
            Starter.vertexDrag.y = e.getY();
            Starter.gp.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void addAreaPoint(MouseEvent e) {
        Starter.n++;
        Starter.points.add(new AreaPoint(e.getX(), e.getY(), Starter.n));
        Starter.gp.repaint();
    }

    public static void removeAll() {
        Starter.points.clear();
        Starter.edges.clear();
        Starter.edgesToRemove.clear();
        Starter.pointsToRemove.clear();
        Starter.pointsTemp.clear();
        Starter.vertex = null;
        Starter.gp.repaint();
        Starter.clicked = false;
        Starter.n = 0;
        Starter.gp.ip.rp.output.setText("");
        Starter.nextB = false;
    }

    public static void removeAll2() {
        Starter.points.clear();
        Starter.edges.clear();
        Starter.edgesToRemove.clear();
        Starter.pointsToRemove.clear();
        Starter.pointsTemp.clear();
        Starter.vertex = null;
        Starter.gp.repaint();
        Starter.clicked = false;
        Starter.n = 0;
        Starter.nextB = false;
    }

    private void deleteEdge(AreaPoint p) {
        if (p.neighbours.containsKey(Starter.vertex.getIndex())) {
            for (Edge edge : Starter.edges) {
                if (((edge.getP2() == Starter.vertex) && (edge.getP1() == p))){
                    Starter.edges.remove(edge);
                    p.removeNeighbour(Starter.vertex);
                    Starter.vertex = null;
                    Starter.gp.repaint();
                    return;
                }
            }
        }
        Starter.vertex = null;
        Starter.gp.repaint();
    }
}
