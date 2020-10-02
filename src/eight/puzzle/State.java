/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.puzzle;

/**
 *
 * @author A
 */
public class State {
    private State parent;   //Parent state
    private int[][] matrix; //The matrix
    private int x, y;       //Blank tile's coordinates
    private int cost;       //Number of misplaced tiles
    private int level;      //Number of moves

    public State() {
    }

    public State(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    this.x = i;
                    this.y = j;
                    break;
                }
            }
        }
    }
    
    public State clone(int x, int y, int newX, int newY, int level){
        State state = new State();
        
        //Copies datas from this State to the new State
        state.matrix = cloneMatrix();
        state.level = level;
        
        //Move tile by 1
        int temp = state.getMatrix(x, y);
        state.setMatrix(x, y, state.getMatrix(newX, newY));
        state.setMatrix(newX, newY, temp);
        
        //Update new blank tile coordinate
        state.setX(newX);
        state.setY(newY);
        
        return state;
    }
    
    public int cost(int desiredMatrix[][]){
        int m = this.matrix.length;
        int n = this.matrix[0].length;
        
        int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.matrix[i][j] != 0 && this.matrix[i][j] != desiredMatrix[i][j]) {
                        count += 1;
                    }
                }
            }
            
        return count;
    }
    
    public int[][] cloneMatrix(){
        int m = this.matrix.length;
        int n = this.matrix[0].length;
        int[][] clone = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = matrix[i][j];
            }
        }
        
        return clone;
    }
    
    public boolean compare(State state){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != state.getMatrix(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public int heuristic(){
        return cost + level;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public int[][] getMatrix() {
        return matrix;
    }
    
    public int getMatrix(int x, int y) {
        return matrix[x][y];
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public void setMatrix(int x, int y, int item) {
        this.matrix[x][y] = item;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}
