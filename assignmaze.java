/**	Name: Amirul Azim bin Amran
	Matric No: 2017425
	Section: 2
	Lecturer: Dr SHARYAR WANI

	References and sources:
	1) https://www.javatpoint.com/java-stack
	2) https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Node.html
	3) https://www.youtube.com/watch?v=kOsPq-vhWwo
	4) https://www.youtube.com/watch?v=llMIT6No7N8
	5) https://www.youtube.com/watch?v=Y37-gB83HKE
	6) https://www.youtube.com/watch?v=SqqOB2HgGsM&t=165s
**/
 
package assignment;
import java.util.Random;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
//choose start point
//each cell four wall
//one wall randomly chosen, carve package trough adjacent only if it hasnt been visited
//new cell become current cell.

public class assignmaze 
{
	public static void main(String[] args) 
	{
		Maze maze1 = new Maze(30);
        maze1.generateMaze();
        System.out.println("Maze\n" + maze1.getMaze());
        System.out.println("Binary Maze/ Dry Run\n" + maze1.getBinaryMaze());
	}

}

class Node 
{
    public final int x;
    public final int y;

    Node(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }
}

class Maze 
{
    
    private Stack<Node> stack1 = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int coordinate;

    Maze(int size) 
    {
    	maze = new int[size][size];
        coordinate = size;
    }

    public void generateMaze() 
    {
        stack1.push(new Node(0,0));
        while (!stack1.empty()) 
        {
            Node next = stack1.pop();
            if (nodeCheck(next)) 
            {
                maze[next.y][next.x] = 1;
                ArrayList<Node> neighbourCell = findNeighbourCells(next);
                nodeAddToStack(neighbourCell);
            }
        }
    }

    public String getMaze() 
    {
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < coordinate; i++) 
        {
            for (int j = 0; j < coordinate; j++) 
            {
            	/**if (maze[i][j] == 1)
            	{
            		stringBuilder1.append(maze[i][j] = "+");
            	}
            	else
            	{
            		stringBuilder1.append(maze[i][j] = " ");
            	}**/
            	stringBuilder1.append(maze[i][j] == 1 ? "+" : " ");
            	stringBuilder1.append("  "); 
            }
            stringBuilder1.append("\n");
        }
        return stringBuilder1.toString();
    }
    
    public String getBinaryMaze() 
    {
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int[] row : maze) 
        {
        	stringBuilder1.append(Arrays.toString(row) + "\n");
        }
        return stringBuilder1.toString();
    }
    
    private Boolean notNodePoint(Node node, int x, int y) 
    {
        return !(x == node.x && y == node.y);
    }

    private Boolean notCornerPoint(Node node, int x, int y) 
    {
        return (x == node.x || y == node.y);
    }
    
    private Boolean pointOnGrid(int x, int y) 
    {
        return x >= 0 && y >= 0 && x < coordinate && y < coordinate;
    }

    private boolean nodeCheck(Node node) 
    {
        int cellCount = 0;
        for (int y = node.y-1; y < node.y+2; y++) 
        {
            for (int x = node.x-1; x < node.x+2; x++) 
            {
                if (pointOnGrid(x, y) && notNodePoint(node, x, y) && maze[y][x] == 1) 
                {
                    cellCount++;
                }
            }
        }
        return (cellCount < 3) && maze[node.y][node.x] != 1;
    }

    private ArrayList<Node> findNeighbourCells(Node node) 
    {
        ArrayList<Node> neighbourCell = new ArrayList<>();
        for (int y = node.y-1; y < node.y+2; y++) {
            for (int x = node.x-1; x < node.x+2; x++) 
            {
                if (pointOnGrid(x, y) && notCornerPoint(node, x, y) && notNodePoint(node, x, y)) 
                {
                    neighbourCell.add(new Node(x, y));
                }
            }
        }
        return neighbourCell;
    }
    
    private void nodeAddToStack(ArrayList<Node> nodes) 
    {
        int point;
        while (!nodes.isEmpty()) 
        {
            point = rand.nextInt(nodes.size());
            stack1.push(nodes.remove(point));
        }
    }

}

