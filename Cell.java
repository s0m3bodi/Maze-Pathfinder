class Cell{
     private boolean up, down, left, right; // true -> wall, false -> no wall
     private boolean visited; 
     private final int row, col; // position

     // CONSTRUCTORS
     public Cell(boolean up, boolean down, boolean left, boolean right, int row, int col) {
          this.up = up;
          this.down = down;
          this.left = left;
          this.right = right;
          
          this.row = row;
          this.col = col;
          visited = false;
     }

     public Cell(int row, int col) {
          up = true;
          down = true;
          left = true;
          right = true;

          this.row = row;
          this.col = col;
          visited = false; 
     }

     // GETTERS
     public int getRow(){
          return row;
     }
     public int getCol(){
          return col;
     }
     public boolean getUp(){
          return up;
     }
     public boolean getRight(){
          return right;
     }
     public boolean getDown(){
          return down;
     }
     public boolean getLeft(){
          return left;
     }
     public boolean isVisited(){
          return visited;
     }


     // SETTERS
     public void setUp(boolean up){
          this.up = up;
     }
     public void setRight(boolean right){
          this.right = right;
     }
     public void setDown(boolean down){
          this.down = down;
     }
     public void setLeft(boolean left){
          this.left = left;
     }
     public void setVisited(boolean visited){
          this.visited = visited;
     }
}