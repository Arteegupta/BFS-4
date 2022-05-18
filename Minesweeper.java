import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
public class Solution {	 
	int[][] dirs;
    int m;
    int n;    
    /**Approach1: BFS**/	
    public char[][] updateBoard(char[][] board, int[] click) {
    	//null
        if(board == null || board.length==0) return board;
        if (board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }        
        m= board.length; n= board[0].length;
        dirs= new int[][] {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,-1},{1,1},{-1,-1}};     
        //logic
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]]= 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count= countMines(board, curr[0], curr[1]);
            if(count>0) {
                board[curr[0]][curr[1]]= (char) (count+'0');
            }else{//process the babies       
                for(int[] dir : dirs){
                    int r= curr[0] + dir[0];
                    int c= curr[1] + dir[1];
                    if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='E'){
                        q.add(new int[] {r,c});
                        board[r][c] = 'B';
                    }
                }
            }
        }         
        return board;
    }
    private int countMines(char[][] board, int i, int j){
        int count=0;
        for(int[] dir : dirs){
            int r= i + dir[0];
            int c= j + dir[1];
            if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='M') count++;
        }
        return count;
    }
    
    /**Approach2: DFS**/   
   /* public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length==0) return board;
        if (board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        m= board.length;
        n= board[0].length;
        dirs= new int[][] {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,-1},{1,1},{-1,-1}};
        
        //DFS
        dfs(board, click[0], click[1]);        
        return board;
    }
    private void dfs(char[][] board, int i, int j){
        //null
        if(i<0 || j<0 || i==m || j==n || board[i][j] != 'E') return;
        
        //logic
        board[i][j]= 'B';  
        int count= countMines(board, i, j);
        if(count>0) {
            board[i][j]= (char) (count+'0');
        }else{
            for(int[] dir : dirs){
                int r= i + dir[0];
                int c= j + dir[1];                
                dfs(board, r, c);                
            }
        }        
    }*/
    
    
	// Driver code to test above
	public static void main (String[] args) {	
		Minesweeper ob  = new Minesweeper();			
		char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
		int[] click= {3,0};				
		System.out.println("Updated board: "+Arrays.deepToString(ob.updateBoard(board, click)));
	}
}
