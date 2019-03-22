package graphtpm.structure;

import graphtpm.Starter;

import java.awt.*;
import java.util.HashMap;

public class AreaPoint extends Point {
    private int index;
    public Color color;
    public HashMap<Integer,Integer> neighbours = new HashMap<>();
    public String certificate="";
    public int degree;
    public boolean visited=false;

    public AreaPoint(int x, int y,int index) {
        super(x, y);
        this.index=index;
        degree=0;
        this.color=new Color(Math.round(100+ Starter.rnd.nextFloat()*125),Math.round(100+ Starter.rnd.nextFloat()*125),Math.round(100+ Starter.rnd.nextFloat()*125));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public boolean inArea(int x2,int y2){
        if (Math.sqrt(Math.pow(x2 - x,2) + Math.pow(y2 - y,2)) <= Starter.r/2)
            return true;
    return false;
    }

    public void addNeighbour(AreaPoint neighbour){
        if(this.neighbours.containsKey(neighbour.getIndex())){
            this.neighbours.replace(neighbour.getIndex(),2);
            neighbour.neighbours.replace(this.getIndex(),2);
        }
        else{
            this.neighbours.put(neighbour.getIndex(),1);
            neighbour.neighbours.put(this.getIndex(),1);
            this.degree++;
            neighbour.degree++;
        }
    }
    public void removeNeighbour(AreaPoint neighbour){
        if(this.neighbours.get(neighbour.getIndex())==2){
            this.neighbours.replace(neighbour.getIndex(),1);
            neighbour.neighbours.replace(this.getIndex(),1);
        }
        else{
            this.neighbours.remove(neighbour.getIndex());
            neighbour.neighbours.remove(this.getIndex());
            this.degree--;
            neighbour.degree--;
        }
    }

}
