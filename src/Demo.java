import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Demo extends JPanel {
    // SCREEN SETTINGS
    final int maxColumns = 20;
    final int maxRows = 20;
    final int nodeSize = 40; // PIXELS.
    final int screenWidth = nodeSize * maxColumns;
    final int screenHeight = nodeSize * maxRows;

    // NODE, col = x axis | row = y axis
    Node[][] node = new Node[maxColumns][maxRows];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    boolean goalReached = false;
    int step = 0;

    public Demo() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRows, maxColumns));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        // PLACE NODES
        int col = 0;
        int row = 0;

        while (col < maxColumns && row < maxRows) {
            node[col][row] = new Node(col, row, this); // Pass 'this' to Node
            this.add(node[col][row]);
            col++;
            if (col == maxColumns) {
                col = 0;
                row++;
            }
        }

        // col = x ; row = y ; starting from top left corner (0,0)
        // SET START AND GOAL NODE
        setStartNode(0, 19);
        setGoalNode(19, 0);
    }
    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }
    public void autoSearch() {
        goalReached = false;
        step = 0;
        for (int i = 0; i < node.length; i++) {
            Node[] nodes = node[i];
            for (int j = 0; j < nodes.length; j++) {
                Node n = nodes[j];
                n.reset();
            }
        }
        openList.clear();
        checkedList.clear();
        currentNode = startNode;

        while (!goalReached && step < 9999) {
            int col = currentNode.col;
            int row = currentNode.row;

//            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN UP NODE
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // OPEN DOWN NODE
            if (row + 1 < maxRows) {
                openNode(node[col][row + 1]);
            }
            // OPEN LEFT NODE
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // OPEN RIGHT NODE
            if (col + 1 < maxColumns) {
                openNode(node[col + 1][row]);
            }
            // OPEN UP-RIGHT DIAGONAL NODE
            if (row - 1 >= 0 && col + 1 < maxColumns) {
                openNode(node[col + 1][row - 1]);
            }
            // OPEN DOWN-RIGHT DIAGONAL NODE
            if (row + 1 < maxRows && col + 1 < maxColumns) {
                openNode(node[col + 1][row + 1]);
            }
            // OPEN UP-LEFT DIAGONAL NODE
            if (row - 1 >= 0 && col - 1 >= 0) {
                openNode(node[col - 1][row - 1]);
            }
            // OPEN DOWN-LEFT DIAGONAL NODE
            if (row + 1 < maxRows && col - 1 >= 0) {
                openNode(node[col - 1][row + 1]);
            }

            // FIND BEST NODE (most optimal!)
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If F cost is equal, check G cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After loop, get best node
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            step++;
        }
        System.out.println("Nodes checked: "+step);
    }
    public void manualSearch() {
        if (!goalReached) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN UP NODE
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // OPEN DOWN NODE
            if (row + 1 < maxRows) {
                openNode(node[col][row + 1]);
            }
            // OPEN LEFT NODE
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // OPEN RIGHT NODE
            if (col + 1 < maxColumns) {
                openNode(node[col + 1][row]);
            }
            // OPEN UP-RIGHT DIAGONAL NODE
            if (row - 1 >= 0 && col + 1 < maxColumns) {
                openNode(node[col + 1][row - 1]);
            }
            // OPEN DOWN-RIGHT DIAGONAL NODE
            if (row + 1 < maxRows && col + 1 < maxColumns) {
                openNode(node[col + 1][row + 1]);
            }
            // OPEN UP-LEFT DIAGONAL NODE
            if (row - 1 >= 0 && col - 1 >= 0) {
                openNode(node[col - 1][row - 1]);
            }
            // OPEN DOWN-LEFT DIAGONAL NODE
            if (row + 1 < maxRows && col - 1 >= 0) {
                openNode(node[col - 1][row + 1]);
            }

            // FIND BEST NODE (most optimal!)
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If F cost is equal, check G cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After loop, get best node
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
        }
    }
    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            // If node is not opened yet, add it to the open list
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    private void trackPath() {
        // Backtrack and draw best path
        Node current = goalNode;
        while (current != startNode) {
            current = current.parent;
            if (current != startNode) {
                current.setAsPath();
            }
        }
    }
}
