package graphtpm.algorithms;

import graphtpm.Starter;
import graphtpm.panels.MouseHandler;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;

public class PruferAlgorithm {
    public static void prufer() throws InterruptedException {
        AreaPoint vTemp2 = new AreaPoint(0, 0, Starter.n + 1);
        vTemp2.degree = Starter.n;
        AreaPoint vTemp1 = vTemp2;
        Starter.threading();
        while (Starter.edges.size() > 1) {
            for (AreaPoint point : Starter.points) {
                if ((point.degree == 1) && (vTemp1.getIndex() > point.getIndex())) {
                    vTemp1 = point;
                }
            }
            if (vTemp1.degree != Starter.n) {
                Starter.pruferList.addAll(vTemp1.neighbours.keySet());
                StringBuilder sb = new StringBuilder();
                for (Integer s : Starter.pruferList) {
                    sb.append(Integer.toString(s));
                    sb.append(" ");
                }
                Starter.gp.ip.rp.output.setText(sb.toString());
                for (Edge edge : Starter.edges) {
                    if ((edge.getP2() == vTemp1) || (edge.getP1() == vTemp1)) {
                        Starter.edgesToRemove.add(edge);
                        break;
                    }
                }
                Starter.edgesToRemove.get(0).getP1().removeNeighbour(Starter.edgesToRemove.get(0).getP2());
                Starter.edges.remove(Starter.edgesToRemove.get(0));
                Starter.edgesToRemove.clear();
                vTemp1 = vTemp2;
            }
            Starter.threading();
        }
        MouseHandler.removeAll2();
        System.out.println(Starter.pruferList);
        Starter.pruferList.clear();
    }
}
