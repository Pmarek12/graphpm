package graphtpm.structure;

import graphtpm.Starter;

public class Edge {

    private AreaPoint p1,p2;

    public Edge(AreaPoint p1,AreaPoint p2){
        this.p1=p1;
        this.p2=p2;
        p1.addNeighbour(p2);
    }

    public static void addEdge(AreaPoint p) {
        for (Edge edge : Starter.edges) {
            if ((edge.getP1() == p) && (edge.getP2() == Starter.vertex)) {
                Starter.gp.repaint();
                Starter.vertex = null;
                return;
            }
        }
        Starter.edges.add(new Edge(p, Starter.vertex));
        Starter.gp.repaint();
        Starter.vertex = null;
        return;
    }

    public AreaPoint getP1() {
        return p1;
    }

    public AreaPoint getP2() {
        return p2;
    }
}

