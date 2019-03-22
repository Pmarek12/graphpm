package graphtpm.algorithms;

import graphtpm.Starter;
import graphtpm.structure.AreaPoint;


public class  IsTreeAlgorithm {

    public static boolean isTree(){
        if(Starter.points.isEmpty()){
            return false;
        }
        if(Starter.points.size()==1){
            return true;
        }
        for(AreaPoint point: Starter.points){
            if(point.neighbours.values().contains(2)){
                return false;
            }
        }
        AreaPoint father=new AreaPoint(0,0,0),son=Starter.points.get(1);
        if(!Visited(father,son)) {
            for(AreaPoint p: Starter.points){
                p.visited=false;
            }
            return false;
        }
        for(AreaPoint point: Starter.points){
            if(!point.visited) {
                for (AreaPoint p : Starter.points) {
                    p.visited = false;
                }
                return false;
            }
        }
        for(AreaPoint p: Starter.points){
            p.visited=false;
        }
        return true;
    }

    private static boolean Visited(AreaPoint father,AreaPoint son){
        son.visited=true;
        AreaPoint temp;
        for(int index: son.neighbours.keySet()){
            temp=Starter.getAreaPoint(index,Starter.points);
            if(temp!=father) {
                if (temp.visited) {
                    return false;
                }
                if (!Visited(son,temp))
                    return false;
            }
        }
        return true;
    }

}
