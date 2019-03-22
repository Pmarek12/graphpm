package graphtpm.algorithms;

import graphtpm.Starter;
import graphtpm.panels.MouseHandler;
import graphtpm.structure.AreaPoint;
import graphtpm.structure.Edge;

import java.util.Collections;

public class CertificateAlgorithm {
    public static String certificate() throws InterruptedException {
        for (AreaPoint point : Starter.points) {
            point.certificate = "01";
        }
        Starter.threading();
        while (Starter.points.size() > 2) {
            for (AreaPoint point : Starter.points) {
                if (point.degree > 1) {
                    Starter.pointsTemp.clear();
                    for (Integer index : point.neighbours.keySet()) {
                        if (Starter.getAreaPoint(index, Starter.points).degree == 1) {
                            Starter.pointsTemp.add(Starter.getAreaPoint(index, Starter.points));
                            Starter.pointsToRemove.add(Starter.getAreaPoint(index, Starter.points)); //dodanie do usuwanych
                        }
                    }
                    for (AreaPoint point2 : Starter.pointsTemp) {
                        Starter.certificateList.add(point2.certificate);
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < point.certificate.length() - 1; i++) {
                        sb.append(point.certificate.charAt(i));
                    }
                    if (sb.length() != 0) {
                        Starter.certificateList.add(sb.toString());
                    }
                    Collections.sort(Starter.certificateList);
                    sb = new StringBuilder();
                    sb.append("0");
                    while (!Starter.certificateList.isEmpty()) {
                        sb.append(Starter.certificateList.get(0));
                        Starter.certificateList.remove(0);
                    }
                    sb.append("1");
                    point.certificate = sb.toString();
                }
            }
            for (AreaPoint point : Starter.pointsToRemove) {
                for (Edge edge : Starter.edges) {
                    if ((edge.getP1() == point) || (edge.getP2() == point)) {
                        Starter.edgesToRemove.add(edge);
                        break;
                    }
                }
                for (Edge edge : Starter.edgesToRemove) {
                    Starter.edges.remove(edge);
                    edge.getP2().removeNeighbour(edge.getP1());
                }
                Starter.edgesToRemove.clear();
                Starter.points.remove(point);
            }
            Starter.threading();
        }
        StringBuilder sb = new StringBuilder();
        for (AreaPoint point : Starter.points) {
            Starter.certificateList.add(point.certificate);
        }
        Collections.sort(Starter.certificateList);
        while (!Starter.certificateList.isEmpty()) {
            sb.append(Starter.certificateList.get(0));
            Starter.certificateList.remove(0);
        }
        MouseHandler.removeAll2();
        Starter.gp.repaint();
        return sb.toString();
    }
}
