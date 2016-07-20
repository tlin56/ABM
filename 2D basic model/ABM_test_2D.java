//A 2D agent based model, every round the program monitors the amount of substrate, enzyme-substrate complex, enzyme, and product on the grid. 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;


public class ABM_test_2D{

	public static void main(String[] args) throws IOException{

//First define inputs for the main. Basically three input flags/arguments. First, filename. Second, the number of substrate that one wants to start with.
//And Third, the number of rounds you wish to run.
	
		File file = new File(args[0]);
		int maxsub = Integer.parseInt(args[1]);
		int maxround = Integer.parseInt(args[2]);
    
//First create the 2D matrix for Enzyme (single enzyme in this case), substrate, and Intermediate. The reason I use multiple matrix to store different types of
//particle is simply becasue it is easier to monitor the change, plus I allow particles to overlap with each other. Also noticed that I did not create a matrix for
// the product. That is becasue there is no need to store the location of the product. I only need to count and store how many product were created each round if necessary.
	    
        char [][] EnzymeOne = new char [100][100];
        char [][] Substrates = new char [100][100];
        char [][] IM = new char [100][100];

//First assign each location in the matrix/grid 'O', to represent that it is blank. Basically set all the grids to blank/zero initially.		
		
        for(int i = 0; i < 100; i++){
        
            for(int j = 0; j < 100; j++){
            
                EnzymeOne[i][j] = 'O';
                IM[i][j] = 'O';
                Substrates[i][j] = 'O';
                
            }
            
        }
        
//Here we are putting 100 enzymes (you can change that of course) into random places in the grid.
		
        int spot_count_one = 0;

        while(spot_count_one < 100){
        
        	int i = (int)(Math.random()*100);
        	int j = (int)(Math.random()*100);
        	if(EnzymeOne[i][j] == 'O'){
        	
        		EnzymeOne[i][j] = 'X';
        		spot_count_one++;
        	
        	}
        
		}
		
	
	FileWriter fw = null;
	BufferedWriter bw = null;
	PrintWriter pw = null;
	
	try{
		
		fw = new FileWriter(file, true);
		bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		
			
//Here I randomly put certain number of substrate into the "substrate" grid.
			
			int spot_count_three = 0;

        while(spot_count_three < maxsub){
        	
        	int i = (int)(Math.random()*100);
        	int j = (int)(Math.random()*100);
        	if(Substrates[i][j] == 'O'){
        
        		Substrates[i][j] = 'X';
        		spot_count_three++;
        
        	}
        	
        }
		
//Baically, each round I would move everything by one step (all directions have equal change of being chosen).
//And for every round, I would record the number of substrate cahnge, the number of enzyme change, the number of enzyme compelx change and etc. You can obvisouly
//what to record and what not to record.
		
		int counter = 0;
		for(int round = 0; round < maxround; round++){
			
			int I = 0;
			int count_P = 0;
			int count_S = 0;
			int count_E = 0;
			int count_ES = 0;
			int count_C = 0;
			moveboard(Substrates, 0.88889);
			moveboard(IM, 0.88889);
			moveboard(EnzymeOne, 0.88889);
			
			for(int i = 0; i < 100; i++){
				for(int j = 0; j < 100; j++){
					
					double random_kf = Math.random();
					double random_kcat = Math.random();
					double random_kr = Math.random();
					
					if(Substrates[i][j] == 'X'){
						count_S++;
					}
					if(IM[i][j] == 'X'){
						count_P++;
					}
					if(EnzymeOne[i][j] == 'X'){
						count_E++;
					}
					if(EnzymeOne[i][j] == 'C'){
						count_ES++;
					}
					if(Substrates[i][j] == EnzymeOne[i][j] && Substrates[i][j] == 'X'){
						Substrates[i][j] = 'O';
						EnzymeOne[i][j] = 'C';
						count_C++;
					}
					if(EnzymeOne[i][j] == 'C' && random_kcat*1001 >= 40){
						EnzymeOne[i][j] = 'X';
						IM[i][j] = 'X';
						I++;
					}
					if(EnzymeOne[i][j] == 'C' && random_kr*1001 <= 20){
						Substrates[i][j] = 'X';
						EnzymeOne[i][j] = 'X';
					}
					
				}
			}
			counter = counter + count_C;
			//if(round == 10){
				
				//int xone = round;
				//int yone = count_I*100;
				//System.out.print(round);
				//System.out.print(",");
				//System.out.println(count_I);
				
				
			//}
			if(count_C != 0){
				
				pw.print(round);
				pw.print(',');
				//pw.print(count_P);
				//pw.print(',');
				//pw.print(count_S);
				//pw.print(',');
				//pw.print(count_E);
				//pw.print(',');
				pw.println(counter);
				pw.flush();
				//System.out.println();
				//System.out.println();
				//System.out.println();
				//System.out.println();
				
			
			}
			if(allO(Substrates)){
				
				break;
				
			}
				
			}
		
		
	
	}finally{
		
		try{
			
			pw.close();
			bw.close();
			fw.close();
			
		}catch(IOException io){}
		
	}
		
	}
	
	
 	public static void reset(char[][] IM, char[][] Substrates, char[][] IM2){
	
		for(int i = 0; i < 500; i++){
		
		    for(int j = 0; j < 500; j++){
		    
			IM[i][j] = 'O';
			Substrates[i][j] = 'O';
			IM2[i][j] = 'O';
			
		    }
		    
		}
	
	}
	
	
	
	/**
	* Given a tissue sample, prints the cell make up in grid form
	*
	* @param tissue a 2-D character arracol representing a tissue sample
	* 
	***/
	
	public static boolean allO(char[][] tissue){
			
	for(int row = 0; row < tissue.length; row++ ){
        
            for(int col = 0; col < tissue[0].length; col++){
            	    
            	    if(tissue[row][col] == 'X'){
            	    
            	    	    return false;
            	    
            	    }
            	    
            }
        }
        
        return true;
	
	}
	
	public static void printTissue(char[][] tissue){
		
        for(int row = 0; row < tissue.length; row++ ){
        
            for(int col = 0; col < tissue[0].length; col++){
            
                System.out.print("'" + tissue[row][col] + "' ");		//Line 30-44 simplcol prints out the element in the 2D arracol
            
            }
        
            System.out.println();
        
        }
	
	} 

		
	

	
	/**
	* Given a tissue sample, and a (row,col) inderow into the arracol, determines if the agent at that location is satisfied.
	* Note: Blank cells are alwacols satisfied (as there is no agent)
	*
	* @param tissue a 2-D character arracol that has been initialized
	* @param row the row inderow of the agent
	* @param col the col inderow of the agent
	* @param threshold the percentage of like agents that must surround the agent to be satisfied
	* @return boolean indicating if given agent is satisfied
	*
	**/
	
	
	//public static boolean smeete(char[][] S, char[][] EOne, char[][] IM, char[][] IM2, int row, int col){
	//	
	//	boolean sme = false;
	//	//double random = Math.random();
	//	
	//	if(S[row][col] == EOne[row][col] && S[row][col] == 'X'){
	//		S[row][col] = 'O';
	//		IM[row][col] = 'X';
	//		sme = true;
	//	}
	//	return sme;
	//}
 
	
	
	/**
	* Given a tissue sample, determines if all agents are satisfied.
	* Note: Blank cells are alwacols satisfied (as there is no agent)
	*
	* @param tissue a 2-D character arracol that has been initialized
	* @return boolean indicating whether entire board has been satisfied (all agents)
	**/
	
	
// 	public static int changeall(char[][] EOne, char[][] S, char[][] IM){
//		
//		int Ichange = 0;
//		for(int row = 0; row < EOne.length; row++){
//		
//			for(int col = 0; col < EOne[0].length; col++){
//				
//				if(S[row][col] == EOne[row][col] && S[row][col] == 'X'){
//					S[row][col] = 'O';
//					IM[row][col] = 'X';
//					Ichange++;
//					
//				}
//				
//				}
//			
//			}
//		
//			return Ichange;
//	}
     
	
	/**
	* Given a tissue sample, move all unsatisfied agents to a vacant cell
	*
	* @param tissue a 2-D character arracol that has been initialized
	* @param threshold the percentage of like agents that must surround the agent to be satisfied
	* @return an integer representing how mancol cells were moved in this round
	*
	**/
	
	
 	public static void moveboard(char[][] tissue, double DC){		
		
			for(int row = 0; row < tissue.length; row++){
			
				for(int col = 0; col <tissue[0].length; col++){
				
					double random = Math.random();
					
					if(tissue[row][col] == 'X'){
					    if(row == 0 && col == 0){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][tissue[0].length-1];
						    tissue[tissue[0].length-1][tissue[0].length-1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][tissue[0].length-1];
						    tissue[row][tissue[0].length-1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][tissue[0].length-1];
						    tissue[row + 1][tissue[0].length-1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col + 1];
						    tissue[row + 1][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col + 1];
						    tissue[tissue[0].length-1][col + 1] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col];
						    tissue[tissue[0].length-1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(row == 0 && col != 0 && col != tissue[row].length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col - 1];
						    tissue[tissue[0].length-1][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col - 1];
						    tissue[row + 1][col - 1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col + 1];
						    tissue[row + 1][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col + 1];
						    tissue[tissue[0].length-1][col + 1] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col];
						    tissue[tissue[0].length-1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(row == 0 && col == tissue[row].length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col - 1];
						    tissue[tissue[0].length-1][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col - 1];
						    tissue[row + 1][col - 1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][0];
						    tissue[row + 1][0] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][0];
						    tissue[row][0] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][0];
						    tissue[tissue[0].length-1][0] = temp;
						}else if( 7*DC/8 < random && random <= DC ){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[tissue[0].length-1][col];
						    tissue[tissue[0].length-1][col] = temp;
						}else if( DC < random && random <= 1 ){
						    tissue[row][col] = 'X';
						}
					    }else if(row != 0 && row != tissue.length - 1 && col == tissue[row].length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col - 1];
						    tissue[row - 1][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col - 1];
						    tissue[row + 1][col - 1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][0];
						    tissue[row + 1][0] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][0];
						    tissue[row][0] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][0];
						    tissue[row - 1][0] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(row == tissue.length - 1 && col == tissue[row].length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col - 1];
						    tissue[row - 1][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col - 1];
						    tissue[0][col - 1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col];
						    tissue[0][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][0];
						    tissue[0][0] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][0];
						    tissue[row][0] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][0];
						    tissue[row - 1][0] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(row == tissue.length - 1 && col != 0 && col != tissue[row].length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col - 1];
						    tissue[row - 1][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col - 1];
						    tissue[0][col - 1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col];
						    tissue[0][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col + 1];
						    tissue[0][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col + 1];
						    tissue[row - 1][col + 1] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(row == tissue.length - 1 && col == 0){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][tissue[0].length-1];
						    tissue[row - 1][tissue[0].length-1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][tissue[0].length-1];
						    tissue[row][tissue[0].length-1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][tissue[0].length-1];
						    tissue[0][tissue[0].length-1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col];
						    tissue[0][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[0][col + 1];
						    tissue[0][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col + 1];
						    tissue[row - 1][col + 1] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else if(col == 0 && row != 0 && row != tissue.length - 1){
					    	if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][tissue[0].length-1];
						    tissue[row - 1][tissue[0].length-1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][tissue[0].length-1];
						    tissue[row][tissue[0].length-1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][tissue[0].length-1];
						    tissue[row + 1][tissue[0].length-1] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col + 1];
						    tissue[row + 1][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col + 1];
						    tissue[row - 1][col + 1] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if(DC < random && random <= 1){
						    tissue[row][col] = 'X';
						}
					    }else{
						if(0.0 <= random && random <= DC/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col - 1];
						    tissue[row][col - 1] = temp;
						}else if(DC/8 < random && random <= DC/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col - 1];
						    tissue[row - 1][col - 1] = temp;
						}else if(DC/4 < random && random <= (3*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col];
						    tissue[row - 1][col] = temp;
						}else if((3*DC)/8 < random && random <= DC/2){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row - 1][col + 1];
						    tissue[row - 1][col + 1] = temp;
						}else if(DC/2 < random && random <= (5*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row][col + 1];
						    tissue[row][col + 1] = temp;
						}else if((5*DC)/8 < random && random <= (3*DC)/4){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col + 1];
						    tissue[row + 1][col + 1] = temp;
						}else if((3*DC)/4 < random && random <= (7*DC)/8){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col];
						    tissue[row + 1][col] = temp;
						}else if((7*DC)/8 < random && random <= DC){
						    char temp = tissue[row][col];
						    tissue[row][col] = tissue[row + 1][col - 1];
						    tissue[row + 1][col - 1] = temp;
						}
					    }
								
								
										}
								
							
        
							
				
				}
			
			}
		}
 
}
