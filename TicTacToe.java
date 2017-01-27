
/**
 * Assignment #4.
 * This program is a 3d tictactoe with an AI built on.
 * Authors: Jeffrey Chan (jchan40@ucsc.edu)
 *        
 */
import java.util.*;

public class TicTacToe {

	static int board[][][] = new int[4][4][4];
	static boolean gameOver = false;
	static boolean playerMove = true; // player's turn
	static final int[][][] lines = {
		{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each lev
		{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
		{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
		{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
		{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
		{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
		{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2     
		{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
		{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
		{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
		{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
		{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
		{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
		{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
		{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
		{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
		{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   cols in each lev
		{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
		{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
		{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
		{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
		{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
		{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
		{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
		{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
		{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
		{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
		{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
		{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
		{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
		{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
		{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
		{{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
		{{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
		{{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
		{{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
		{{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
		{{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
		{{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
		{{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
		{{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
		{{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
		{{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
		{{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
		{{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
		{{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
		{{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
		{{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
		{{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
		{{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
		{{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
		{{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
		{{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
		{{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
		{{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
		{{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
		{{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
		{{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
		{{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
		{{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
		{{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
		{{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
		{{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
		{{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
		{{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
		{{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
		{{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
		{{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
		{{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
		{{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
		{{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
		{{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
		{{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
		{{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
		{{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
		{{3,3,0},{2,2,1},{1,1,2},{0,0,3}}        
	};
	static int pSum, rowIsTen;
	static boolean playerWins, compWins, gameDraws;
	static ArrayList<Integer> nonDeadLines = new ArrayList<>();

	public static void main(String[] args) {
	    board[0][0][0] = 5;
	    board[0][0][1] = 5;
	    board[0][0][2] = 5;
	    board[0][1][0] = 5;
	    board[0][1][1] = 5;
	    board[0][1][2] = 5;
	    board[0][0][3] = 1;
	    board[0][1][3] = 1;
	    board[0][3][3] = 1;
		while ( gameOver == false ) { // while game is not over

			for ( int row = board.length-1 ; row >= 0 ; row-- ) {  // draw the board
				for ( int col = board[row].length-1 ; col >= 0 ; col-- ) {
					System.out.println();
					for ( int space = col; space >= 0 ; space--) {
						System.out.print(" ");
					}
					System.out.print(row + "" + col + " ");

					for ( int level = 0; level <=board[row][col].length-1 ; level ++ ) {
						if(board[row][col][level] == 0) {
							System.out.print(" _ ");
						}
						if ( board[row][col][level] == 5 ){
							System.out.print(" X ");
						} 
						if ( board[row][col][level] == 1 ){
							System.out.print(" O ");
						}
						if ( board[row][col][level] == 0 &&board[row][col][level] == 5 &&board[row][col][level] == 1 ) {
						    gameDraws = true;
						}
					}
				}

				System.out.println();
			}

			if (playerWins) {
				System.out.println("Congradulations, you win!");
				gameOver = true;
			} else if (compWins) {
				System.out.println("Computer wins again!");
				gameOver = true;
			} else if ( gameDraws ){
				System.out.println("Game Draws! Play Again!");
				gameOver = true;
			} else {
				System.out.println( "\n   0 1 2 3");
				System.out.println("Type your move as one three digit number(1rc)");

				Scanner pMove = new Scanner(System.in);

				int x = pMove.nextInt();

				int r = x/100;
				int c = x/10 % 10;
				int l = x%10;

				while( playerMove ) { // while the player can make a move
					if ( board [r][c][l] == 0 ) {
						board[r][c][l] = 5;
						playerMove = false;
					} else {
						System.out.println("Sorry that cell is occupied.");
						System.out.println("Type your move as one three digit number(1rc)");
						x = pMove.nextInt();
						r = x/100;
						c = x/10 % 10;
						l = x%10;
					}
				}

				gameLogic();
				computerMove();
			}
		}

	}
	public static boolean checkFor3() {
	    int sum = 0;
	    
	    for ( int a = 0 ; a < lines.length ; a++ ) {
	        sum = 0;
            for ( int b = 0 ; b < lines[a].length ; b++) {
                sum += board[lines[a][b][0]][lines[a][b][1]][lines[a][b][2]];
               
                
                if(sum == 3) {
                    for ( int b2 = 0 ; b2 < lines[a].length ; b2++) {
                    if ( board[lines[a][b2][0]][lines[a][b2][1]][lines[a][b2][2]] == 0 ) {
                        board[lines[a][b2][0]][lines[a][b2][1]][lines[a][b2][2]] = 1;
                        compWins = true;
                        return true;
                    }
                  
                }
            }
             
	    }
	    }   
	    return false;
	}
	public static boolean gameLogic() {
		int firstNum = 0, secNum = 0, thirdNum = 0;
		for ( int a = 0 ; a < lines.length ; a++ ) {
			pSum = 0; // reset the count after this row
			for ( int b = 0 ; b < lines[a].length ; b++) {
				firstNum = lines[a][b][0];
				secNum = lines[a][b][1];
				thirdNum = lines[a][b][2];

				if ( board[firstNum][secNum][thirdNum] == 5 ) {
					pSum += 5;
				}
				if ( board[firstNum][secNum][thirdNum] == 1 ) {
					pSum += 1;
				}

			} // one line
			
			if(checkFor3()) {
			    return true;
			} else
			if (pSum == 20 ) {
				playerWins = true;
				return true;
			} else if ( pSum == 15 && playerMove == false) { // prevents player from winning
				for ( int b = 0 ; b < lines[a].length ; b++) {
					firstNum = lines[a][b][0];
					secNum = lines[a][b][1];
					thirdNum = lines[a][b][2];
					if ( board[firstNum][secNum][thirdNum] == 0 ) {
						board[firstNum][secNum][thirdNum] = 1;
						playerMove = true;
						return false;
					}
				}
			} else if ( pSum == 2 && playerMove == false ) { // Computer creates a fork when it's possible
				rowIsTen = a;
				for ( int r = 0 ; r < lines.length ; r++ ) {
					int sum = 0;
					if( rowIsTen != r ) { // check other lines that have sum of ten
						sum = 0;
						for ( int b = 0 ; b < lines[a].length ; b++) { 
							firstNum = lines[r][b][0];
							secNum = lines[r][b][1];
							thirdNum = lines[r][b][2];

							if ( board[firstNum][secNum][thirdNum] == 1 ) { 
								sum += 1;
							}

						}

						if ( sum == 2  ) { // check if the lines intersect with each other, if so create a fork.
							for ( int e = 0 ; e < lines[r].length ; e++) {
								for ( int y = 0 ; y < lines[r].length ; y++) {
									if( Arrays.equals(lines[r][e],lines[rowIsTen][y]) && playerMove == false) {
										if ( board[lines[rowIsTen][y][0]][lines[rowIsTen][y][1]][lines[rowIsTen][y][2]] == 0 ) {
											board[lines[rowIsTen][y][0]][lines[rowIsTen][y][1]][lines[rowIsTen][y][2]] = 1;
											playerMove = true;
											return true;
										}
									}
								}
							}
						}

					}


				}
			} else if (pSum == 10 && playerMove == false ) { // Computer prevents player from creating a fork 
				rowIsTen = a;
				for ( int r = 0 ; r < lines.length ; r++ ) {
					int sum = 0;
					if( rowIsTen != r ) { // checking other lines of sum of ten
						sum = 0;
						for ( int b = 0 ; b < lines[a].length ; b++) {
							firstNum = lines[r][b][0];
							secNum = lines[r][b][1];
							thirdNum = lines[r][b][2];

							if ( board[firstNum][secNum][thirdNum] == 5 ) {
								sum += 5;
							}

						}


						if ( sum == 10 ) { // check if other lines are intersecting with the line that has sum of ten
							for ( int e = 0 ; e < lines[r].length ; e++) { 
								for ( int y = 0 ; y < lines[r].length ; y++) {
									if( Arrays.equals(lines[r][e],lines[rowIsTen][y]) && playerMove == false) { // if two lines intersects, and not occupied
										if ( board[lines[rowIsTen][y][0]][lines[rowIsTen][y][1]][lines[rowIsTen][y][2]] == 0 ) {
											board[lines[rowIsTen][y][0]][lines[rowIsTen][y][1]][lines[rowIsTen][y][2]] = 1; // blocks the fork
											playerMove = true;
											return true;
										}
									}
									
								}
							}
						}

					}


				}

			} else if ( (pSum != 1 || pSum != 2 || pSum != 3 || pSum != 5 || pSum != 10 || pSum != 15 ) && playerMove == false ) { // checks for non-dead lines
				nonDeadLines.add(a); // use array list to store non-dead line rows
			}

		}

		return false;
	}

	public static void computerMove() {

		if ( nonDeadLines.size() != 0 && playerMove == false ) { // randomly plays in a non-dead line

			int randNum = nonDeadLines.get((int)(Math.random()*(nonDeadLines.size())));

			int randNum2 = (int)(Math.random()*4);

			int y = lines[randNum][randNum2][0];
			int o = lines[randNum][randNum2][1];
			int u = lines[randNum][randNum2][2];

			while ( board[y][o][u] != 0 ) { // If randomly chosen place is occupied, keep trying to pick another random place 
				randNum2 = (int)(Math.random()*4);
				y = lines[randNum][randNum2][0];
				o = lines[randNum][randNum2][1];
				u = lines[randNum][randNum2][2];
			}
			board[y][o][u] = 1;
			nonDeadLines = new ArrayList<>();
			playerMove = true;
		} 

	}

}