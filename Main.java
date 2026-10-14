import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter num Rows: ");
        int rows = sc.nextInt();

        System.out.println("Enter num Cols: ");
        int cols = sc.nextInt();

        Maze maze = new Maze(rows, cols);
        maze.generate();
        maze.print();


        sc.close();
    }
}