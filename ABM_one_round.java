import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;


public class ABM_one_round{

	public static void main(String[] args) throws IOException{
	
		File file = new File(args[0]);
		int round_input = Integer.parseInt(args[1]);
//==========================================================Define cell===================================================================================
        
        char [][] EnzymeOne = new char [500][500];
        char [][] Substrates = new char [500][500];
        char [][] IM = new char [500][500];
        char [][] IM2 = new char [500][500];

        for(int i = 0; i < 500; i++){
        
            for(int j = 0; j < 500; j++){
            
                EnzymeOne[i][j] = 'O';
                IM[i][j] = 'O';
                IM2[i][j] = 'O';
                Substrates[i][j] = 'O';
                
            }
            
        }
        
        int spot_count_one = 0;

        while(spot_count_one < 10){
        
        	int i = (int)(Math.random()*500);
        	int j = (int)(Math.random()*500);
        	if(EnzymeOne[i][j] == 'O'){
        	
        		EnzymeOne[i][j] = 'X';
        		spot_count_one++;
        	
        	}
        
		}
		
//==========================================================================================================================================================
		//The following code tests if there are only 10 enzyme particle in the grid. The count should be 10 (same as the final spot_count_one).
		
		int count_enzyme = 0;
		
		for(int i = 0; i < 500; i++){
			for(int j = 0; j < 500; j++){
				
				if(EnzymeOne[i][j] == 'X'){
					
					count_enzyme++;
					
				}
				
			}
			
		}
//==========================================================================================================================================================		
		
	System.out.println(count_enzyme);
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	
	
//==========================================================================================================================================================
	
	FileWriter fw = null;
	BufferedWriter bw = null;
	PrintWriter pw = null;
	int maxsub = 200000;
	int marrowRounds = 100000;
	
	try{
		
		fw = new FileWriter(file, true);
		bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		
		for(int steps = 500; steps <= maxsub; steps = steps + 1000){
			
			reset(IM, Substrates, IM2);
			//int number = 0;
			int spot_count_three = 0;
			int count_I = 0;

        while(spot_count_three < steps){
        	
        	int i = (int)(Math.random()*500);
        	int j = (int)(Math.random()*500);
        	if(Substrates[i][j] == 'O'){
        
        		Substrates[i][j] = 'X';
        		spot_count_three++;
        
        	}
        	
        }
		
		
		for(int round = 0; round < marrowRounds; round++){
			
			int I = 0;
			moveboard(Substrates, 0.88889);
			moveboard(IM, 0.88889);
			moveboard(EnzymeOne, 0.88889);
			
			for(int i = 0; i < 500; i++){
				for(int j = 0; j < 500; j++){
					
					if(Substrates[i][j] == EnzymeOne[i][j] && Substrates[i][j] == 'X'){
					Substrates[i][j] = 'O';
					IM[i][j] = 'X';
					I++;
					}
					
				}
			}
			
			count_I = count_I + I;
			//if(round == 10){
				
				//int xone = round;
				//int yone = count_I*100;
				//System.out.print(round);
				//System.out.print(",");
				//System.out.println(count_I);
				
				
			//}
			if(round == round_input){
				
				int x = round;
				int y = count_I*100;
				double m = y/x;
				pw.print(spot_count_three);
				pw.print(',');
				pw.format("%.2f",m);
				pw.println();
				pw.flush();
				//System.out.println();
				//System.out.println();
				//System.out.println();
				//System.out.println();
				break;
				
			
			}
				
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