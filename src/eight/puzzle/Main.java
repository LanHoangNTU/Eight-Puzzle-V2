/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.puzzle;


import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

/**
 *
 * @author A
 */
public class Main {
    static List<State> nodes = new ArrayList<>();
    static List<State> history = new ArrayList<>();
    
    private static State filter(List<State> children){
        return children.stream()
                .min(Comparator.comparingInt(State::heuristic))
                .get();
    }
    
    private static boolean isValid(int x, int y, State subject){
        int m = subject.getMatrix().length;
        int n = subject.getMatrix()[0].length;
        
        return (x <= m - 1 && x >= 0 && y <= n - 1 && y >= 0);
    }
    
    private static void print(State state){
        for (int[] is : state.getMatrix()) {
            for (int i : is) {
                System.out.print(" " + i);
            }
            System.out.println("");
        }
        
        System.out.println("");
    }
    
    private static void printPath(State root){
        if (root == null) {
            return;
        }
        
        print(root);
        printPath(root.getParent());
    }
    
    private static boolean checkHistory(List<State> history, State child){
        for (State state : history) {
            if (true) {
                if (child.compare(state)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void solve(State in, State out){
        in.setLevel(0);
        history.add(in);
        nodes.add(in);
        
        int[] posR = {1, 0, -1, 0};
        int[] posC = {0, -1, 0, 1};
        boolean solved = false;
        while(!solved){
            State min = filter(nodes);
            
            if (min.cost(out.getMatrix()) == 0) {
                printPath(min);
                solved = true;
            }
            else{
                nodes.remove(min);
                for (int i = 0; i < 4; i++) {
                    int x = min.getX();
                    int y = min.getY();
                    if (isValid(x + posR[i], y + posC[i], min)) {
                        State child = min.clone(x, y, x + posR[i], y + posC[i], min.getLevel() + 1);
                        child.setParent(min);
                        child.setCost(child.cost(out.getMatrix()));
                        
                        if (!checkHistory(history, child)) {
                            history.add(child);
                            //System.out.println("H: " + child.heuristic());
                            //print(child);
                            nodes.add(child);
                        }
                    }
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] in = {
            {5, 2, 1}, 
            {0, 3, 4}, 
            {8, 7, 6} 
        };
        
        int[][] de = {
            {1, 2, 3}, 
            {4, 5, 6}, 
            {7, 8, 0} 
        };
        
        State initial = new State(in);
        initial.setLevel(0);
        State desired = new State(de);
        solve(initial, desired);
    }
    
}
