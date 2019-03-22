package graphtpm.buttons.actions;

import graphtpm.Starter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProceedAction extends  AbstractAction{

        public ProceedAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            if(!Starter.gp.ip.getInput().equals("")){
                String array[] = Starter.gp.ip.getInput().split(" ");
                Starter.pruferListC.clear();
                for (String string : array) {
                    try {
                        Starter.pruferListC.add(Integer.parseInt(string));
                    }catch (NumberFormatException exeption){
                        Starter.gp.ip.rp.output.setText("Wrong input!");
                        Starter.input=false;
                        return;
                    }
                }
                Starter.gp.ip.rp.output.setText("Choose algorithm");
                Starter.input=true;
            }
        }
}
