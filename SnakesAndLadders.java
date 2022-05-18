import java.util.LinkedList;
import java.util.Queue;

//Time Complexity : O(n^2)
//Space Complexity : O(n^2)
public class SnakesAndLadders {    
    /**Approach: BFS | Time O(n^2) | Space O(n^2)**/	
    public int snakesAndLadders(int[][] board) {
        //null
        if(board == null || board.length==0) return 0;        
        int m= board.length;
        int n= board[0].length;        
        int[] moves = new int[n*n]; //1D representation of board
        int i=n-1; int j=0;
        int idx=0;
        int even = 0; //To change direction of traversal        
        while(idx < n*n){
            if(board[i][j] == -1){
                moves[idx] = board[i][j];
            }else{
                moves[idx] = board[i][j] - 1; //store index for snake/ladder
            }
            idx++;
            if(even % 2 == 0){ //Traverse left to right
                j++;
                if(j == n){
                    i--; j--; even++;
                }
            }else{ //Traverse right to left
                j--;
                if(j == -1){
                    i--; j++; even++;
                }
            }
        }        
        //BFS on 1D array moves[]   
        int level= 0;
        Queue<Integer> q= new LinkedList<>();
        q.add(0);
        while(!q.isEmpty()){
            int size= q.size();
            for(int l=0; l< size; l++){
                int curr= q.poll();
                if(curr == n*n -1) return level;                
                for(int k=1; k<=6; k++){
                    int child = curr + k;
                    if(child < n*n && moves[child] != -2){
                        if(moves[child] == -1){
                            q.add(child);
                        }else{
                            q.add(moves[child]);
                        }
                        moves[child] = -2; //mark it visited
                    }
                }
            }
            level++;
        } 
        
        return -1;
    }
     
    
	// Driver code to test above
	public static void main (String[] args) {	
		SnakesAndLadders ob  = new SnakesAndLadders();			
		int[][] board = {{-1,-1,-1,-1,-1,-1},
						 {-1,-1,-1,-1,-1,-1},
						 {-1,-1,-1,-1,-1,-1},
						 {-1,35,-1,-1,13,-1},
						 {-1,-1,-1,-1,-1,-1},
						 {-1,15,-1,-1,-1,-1}};				
		System.out.println("Min number of moves to reach end of the board are: "+ob.snakesAndLadders(board));
	}
}
