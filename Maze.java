import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;


public class Maze {
    private final Cell[][] grid;
    private final int rows, cols;
    private final Random random = new Random();

    // 0 = up, 1 = down, 2 = left, 3 = right
    private static final int[] D_ROW = {-1, 1, 0, 0};
    private  static final int[] D_COL = {0, 0, -1, 1};
    
    // Constructor
    public Maze(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int row = 0 ; row < rows ; row++){
            for (int col = 0 ; col < cols ; col++){
                grid[row][col] = new Cell(row, col); // fully walled & unvisited
            }
        }
    }


    // GETTERS
    public Cell getCell(int row, int col) { return grid[row][col]; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }



    public void generate() {
        Deque<Cell> stack = new ArrayDeque<>();

        Cell start = grid[0][0];
        start.setVisited(true);
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();

            // Possible unvisited cell directions
            ArrayList<Integer> options = new ArrayList<>();
            for (int d = 0 ; d < 4 ; d++) {
                int nextRow = current.getRow() + D_ROW[d];
                int nextCol = current.getCol() + D_COL[d];

                if (nextRow >= 0 && nextRow < rows && nextCol >=0 && nextCol < cols
                && !grid[nextRow][nextCol].isVisited()) {
                    options.add(d);
                }
            }

            // if all neighbours have been visited, go back
            if (options.isEmpty()) {
                stack.pop();
            } else { // else pick a random neighbour to visit
                int d = options.get(random.nextInt(options.size()));
                Cell next = grid[current.getRow()+D_ROW[d]][current.getCol()+D_COL[d]];
                removeWall(current, next, d);
                next.setVisited(true);
                stack.push(next);
            }
        }

        // Entrance & Exit
        grid[0][0].setUp(false);
        grid[rows-1][cols-1].setRight(false);

        // Reseting visited flags
        for (int r = 0 ; r < rows ; r++){
            for (int c = 0 ; c < cols ; c++){
                grid[r][c].setVisited(false);
            }
        }
    }
    
    // Removes walls on both sides
    private void removeWall (Cell a, Cell b, int d){
        switch (d) {
            case 0: a.setUp(false); b.setDown(false);   break;
            case 1: a.setDown(false);  b.setUp(false);  break;
            case 2: a.setLeft(false);  b.setRight(false);   break;
            case 3: a.setRight(false); b.setLeft(false);    break;
        }
    }

    // quick ASCII view for testing
    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < cols; c++){
            if (!grid[0][c].getUp()) {
                sb.append("+  ");
            } else sb.append("+--");
        }
        sb.append("+\n");
        for (int r = 0; r < rows; r++) {
            StringBuilder mid = new StringBuilder("|");
            StringBuilder bot = new StringBuilder("+");
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];
                mid.append("  ").append(cell.getRight() ? "|" : " ");
                bot.append(cell.getDown() ? "--" : "  ").append("+");
            }
            sb.append(mid).append("\n").append(bot).append("\n");
        }
        System.out.print(sb);
    }

}
