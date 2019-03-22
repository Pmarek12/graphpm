package graphtpm.algorithms;

import graphtpm.Starter;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;

public class PruferDrawAlgorithm {

    public static void pruferDraw() throws InterruptedException {
        if (!Starter.pruferListC.isEmpty()) {
            circle(Starter.pruferListC.size() + 2);
            Starter.pointsToRemove.addAll(Starter.points);
            int k;
            Starter.threading();
            while (!Starter.pruferListC.isEmpty()) {
                k = 0;
                while (k < Starter.pointsToRemove.size() + 1) {
                    if (!Starter.pruferListC.contains(Starter.pointsToRemove.get(k).getIndex())) {
                        break;
                    }
                    k++;
                }
                Starter.edges.add(new Edge(Starter.pointsToRemove.get(k), Starter.getAreaPoint(Starter.pruferListC.get(0), Starter.points)));
                Starter.pointsToRemove.remove(k);
                Starter.pruferListC.remove(0);
                Starter.threading();
            }
            Starter.edges.add(new Edge(Starter.pointsToRemove.get(0), Starter.pointsToRemove.get(1)));
            Starter.pointsToRemove.clear();
            Starter.gp.repaint();
        }

    }

    public static void circle(int n) {
        int x = Starter.gp.getWidth() / 2;
        int y = Starter.gp.getHeight() / 2;
        int r = y - y / 10;
        double dx;
        double dy;

        for (int k = 1; k <= n; k++) {
            dx = x + r * Math.cos((k * 2 * Math.PI) / n);
            dy = y + r * Math.sin((k * 2 * Math.PI) / n);
            Starter.n++;
            Starter.points.add(new AreaPoint((int) dx, (int) dy, Starter.n));
        }
        Starter.gp.repaint();
    }
}
