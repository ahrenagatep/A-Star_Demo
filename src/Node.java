import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Node extends JButton implements ActionListener{
    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;
    Demo demo;
    public Node(int col, int row, Demo demo){
        this.col = col;
        this.row = row;
        this.demo = demo;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }
    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
//        setText("START");
        start = true;
    }
    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
//        setText("GOAL");
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
    public void setAsChecked(){
        if(start == false && goal == false){
            setBackground(Color.orange);
        }
    }
    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        setAsSolid();
        demo.requestFocusInWindow();
    }
    public void reset() {
        parent = null;
        gCost = 0;
        hCost = 0;
        fCost = 0;
        open = false;
        checked = false;
        if (!solid && !goal && !start) {
            setBackground(Color.white);
            setForeground(Color.black);
            setText("");
        }
    }
}
