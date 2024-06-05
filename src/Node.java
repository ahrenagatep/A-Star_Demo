import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Node extends JButton implements ActionListener{
    Color customRed = new Color(244, 91, 105);
    Color customBlue = new Color(45, 125, 210);
    Color customGreen = new Color(98, 195, 112);
    Color customYellow = new Color(250, 169, 22);
    Color customWhite = new Color(251, 255, 254);
    Color customBlack = new Color(25, 25, 25);
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

        setBackground(customWhite);
        setForeground(Color.black);
        addActionListener(this);
    }
    public void setAsStart(){
        setBackground(customGreen);
        setForeground(Color.white);
//        setText("START");
        start = true;
    }
    public void setAsGoal(){
        setBackground(customRed);
        setForeground(Color.black);
        goal = true;
    }
    public void setAsSolid(){
        setBackground(customBlack);
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
        setBackground(customBlue);
        setForeground(Color.black);
    }
    public void paintAsOpen(){
        setBackground(customWhite);
        setForeground(Color.black);
        open = true;
    }
    public void paintAsComputer(){
        setBackground(customYellow);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (!solid){
            setAsSolid();
            solid = true;
            demo.autoSearch();
        }else{
            paintAsOpen();
            solid = false;
            demo.autoSearch();
        }
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
