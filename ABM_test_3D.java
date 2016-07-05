//A 3D Agent-based model similar as the 2D one.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;


public class ABM_test_3D{

	public static void main(String[] args) throws IOException{
	
		File file = new File(args[0]);
		//int round_input = Integer.parseInt(args[1]);
		int maxsub = Integer.parseInt(args[1]);
		int maxround = Integer.parseInt(args[2]);
//==========================================================Define cell===================================================================================
        
        char [][][] EnzymeOne = new char [200][200][200];
        char [][][] Substrates = new char [200][200][200];

        for(int i = 0; i < 200; i++){
        
            for(int j = 0; j < 200; j++){

                for(int k = 0; k < 200; k++){
            
                EnzymeOne[i][j][k] = 'O';
                Substrates[i][j][k] = 'O';
                
                }
            }
            
        }
        
        int spot_count_one = 0;

        while(spot_count_one < 200){
        
        	int i = (int)(Math.random()*200);
        	int j = (int)(Math.random()*200);
            int k = (int)(Math.random()*200);
        	if(EnzymeOne[i][j][k] == 'O'){
        	
        		EnzymeOne[i][j][k] = 'X';
        		spot_count_one++;
        	
        	}
        
		}
		
//==========================================================================================================================================================
		//The following code tests if there are only 10 enzyme particle in the grid. The count should be 10 (same as the final spot_count_one).
		
		int count_enzyme = 0;
		
		for(int i = 0; i < 200; i++){
			for(int j = 0; j < 200; j++){
                for(int k = 0; k < 200; k++){
				
				if(EnzymeOne[i][j][k] == 'X'){
					
					count_enzyme++;
					
				}
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
	//int marrowRounds = 200000;
	
	try{
		
		fw = new FileWriter(file, true);
		bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		
			
			//reset(IM, Substrates, IM2);
			//int number = 0;
			int spot_count_three = 0;
            int count_P = 0;

        while(spot_count_three < maxsub){
        	
        	int i = (int)(Math.random()*200);
        	int j = (int)(Math.random()*200);
            int k = (int)(Math.random()*200);
        	if(Substrates[i][j][k] == 'O'){
        
        		Substrates[i][j][k] = 'X';
        		spot_count_three++;
        
        	}
        	
        }
		
		
		for(int round = 0; round < maxround; round++){
			
			int I = 0;
			int count_S = 0;
			int count_E = 0;
			int count_ES = 0;
			moveboard(Substrates);
			moveboard(EnzymeOne);
			
			for(int i = 0; i < 200; i++){
				for(int j = 0; j < 200; j++){
                    for(int k = 0; k < 200; k++){

					double random_kf = Math.random();
					double random_kcat = Math.random();
					double random_kr = Math.random();
					
				   	if(Substrates[i][j][k] == EnzymeOne[i][j][k] && Substrates[i][j][k] == 'X' && random_kf*101 <= 60){
						Substrates[i][j][k] = 'O';
						EnzymeOne[i][j][k] = 'C';
					}
					if(EnzymeOne[i][j][k] == 'C' && random_kcat*1001 <= 50){
						EnzymeOne[i][j][k] = 'X';
						I++;
					}
					if(EnzymeOne[i][j][k] == 'C' && Substrates[i][j][k] == 'O' && random_kr*1001 <= 10){
						Substrates[i][j][k] = 'X';
						EnzymeOne[i][j][k] = 'X';
					} 
                    if(Substrates[i][j][k] == 'X'){
						count_S++;
					}
					if(EnzymeOne[i][j][k] == 'X'){
						count_E++;
					}
					if(EnzymeOne[i][j][k] == 'C'){
						count_ES++;
					}

					}
				}
			}
			
			//if(round == 10){
				count_P = count_P + I;
				//int xone = round;
				//int yone = count_I*100;
				//System.out.print(round);
				//System.out.print(",");
				//System.out.println(count_I);
				
				
			//}
			if(I != 0){
				
				pw.print(round);
				pw.print(',');
				pw.println(count_P);
				//pw.print(',');
				//pw.print(count_S);
				//pw.print(',');
				//pw.print(count_E);
				//pw.print(',');
				//pw.println(count_ES);
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
	
	
 	public static void reset(char[][][] IM, char[][][] Substrates, char[][][] IM2){
	
		for(int i = 0; i < 200; i++){
		
		    for(int j = 0; j < 200; j++){
            for(int k = 0; k < 200; k++){
		    
			IM[i][j][k] = 'O';
			Substrates[i][j][k] = 'O';
			IM2[i][j][k] = 'O';
			}
		    }
		    
		}
	
	}
	
	
	
	/**
	* Given a tissue sample, prints the cell make up in grid form
	*
	* @param tissue a 2-D character arracol representing a tissue sample
	* 
	***/
	
	public static boolean allO(char[][][] tissue){
			
	for(int row = 0; row < tissue.length; row++ ){
        
            for(int col = 0; col < tissue[0].length; col++){
                for(int k = 0; k < tissue[0][0].length; k++){
            	    
            	    if(tissue[row][col][k] == 'X'){
            	    
            	    	    return false;
            	    
            	    }
            	   } 
            }
        }
        
        return true;
	
	}
	
	public static void printTissue(char[][][] tissue){
		
        for(int row = 0; row < tissue.length; row++ ){
        
            for(int col = 0; col < tissue[0].length; col++){
                for(int k = 0; k < tissue[0][0].length; k++){
            
                System.out.print("'" + tissue[row][col][k] + "' ");		//Line 30-44 simplcol prints out the element in the 2D arracol
            }
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
	
	
 	public static void moveboard(char[][][] tissue){		
		
			for(int row = 0; row < tissue.length; row++){
			
				for(int col = 0; col <tissue[0].length; col++){
					for(int k = 0; k < tissue[0][0].length; k++){
				
					double random = Math.random()*261;
					
					if(tissue[row][col][k] == 'X'){
					    if(row == 0 && col == 0 && k == 0){
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-1];
						    tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-2];
						    tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-2] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-3];
						    tissue[tissue.length-1][tissue[0].length-1][tissue[0][0].length-3] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-1];
						    tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-2];
						    tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-2] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-3];
						    tissue[tissue.length-2][tissue[0].length-1][tissue[0][0].length-3] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-1];
						    tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-2];
						    tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-2] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-3];
						    tissue[tissue.length-3][tissue[0].length-1][tissue[0][0].length-3] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-1];
						    tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length-2][tissue[0][0].length-1];
						    tissue[tissue.length-2][tissue[0].length-2][tissue[0][0].length-1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length-2][tissue[0][0].length-1];
						    tissue[tissue.length-3][tissue[0].length-2][tissue[0][0].length-1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-1];
						    tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length-3][tissue[0][0].length-1];
						    tissue[tissue.length-2][tissue[0].length-3][tissue[0][0].length-1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length-3][tissue[0][0].length-1];
						    tissue[tissue.length-3][tissue[0].length-3][tissue[0][0].length-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-2];
						    tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-2] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-3];
						    tissue[tissue.length-1][tissue[0].length-2][tissue[0][0].length-3] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-2];
						    tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-2] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-3];
						    tissue[tissue.length-1][tissue[0].length-3][tissue[0][0].length-3] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[0][1][0];
							tissue[0][1][0] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[0][1][1];
							tissue[0][1][1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[0][0][1];
							tissue[0][0][1] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[1][0][1];
							tissue[1][0][1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[1][0][0];
							tissue[1][0][0] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[1][1][0];
							tissue[1][1][0] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
							tissue[row][col][k] = tissue[1][1][1];
							tissue[1][1][1] = temp;
						}
					    }else if(row == tissue.length - 1 && col == 0 && k == 0){
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 1][tissue[0][0].length - 1];
						    tissue[0][tissue[0].length - 1][tissue[0][0].length - 1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[0][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 1][tissue[0][0].length - 3];
						    tissue[0][tissue[0].length - 1][tissue[0][0].length - 3] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[0][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 2][tissue[0][0].length - 2];
						    tissue[0][tissue[0].length - 2][tissue[0][0].length - 2] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 2][tissue[0][0].length - 3];
						    tissue[0][tissue[0].length - 2][tissue[0][0].length - 3] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 3][tissue[0][0].length - 1];
						    tissue[0][tissue[0].length - 3][tissue[0][0].length - 1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 3][tissue[0][0].length - 2];
						    tissue[0][tissue[0].length - 3][tissue[0][0].length - 2] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 3][tissue[0][0].length - 3];
						    tissue[0][tissue[0].length - 3][tissue[0][0].length - 3] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 1][tissue[0][0].length - 1];
						    tissue[1][tissue[0].length - 1][tissue[0][0].length - 1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[1][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 1][tissue[0][0].length - 3];
						    tissue[1][tissue[0].length - 1][tissue[0][0].length - 3] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[1][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 3][tissue[0][0].length - 1];
						    tissue[1][tissue[0].length - 3][tissue[0][0].length - 1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length - 1][tissue[0][0].length - 1];
						    tissue[2][tissue[0].length - 1][tissue[0][0].length - 1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[2][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length - 1][tissue[0][0].length - 3];
						    tissue[2][tissue[0].length - 1][tissue[0][0].length - 3] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[2][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length - 3][tissue[0][0].length - 1];
						    tissue[2][tissue[0].length - 3][tissue[0][0].length - 1] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][0];
						    tissue[tissue.length - 1][1][0] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][1];
						    tissue[tissue.length - 1][1][1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][0][1];
						    tissue[tissue.length - 1][0][1] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][0];
						    tissue[tissue.length - 2][0][0] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][1][0];
						    tissue[tissue.length - 2][1][0] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][1][1];
						    tissue[tissue.length - 2][1][1] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][1];
						    tissue[tissue.length - 2][0][1] = temp;
						}
					    }else if(row == 0 && col == 0 && k == tissue[0][0].length - 1){								//#3 corner
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 1][0];
						    tissue[tissue.length-1][tissue[0].length - 1][0] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 2][0];
						    tissue[tissue.length-1][tissue[0].length - 2][0] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 3][0];
						    tissue[tissue.length-1][tissue[0].length - 3][0] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length - 1][0];
						    tissue[tissue.length-2][tissue[0].length - 1][0] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length - 2][0];
						    tissue[tissue.length-2][tissue[0].length - 2][0] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length - 3][0];
						    tissue[tissue.length-2][tissue[0].length - 3][0] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length - 1][0];
						    tissue[tissue.length-3][tissue[0].length - 1][0] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length - 2][0];
						    tissue[tissue.length-3][tissue[0].length - 2][0] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length - 3][0];
						    tissue[tissue.length-3][tissue[0].length - 3][0] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 1][1];
						    tissue[tissue.length-1][tissue[0].length - 1][1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 2][1];
						    tissue[tissue.length-1][tissue[0].length - 2][1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 3][1];
						    tissue[tissue.length-1][tissue[0].length - 3][1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 1][2];
						    tissue[tissue.length-1][tissue[0].length - 1][2] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 2][2];
						    tissue[tissue.length-1][tissue[0].length - 2][2] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][tissue[0].length - 3][2];
						    tissue[tissue.length-1][tissue[0].length - 3][2] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length - 1][1];
						    tissue[tissue.length-2][tissue[0].length - 1][1] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-2][tissue[0].length - 1][2];
						    tissue[tissue.length-2][tissue[0].length - 1][2] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length - 1][1];
						    tissue[tissue.length-3][tissue[0].length - 1][1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-3][tissue[0].length - 1][2];
						    tissue[tissue.length-3][tissue[0].length - 1][2] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][tissue[0][0].length - 2];
						    tissue[0][0][tissue[0][0].length - 2] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][tissue[0][0].length - 1];
						    tissue[0][1][tissue[0][0].length - 1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][tissue[0][0].length - 2];
						    tissue[0][1][tissue[0][0].length - 2] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][tissue[0][0].length - 1];
						    tissue[1][0][tissue[0][0].length - 1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][tissue[0][0].length - 2];
						    tissue[1][0][tissue[0][0].length - 2] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][1][tissue[0][0].length - 1];
						    tissue[1][1][tissue[0][0].length - 1] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][1][tissue[0][0].length - 2];
						    tissue[1][1][tissue[0][0].length - 2] = temp;
						}
					    }else if(row == tissue.length -1 && col == 0 && k == tissue[0][0].length -1){								//#4 corner
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -3][0];
						    tissue[0][tissue[0].length -3][0] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -3][1];
						    tissue[0][tissue[0].length -3][1] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -3][2];
						    tissue[0][tissue[0].length -3][2] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -2][0];
						    tissue[0][tissue[0].length -2][0] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -2][1];
						    tissue[0][tissue[0].length -2][1] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -2][2];
						    tissue[0][tissue[0].length -2][2] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -1][0];
						    tissue[0][tissue[0].length -1][0] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -1][1];
						    tissue[0][tissue[0].length -1][1] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -1][2];
						    tissue[0][tissue[0].length -1][2] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -1][0];
						    tissue[1][tissue[0].length -1][0] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -1][1];
						    tissue[1][tissue[0].length -1][1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -1][2];
						    tissue[1][tissue[0].length -1][2] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length -1][0];
						    tissue[2][tissue[0].length -1][0] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length -1][1];
						    tissue[2][tissue[0].length -1][1] = temp;
						}else if(140.0 > random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length -1][2];
						    tissue[2][tissue[0].length -1][2] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -2][0];
						    tissue[1][tissue[0].length -2][0] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -3][0];
						    tissue[1][tissue[0].length -3][0] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length -2][0];
						    tissue[2][tissue[0].length -2][0] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][tissue[0].length -3][0];
						    tissue[2][tissue[0].length -3][0] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][0][tissue[0][0].length -2];
						    tissue[tissue.length - 1][0][tissue[0][0].length -2] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][tissue[0][0].length -1];
						    tissue[tissue.length - 1][1][tissue[0][0].length -1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][tissue[0][0].length -2];
						    tissue[tissue.length - 1][1][tissue[0][0].length -2] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][tissue[0][0].length -1];
						    tissue[tissue.length - 2][0][tissue[0][0].length -1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][tissue[0][0].length -2];
						    tissue[tissue.length - 2][0][tissue[0][0].length -2] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][1][tissue[0][0].length -1];
						    tissue[tissue.length - 2][1][tissue[0][0].length -1] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][1][tissue[0][0].length -2];
						    tissue[tissue.length - 2][1][tissue[0][0].length -2] = temp;
						}
					    }else if(row == 0 && col == tissue[0].length -1 && k == tissue[0][0].length -1){									//#5 corner
					    	if(0.0 <= random && random <=10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 3][0][0];
						    tissue[tissue.length - 3][0][0] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 3][0][1];
						    tissue[tissue.length - 3][0][1] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 3][0][2];
						    tissue[tissue.length - 3][0][2] = temp;
						}else if(30 < random && random <= 40){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][0];
						    tissue[tissue.length - 2][0][0] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][1];
						    tissue[tissue.length - 2][0][1] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][0][2];
						    tissue[tissue.length - 2][0][2] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][0][0];
						    tissue[tissue.length - 1][0][0] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][0][1];
						    tissue[tissue.length - 1][0][1] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][0][2];
						    tissue[tissue.length - 1][0][2] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][0];
						    tissue[tissue.length - 1][1][0] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][1];
						    tissue[tissue.length - 1][1][1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][1][2];
						    tissue[tissue.length - 1][1][2] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][2][0];
						    tissue[tissue.length - 1][2][0] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][2][1];
						    tissue[tissue.length - 1][2][1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][2][2];
						    tissue[tissue.length - 1][2][2] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][2][0];
						    tissue[tissue.length - 2][2][0] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][1][0];
						    tissue[tissue.length - 2][1][0] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 3][2][0];
						    tissue[tissue.length - 3][2][0] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 3][1][0];
						    tissue[tissue.length - 3][1][0] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[0][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[0][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length - 2][tissue[0][0].length - 2];
						    tissue[0][tissue[0].length - 2][tissue[0][0].length - 2] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 1][tissue[0][0].length - 1];
						    tissue[1][tissue[0].length - 1][tissue[0][0].length - 1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[1][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[1][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length - 2][tissue[0][0].length - 2];
						    tissue[1][tissue[0].length - 2][tissue[0][0].length - 2] = temp;
						}
					    }else if(row == tissue.length - 1 && col == tissue[0].length - 1 && k == tissue[0][0].length - 1){								//#6 corner
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][0];
						    tissue[0][0][0] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][1];
						    tissue[0][0][1] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][2];
						    tissue[0][0][2] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][0];
						    tissue[0][1][0] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][1];
						    tissue[0][1][1] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][2];
						    tissue[0][1][2] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][0];
						    tissue[0][2][0] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][1];
						    tissue[0][2][1] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][2];
						    tissue[0][2][2] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][0];
						    tissue[1][0][0] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][1];
						    tissue[1][0][1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][2];
						    tissue[1][0][2] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][0];
						    tissue[2][0][0] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][1];
						    tissue[2][0][1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][2];
						    tissue[2][0][2] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][0];
						    tissue[0][1][0] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][0];
						    tissue[0][2][0] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][2][0];
						    tissue[1][2][0] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][1][0];
						    tissue[1][1][0] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[tissue.length -1][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[tissue.length -1][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length - 2][tissue[0][0].length - 2];
						    tissue[tissue.length -1][tissue[0].length - 2][tissue[0][0].length - 2] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length - 1][tissue[0][0].length - 1];
						    tissue[tissue.length -2][tissue[0].length - 1][tissue[0][0].length - 1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length - 1][tissue[0][0].length - 2];
						    tissue[tissue.length -2][tissue[0].length - 1][tissue[0][0].length - 2] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length - 2][tissue[0][0].length - 1];
						    tissue[tissue.length -2][tissue[0].length - 2][tissue[0][0].length - 1] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length - 2][tissue[0][0].length - 2];
						    tissue[tissue.length -2][tissue[0].length - 2][tissue[0][0].length - 2] = temp;
						}
					    }else if(row == 0 && col == tissue[0].length -1 && k == 0){															//#7 cormer
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][0][tissue[0][0].length - 1];
						    tissue[tissue.length -1][0][tissue[0][0].length - 1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][0][tissue[0][0].length - 2];
						    tissue[tissue.length -1][0][tissue[0][0].length - 2] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][0][tissue[0][0].length - 3];
						    tissue[tissue.length -1][0][tissue[0][0].length - 3] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][0][tissue[0][0].length - 1];
						    tissue[tissue.length -2][0][tissue[0][0].length - 1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][0][tissue[0][0].length - 2];
						    tissue[tissue.length -2][0][tissue[0][0].length - 2] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][0][tissue[0][0].length - 3];
						    tissue[tissue.length -2][0][tissue[0][0].length - 3] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -3][0][tissue[0][0].length - 1];
						    tissue[tissue.length -3][0][tissue[0][0].length - 1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -3][0][tissue[0][0].length - 2];
						    tissue[tissue.length -3][0][tissue[0][0].length - 2] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -3][0][tissue[0][0].length - 3];
						    tissue[tissue.length -3][0][tissue[0][0].length - 3] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][1][tissue[0][0].length - 1];
						    tissue[tissue.length -1][1][tissue[0][0].length - 1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][1][tissue[0][0].length - 2];
						    tissue[tissue.length -1][1][tissue[0][0].length - 2] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][1][tissue[0][0].length - 3];
						    tissue[tissue.length -1][1][tissue[0][0].length - 3] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][2][tissue[0][0].length - 1];
						    tissue[tissue.length -1][2][tissue[0][0].length - 1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][2][tissue[0][0].length - 2];
						    tissue[tissue.length -1][2][tissue[0][0].length - 2] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -1][2][tissue[0][0].length - 3];
						    tissue[tissue.length -1][2][tissue[0][0].length - 3] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][1][tissue[0][0].length - 1];
						    tissue[tissue.length -2][1][tissue[0][0].length - 1] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -3][1][tissue[0][0].length - 1];
						    tissue[tissue.length -3][1][tissue[0][0].length - 1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -2][2][tissue[0][0].length - 1];
						    tissue[tissue.length -2][2][tissue[0][0].length - 1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length -3][2][tissue[0][0].length - 1];
						    tissue[tissue.length -3][2][tissue[0][0].length - 1] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -2][0];
						    tissue[0][tissue[0].length -2][0] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -2][1];
						    tissue[0][tissue[0].length -2][1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][tissue[0].length -1][1];
						    tissue[0][tissue[0].length -1][1] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -1][0];
						    tissue[1][tissue[0].length -1][0] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -1][1];
						    tissue[1][tissue[0].length -1][1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -2][0];
						    tissue[1][tissue[0].length -2][0] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][tissue[0].length -2][1];
						    tissue[1][tissue[0].length -2][1] = temp;
						}
					    }else if(col == tissue.length -1 && row == tissue[0].length - 1 && k ==0){															//#8 corner
					    	if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][tissue[0][0].length -3];
						    tissue[0][0][tissue[0][0].length -3] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][tissue[0][0].length -3];
						    tissue[1][0][tissue[0][0].length -3] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][tissue[0][0].length -3];
						    tissue[2][0][tissue[0][0].length -3] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][tissue[0][0].length -2];
						    tissue[0][0][tissue[0][0].length -2] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][tissue[0][0].length -2];
						    tissue[1][0][tissue[0][0].length -2] = temp;
						}else if(50.0 < random && random <= 60.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][tissue[0][0].length -2];
						    tissue[2][0][tissue[0][0].length -2] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][0][tissue[0][0].length -1];
						    tissue[0][0][tissue[0][0].length -1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][0][tissue[0][0].length -1];
						    tissue[1][0][tissue[0][0].length -1] = temp;
						}else if(80.0 < random && random <= 90.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][0][tissue[0][0].length -1];
						    tissue[2][0][tissue[0][0].length -1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][tissue[0][0].length -1];
						    tissue[0][1][tissue[0][0].length -1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][1][tissue[0][0].length -1];
						    tissue[1][1][tissue[0][0].length -1] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][1][tissue[0][0].length -1];
						    tissue[2][1][tissue[0][0].length -1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][tissue[0][0].length -1];
						    tissue[0][2][tissue[0][0].length -1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[1][2][tissue[0][0].length -1];
						    tissue[1][2][tissue[0][0].length -1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[2][2][tissue[0][0].length -1];
						    tissue[2][2][tissue[0][0].length -1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][tissue[0][0].length -3];
						    tissue[0][1][tissue[0][0].length -3] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][1][tissue[0][0].length -2];
						    tissue[0][1][tissue[0][0].length -2] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][tissue[0][0].length -3];
						    tissue[0][2][tissue[0][0].length -3] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][2][tissue[0][0].length -2];
						    tissue[0][2][tissue[0][0].length -2] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][tissue[0].length - 2][0];
						    tissue[tissue.length - 1][tissue[0].length - 2][0] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][tissue[0].length - 2][1];
						    tissue[tissue.length - 1][tissue[0].length - 2][1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 1][tissue[0].length - 1][1];
						    tissue[tissue.length - 1][tissue[0].length - 1][1] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][tissue[0].length - 1][0];
						    tissue[tissue.length - 2][tissue[0].length - 1][0] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][tissue[0].length - 1][1];
						    tissue[tissue.length - 2][tissue[0].length - 1][1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][tissue[0].length - 2][0];
						    tissue[tissue.length - 2][tissue[0].length - 2][0] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length - 2][tissue[0].length - 2][1];
						    tissue[tissue.length - 2][tissue[0].length - 2][1] = temp;
						}
					    }else if(row == 0 && col != 0 && col != tissue[0].length -1 && k == 0){																										//#1 edge
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col -1][tissue[0][0].length -1];
								tissue[tissue.length -1][col -1][tissue[0][0].length -1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col -1][tissue[0][0].length -2];
								tissue[tissue.length -1][col -1][tissue[0][0].length -2] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col -1][tissue[0][0].length -3];
								tissue[tissue.length -1][col -1][tissue[0][0].length -3] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col][tissue[0][0].length -1];
								tissue[tissue.length -1][col][tissue[0][0].length -1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col][tissue[0][0].length -2];
								tissue[tissue.length -1][col][tissue[0][0].length -2] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col][tissue[0][0].length -3];
								tissue[tissue.length -1][col][tissue[0][0].length -3] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col+1][tissue[0][0].length -1];
								tissue[tissue.length -1][col+1][tissue[0][0].length -1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col+1][tissue[0][0].length -2];
								tissue[tissue.length -1][col+1][tissue[0][0].length -2] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][col+1][tissue[0][0].length -3];
								tissue[tissue.length -1][col+1][tissue[0][0].length -3] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][col-1][tissue[0][0].length -1];
								tissue[tissue.length -2][col-1][tissue[0][0].length -1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][col][tissue[0][0].length -1];
								tissue[tissue.length -2][col][tissue[0][0].length -1] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][col+1][tissue[0][0].length -1];
								tissue[tissue.length -2][col+1][tissue[0][0].length -1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][col-1][tissue[0][0].length -1];
								tissue[tissue.length -3][col-1][tissue[0][0].length -1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][col][tissue[0][0].length -1];
								tissue[tissue.length -3][col][tissue[0][0].length -1] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][col+1][tissue[0][0].length -1];
								tissue[tissue.length -3][col+1][tissue[0][0].length -1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k+1];
								tissue[row][col-1][k+1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k+1];
								tissue[row][col][k+1] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k+1];
								tissue[row][col+1][k+1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k];
								tissue[row+1][col-1][k] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k+1];
								tissue[row+1][col-1][k+1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k+1];
								tissue[row+1][col][k+1] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k+1];
								tissue[row+1][col+1][k+1] = temp;
							}
						}else if(row == 0 && col == 0 && k != 0 && k != tissue[0][0].length -1){																				//#2 edge
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -3][k-1];
								tissue[tissue.length -1][tissue[0].length -3][k-1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -3][k];
								tissue[tissue.length -1][tissue[0].length -3][k] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -3][k+1];
								tissue[tissue.length -1][tissue[0].length -3][k+1] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -2][k-1];
								tissue[tissue.length -1][tissue[0].length -2][k-1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -2][k];
								tissue[tissue.length -1][tissue[0].length -2][k] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -2][k+1];
								tissue[tissue.length -1][tissue[0].length -2][k+1] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -1][k-1];
								tissue[tissue.length -1][tissue[0].length -1][k-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -1][k];
								tissue[tissue.length -1][tissue[0].length -1][k] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -1][tissue[0].length -1][k+1];
								tissue[tissue.length -1][tissue[0].length -1][k+1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length -1][k-1];
								tissue[tissue.length -2][tissue[0].length -1][k-1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length -1][k];
								tissue[tissue.length -2][tissue[0].length -1][k] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -2][tissue[0].length -1][k+1];
								tissue[tissue.length -2][tissue[0].length -1][k+1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][tissue[0].length -1][k-1];
								tissue[tissue.length -3][tissue[0].length -1][k-1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][tissue[0].length -1][k];
								tissue[tissue.length -3][tissue[0].length -1][k] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length -3][tissue[0].length -1][k+1];
								tissue[tissue.length -3][tissue[0].length -1][k+1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k -1];
								tissue[row][col][k-1] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k +1];
								tissue[row][col][k+1] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k -1];
								tissue[row][col+1][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k+1];
								tissue[row][col+1][k+1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k -1];
								tissue[row+1][col][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k+1];
								tissue[row+1][col][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k -1];
								tissue[row+1][col+1][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k+1];
								tissue[row+1][col+1][k+1] = temp;
							}
						}else if(row == 0 && col != 0 && col != tissue[0].length -1 && k == 0){																					//#3 edge
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][col-1][0];
								tissue[tissue.length-3][col-1][0] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][col][0];
								tissue[tissue.length-3][col][0] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][col+1][0];
								tissue[tissue.length-3][col+1][0] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][col-1][0];
								tissue[tissue.length-2][col-1][0] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][col][0];
								tissue[tissue.length-2][col][0] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][col+1][0];
								tissue[tissue.length-2][col+1][0] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col-1][0];
								tissue[tissue.length-1][col-1][0] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col][0];
								tissue[tissue.length-1][col][0] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col+1][0];
								tissue[tissue.length-1][col+1][0] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col-1][1];
								tissue[tissue.length-1][col-1][1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col][1];
								tissue[tissue.length-1][col][1] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col+1][1];
								tissue[tissue.length-1][col+1][1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col-1][2];
								tissue[tissue.length-1][col-1][2] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col][2];
								tissue[tissue.length-1][col][2] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][col+1][2];
								tissue[tissue.length-1][col+1][2] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k-1];
								tissue[row][col-1][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k-1];
								tissue[row][col+1][k-1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k-1];
								tissue[row+1][col-1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k-1];
								tissue[row+1][col][k-1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k-1];
								tissue[row+1][col+1][k-1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k];
								tissue[row+1][col-1][k] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}
						}else if(row == 0 && col == tissue[0].length -1 && k != 0 && k != tissue[0][0].length -1){																			//#4 edge
							if(0.0 < random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][0][k-1];
								tissue[tissue.length-3][0][k-1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][0][k];
								tissue[tissue.length-3][0][k] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-3][0][k+1];
								tissue[tissue.length-3][0][k+1] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][0][k-1];
								tissue[tissue.length-2][0][k-1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][0][k];
								tissue[tissue.length-2][0][k] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-2][0][k+1];
								tissue[tissue.length-2][0][k+1] = temp;
							}else if(60.0 < random && random < 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][0][k-1];
								tissue[tissue.length-1][0][k-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][0][k];
								tissue[tissue.length-1][0][k] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][0][k+1];
								tissue[tissue.length-1][0][k+1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][1][k-1];
								tissue[tissue.length-1][1][k-1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][1][k];
								tissue[tissue.length-1][1][k] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][1][k+1];
								tissue[tissue.length-1][1][k+1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][2][k-1];
								tissue[tissue.length-1][2][k-1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][2][k];
								tissue[tissue.length-1][2][k] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[tissue.length-1][2][k+1];
								tissue[tissue.length-1][2][k+1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k+1];
								tissue[row][col][k+1] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k-1];
								tissue[row][col-1][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k+1];
								tissue[row][col-1][k+1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k-1];
								tissue[row+1][col-1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k];
								tissue[row+1][col-1][k] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k+1];
								tissue[row+1][col-1][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k-1];
								tissue[row+1][col][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k+1];
								tissue[row+1][col][k+1] = temp;
							}
						}else if(row == tissue.length -1 && col != 0 && col != tissue[0].length -1 && k == 0){																	//#5 edge
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-3];
								tissue[0][col-1][tissue[0][0].length-3] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-3];
								tissue[0][col][tissue[0][0].length-3] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-3];
								tissue[0][col+1][tissue[0][0].length-3] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-2];
								tissue[0][col-1][tissue[0][0].length-2] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-2];
								tissue[0][col][tissue[0][0].length-2] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-2];
								tissue[0][col+1][tissue[0][0].length-2] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-1];
								tissue[0][col-1][tissue[0][0].length-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-1];
								tissue[0][col][tissue[0][0].length-1] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-1];
								tissue[0][col+1][tissue[0][0].length-1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col-1][tissue[0][0].length-1];
								tissue[1][col-1][tissue[0][0].length-1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col][tissue[0][0].length-1];
								tissue[1][col][tissue[0][0].length-1] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col+1][tissue[0][0].length-1];
								tissue[1][col+1][tissue[0][0].length-1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col-1][tissue[0][0].length-1];
								tissue[2][col-1][tissue[0][0].length-1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col][tissue[0][0].length-1];
								tissue[2][col][tissue[0][0].length-1] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col+1][tissue[0][0].length-1];
								tissue[2][col+1][tissue[0][0].length-1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k];
								tissue[row-1][col-1][k] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k+1];
								tissue[row-1][col-1][k+1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k+1];
								tissue[row-1][col][k+1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k+1];
								tissue[row-1][col+1][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k+1];
								tissue[row][col-1][k+1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k+1];
								tissue[row][col][k+1] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k+1];
								tissue[row][col+1][k+1] = temp;
							}
						}else if(row == tissue.length-1 && col == 0 && k != 0 && k != tissue[0][0].length-1){																	//#6 edge
							if(0.0 < random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-3][k-1];
								tissue[0][tissue[0].length-3][k-1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-3][k];
								tissue[0][tissue[0].length-3][k] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-3][k+1];
								tissue[0][tissue[0].length-3][k+1] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-2][k-1];
								tissue[0][tissue[0].length-2][k-1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-2][k];
								tissue[0][tissue[0].length-2][k] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-2][k+1];
								tissue[0][tissue[0].length-2][k+1] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-1][k-1];
								tissue[0][tissue[0].length-1][k-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-1][k];
								tissue[0][tissue[0].length-1][k] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][tissue[0].length-1][k+1];
								tissue[0][tissue[0].length-1][k+1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][tissue[0].length-1][k-1];
								tissue[1][tissue[0].length-1][k-1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][tissue[0].length-1][k];
								tissue[1][tissue[0].length-1][k] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][tissue[0].length-1][k+1];
								tissue[1][tissue[0].length-1][k+1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][tissue[0].length-1][k-1];
								tissue[2][tissue[0].length-1][k-1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][tissue[0].length-1][k];
								tissue[2][tissue[0].length-1][k] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][tissue[0].length-1][k+1];
								tissue[2][tissue[0].length-1][k+1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k+1];
								tissue[row][col][k+1] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k-1];
								tissue[row][col+1][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k+1];
								tissue[row][col+1][k+1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k-1];
								tissue[row-1][col+1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k+1];
								tissue[row-1][col+1][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k-1];
								tissue[row-1][col][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k+1];
								tissue[row-1][col][k+1] = temp;
							}
						}else if(row == tissue.length-1 && col != 0 && col != tissue[0].length-1 && k == tissue[0][0].length-1){																	//#7 edge
							if(0.0 < random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-3];
								tissue[0][col-1][tissue[0][0].length-3] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-3];
								tissue[0][col][tissue[0][0].length-3] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-3];
								tissue[0][col+1][tissue[0][0].length-3] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-2];
								tissue[0][col-1][tissue[0][0].length-2] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-2];
								tissue[0][col][tissue[0][0].length-2] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-2];
								tissue[0][col+1][tissue[0][0].length-2] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col-1][tissue[0][0].length-1];
								tissue[0][col-1][tissue[0][0].length-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col][tissue[0][0].length-1];
								tissue[0][col][tissue[0][0].length-1] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][col+1][tissue[0][0].length-1];
								tissue[0][col+1][tissue[0][0].length-1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col-1][tissue[0][0].length-3];
								tissue[1][col-1][tissue[0][0].length-3] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col][tissue[0][0].length-3];
								tissue[1][col][tissue[0][0].length-3] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][col+1][tissue[0][0].length-3];
								tissue[1][col+1][tissue[0][0].length-3] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col-1][tissue[0][0].length-3];
								tissue[2][col-1][tissue[0][0].length-3] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col][tissue[0][0].length-3];
								tissue[2][col][tissue[0][0].length-3] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][col+1][tissue[0][0].length-3];
								tissue[2][col+1][tissue[0][0].length-3] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k-1];
								tissue[row][col-1][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k-1];
								tissue[row][col+1][k-1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k-1];
								tissue[row-1][col-1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k-1];
								tissue[row-1][col][k-1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k-1];
								tissue[row-1][col+1][k-1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k];
								tissue[row-1][col-1][k] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}
						}else if(row == tissue.length-1 && col == tissue[0].length-1 && k != 0 && k != tissue[0][0].length-1){																	//#8 edge		
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][2][k-1];
								tissue[0][2][k-1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][2][k];
								tissue[0][2][k] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][2][k+1];
								tissue[0][2][k+1] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][1][k-1];
								tissue[0][1][k-1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][1][k];
								tissue[0][1][k] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][1][k+1];
								tissue[0][1][k+1] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][0][k-1];
								tissue[0][0][k-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][0][k];
								tissue[0][0][k] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[0][0][k+1];
								tissue[0][0][k+1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][0][k-1];
								tissue[1][0][k-1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][0][k];
								tissue[1][0][k] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[1][0][k+1];
								tissue[1][0][k+1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][0][k-1];
								tissue[2][0][k-1] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][0][k];
								tissue[2][0][k] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[2][0][k+1];
								tissue[2][0][k+1] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k+1];
								tissue[row][col][k+1] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k-1];
								tissue[row-1][col][k-1] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k+1];
								tissue[row-1][col][k+1] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k-1];
								tissue[row-1][col-1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k];
								tissue[row-1][col-1][k] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k+1];
								tissue[row-1][col-1][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k-1];
								tissue[row][col-1][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k+1];
								tissue[row][col-1][k+1] = temp;
							}
						}else if(row == 0 && col != 0 && col != tissue[0].length-1 && k != 0 && k != tissue[0][0].length-1){																					//#1 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col - 1][k-1];
						    tissue[tissue.length-1][col - 1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col - 1][k];
						    tissue[tissue.length-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col - 1][k+1];
						    tissue[tissue.length-1][col - 1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k-1];
						    tissue[row][col - 1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k+1];
						    tissue[row][col - 1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k-1];
						    tissue[row+1][col - 1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k];
						    tissue[row+1][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k+1];
						    tissue[row+1][col - 1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col][k-1];
						    tissue[tissue.length-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col][k];
						    tissue[tissue.length-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col][k+1];
						    tissue[tissue.length-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k-1];
						    tissue[row+1][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k+1];
						    tissue[row+1][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col+1][k-1];
						    tissue[tissue.length-1][col+1][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col+1][k];
						    tissue[tissue.length-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[tissue.length-1][col+1][k+1];
						    tissue[tissue.length-1][col+1][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k-1];
						    tissue[row][col+1][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k+1];
						    tissue[row][col+1][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k-1];
						    tissue[row+1][col+1][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k];
						    tissue[row+1][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k+1];
						    tissue[row+1][col+1][k+1] = temp;
						}
						}else if(row != 0 && row != tissue.length-1 && col != 0 && col != tissue[0].length-1 && k == tissue[0][0].length-1){							//#2 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k-1];
						    tissue[row-1][col - 1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k];
						    tissue[row-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][0];
						    tissue[row-1][col - 1][0] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k-1];
						    tissue[row][col - 1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][0];
						    tissue[row][col - 1][0] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k-1];
						    tissue[row+1][col - 1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k];
						    tissue[row+1][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][0];
						    tissue[row+1][col - 1][0] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k-1];
						    tissue[row-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][0];
						    tissue[row-1][col][0] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][0];
						    tissue[row][col][0] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k-1];
						    tissue[row+1][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][0];
						    tissue[row+1][col][0] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k-1];
						    tissue[row-1][col+1][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k];
						    tissue[row-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][0];
						    tissue[row-1][col+1][0] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k-1];
						    tissue[row][col+1][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][0];
						    tissue[row][col+1][0] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k-1];
						    tissue[row+1][col+1][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k];
						    tissue[row+1][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][0];
						    tissue[row+1][col+1][0] = temp;
						}
						}else if(row == tissue.length-1 && col != 0 && col != tissue[0].length-1 && k != 0 && k!= tissue[0][0].length-1){																	//#3 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k-1];
						    tissue[row-1][col - 1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k];
						    tissue[row-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k+1];
						    tissue[row-1][col - 1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k-1];
						    tissue[row][col - 1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k+1];
						    tissue[row][col - 1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col - 1][k-1];
						    tissue[0][col - 1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col - 1][k];
						    tissue[0][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col - 1][k+1];
						    tissue[0][col - 1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k-1];
						    tissue[row-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k+1];
						    tissue[row-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col][k-1];
						    tissue[0][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col][k];
						    tissue[0][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col][k+1];
						    tissue[0][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k-1];
						    tissue[row-1][col+1][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k];
						    tissue[row-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k+1];
						    tissue[row-1][col+1][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k-1];
						    tissue[row][col+1][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k+1];
						    tissue[row][col+1][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col+1][k-1];
						    tissue[0][col+1][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col+1][k];
						    tissue[0][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[0][col+1][k+1];
						    tissue[0][col+1][k+1] = temp;
						}
						}else if(row != 0 && row != tissue.length-1 && col != 0 && col != tissue[0].length-1 && k == 0){															//#4 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][tissue[0][0].length-1];
						    tissue[row-1][col - 1][tissue[0][0].length-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k];
						    tissue[row-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k+1];
						    tissue[row-1][col - 1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][tissue[0][0].length-1];
						    tissue[row][col - 1][tissue[0][0].length-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k+1];
						    tissue[row][col - 1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][tissue[0][0].length-1];
						    tissue[row+1][col - 1][tissue[0][0].length-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k];
						    tissue[row+1][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k+1];
						    tissue[row+1][col - 1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][tissue[0][0].length-1];
						    tissue[row-1][col][tissue[0][0].length-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k+1];
						    tissue[row-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][tissue[0][0].length-1];
						    tissue[row][col][tissue[0][0].length-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][tissue[0][0].length-1];
						    tissue[row+1][col][tissue[0][0].length-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k+1];
						    tissue[row+1][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][tissue[0][0].length-1];
						    tissue[row-1][col+1][tissue[0][0].length-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k];
						    tissue[row-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k+1];
						    tissue[row-1][col+1][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][tissue[0][0].length-1];
						    tissue[row][col+1][tissue[0][0].length-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k+1];
						    tissue[row][col+1][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][tissue[0][0].length-1];
						    tissue[row+1][col+1][tissue[0][0].length-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k];
						    tissue[row+1][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k+1];
						    tissue[row+1][col+1][k+1] = temp;
						}
						}else if(row != 0 && row != tissue.length-1 && col == 0 && k != 0 && k != tissue[0][0].length-1){																//#5 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][tissue[0].length-1][k-1];
						    tissue[row-1][tissue[0].length-1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][tissue[0].length-1][k];
						    tissue[row-1][tissue[0].length-1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][tissue[0].length-1][k+1];
						    tissue[row-1][tissue[0].length-1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][tissue[0].length-1][k-1];
						    tissue[row][tissue[0].length-1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][tissue[0].length-1][k];
						    tissue[row][tissue[0].length-1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][tissue[0].length-1][k+1];
						    tissue[row][tissue[0].length-1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][tissue[0].length-1][k-1];
						    tissue[row+1][tissue[0].length-1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][tissue[0].length-1][k];
						    tissue[row+1][tissue[0].length-1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][tissue[0].length-1][k+1];
						    tissue[row+1][tissue[0].length-1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k-1];
						    tissue[row-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k+1];
						    tissue[row-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k-1];
						    tissue[row+1][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k+1];
						    tissue[row+1][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k-1];
						    tissue[row-1][col+1][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k];
						    tissue[row-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k+1];
						    tissue[row-1][col+1][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k-1];
						    tissue[row][col+1][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k+1];
						    tissue[row][col+1][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k-1];
						    tissue[row+1][col+1][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k];
						    tissue[row+1][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k+1];
						    tissue[row+1][col+1][k+1] = temp;
						}
						}else if(row != 0 && row != tissue.length-1 && col == tissue[0].length-1 && k != 0 && k != tissue[0][0].length-1){																		//#6 face
							if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k-1];
						    tissue[row-1][col - 1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k];
						    tissue[row-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k+1];
						    tissue[row-1][col - 1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k-1];
						    tissue[row][col - 1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k+1];
						    tissue[row][col - 1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k-1];
						    tissue[row+1][col - 1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k];
						    tissue[row+1][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k+1];
						    tissue[row+1][col - 1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k-1];
						    tissue[row-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k+1];
						    tissue[row-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k-1];
						    tissue[row+1][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k+1];
						    tissue[row+1][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][0][k-1];
						    tissue[row-1][0][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][0][k];
						    tissue[row-1][0][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][0][k+1];
						    tissue[row-1][0][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][0][k-1];
						    tissue[row][0][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][0][k];
						    tissue[row][0][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][0][k+1];
						    tissue[row][0][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][0][k-1];
						    tissue[row+1][0][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][0][k];
						    tissue[row+1][0][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][0][k+1];
						    tissue[row+1][0][k+1] = temp;
						}
						}else if(row != 0 && row != tissue.length-1 && col == 0 && k == 0){																	//#9 edge
							if(0.0 <= random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row -1][tissue[0].length-3][tissue[0][0].length-1];
								tissue[row -1][tissue[0].length-3][tissue[0][0].length-1] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-3][tissue[0][0].length-1];
								tissue[row][tissue[0].length-3][tissue[0][0].length-1] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-3][tissue[0][0].length-1];
								tissue[row+1][tissue[0].length-3][tissue[0][0].length-1] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row -1][tissue[0].length-2][tissue[0][0].length-1];
								tissue[row -1][tissue[0].length-2][tissue[0][0].length-1] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-2][tissue[0][0].length-1];
								tissue[row][tissue[0].length-2][tissue[0][0].length-1] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-2][tissue[0][0].length-1];
								tissue[row+1][tissue[0].length-2][tissue[0][0].length-1] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row -1][tissue[0].length-1][tissue[0][0].length-1];
								tissue[row -1][tissue[0].length-1][tissue[0][0].length-1] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][tissue[0][0].length-1];
								tissue[row][tissue[0].length-1][tissue[0][0].length-1] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][tissue[0][0].length-1];
								tissue[row+1][tissue[0].length-1][tissue[0][0].length-1] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row -1][tissue[0].length-1][tissue[0][0].length-2];
								tissue[row -1][tissue[0].length-1][tissue[0][0].length-2] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][tissue[0][0].length-2];
								tissue[row][tissue[0].length-1][tissue[0][0].length-2] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][tissue[0][0].length-2];
								tissue[row+1][tissue[0].length-1][tissue[0][0].length-2] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row -1][tissue[0].length-1][tissue[0][0].length-3];
								tissue[row -1][tissue[0].length-1][tissue[0][0].length-3] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][tissue[0][0].length-3];
								tissue[row][tissue[0].length-1][tissue[0][0].length-3] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][tissue[0][0].length-3];
								tissue[row+1][tissue[0].length-1][tissue[0][0].length-3] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k+1];
								tissue[row-1][col+1][k+1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k+1];
								tissue[row][col+1][k+1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k+1];
								tissue[row+1][col+1][k+1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}
						}else if(row != 0 && row != tissue.length-1 && col == 0 && k == tissue[0][0].length-1){																															//#10 edge
							if(0.0 < random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][tissue[0].length-3][0];
								tissue[row-1][tissue[0].length-3][0] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-3][0];
								tissue[row][tissue[0].length-3][0] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-3][0];
								tissue[row+1][tissue[0].length-3][0] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][tissue[0].length-2][0];
								tissue[row-1][tissue[0].length-2][0] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-2][0];
								tissue[row][tissue[0].length-2][0] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-2][0];
								tissue[row+1][tissue[0].length-2][0] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][tissue[0].length-1][0];
								tissue[row-1][tissue[0].length-1][0] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][0];
								tissue[row][tissue[0].length-1][0] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][0];
								tissue[row+1][tissue[0].length-1][0] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][tissue[0].length-1][1];
								tissue[row-1][tissue[0].length-1][1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][1];
								tissue[row][tissue[0].length-1][1] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][1];
								tissue[row+1][tissue[0].length-1][1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][tissue[0].length-1][2];
								tissue[row-1][tissue[0].length-1][2] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][tissue[0].length-1][2];
								tissue[row][tissue[0].length-1][2] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][tissue[0].length-1][2];
								tissue[row+1][tissue[0].length-1][2] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k];
								tissue[row-1][col+1][k] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k];
								tissue[row][col+1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k];
								tissue[row+1][col+1][k] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col+1][k-1];
								tissue[row-1][col+1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col+1][k-1];
								tissue[row][col+1][k-1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col+1][k-1];
								tissue[row+1][col+1][k-1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k-1];
								tissue[row-1][col][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k-1];
								tissue[row+1][col][k-1] = temp;
							}
						}else if(row != 0 && row != tissue.length-1 && col == tissue[0].length-1 && k == tissue[0][0].length-1){																															//#11 edge
							if(0.0 < random && random <= 10.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][0][0];
								tissue[row-1][0][0] = temp;
							}else if(10.0 < random && random <= 20.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][0][0];
								tissue[row][0][0] = temp;
							}else if(20.0 < random && random <= 30.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][0][0];
								tissue[row+1][0][0] = temp;
							}else if(30.0 < random && random <= 40.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][1][0];
								tissue[row-1][1][0] = temp;
							}else if(40.0 < random && random <= 50.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][1][0];
								tissue[row][1][0] = temp;
							}else if(50.0 < random && random <= 60.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][1][0];
								tissue[row+1][1][0] = temp;
							}else if(60.0 < random && random <= 70.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][2][0];
								tissue[row-1][2][0] = temp;
							}else if(70.0 < random && random <= 80.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][2][0];
								tissue[row][2][0] = temp;
							}else if(80.0 < random && random <= 90.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][2][0];
								tissue[row+1][2][0] = temp;
							}else if(90.0 < random && random <= 100.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][0][1];
								tissue[row-1][0][1] = temp;
							}else if(100.0 < random && random <= 110.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][0][1];
								tissue[row][0][1] = temp;
							}else if(110.0 < random && random <= 120.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][0][1];
								tissue[row+1][0][1] = temp;
							}else if(120.0 < random && random <= 130.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][0][2];
								tissue[row-1][0][2] = temp;
							}else if(130.0 < random && random <= 140.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][0][2];
								tissue[row][0][2] = temp;
							}else if(140.0 < random && random <= 150.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][0][2];
								tissue[row+1][0][2] = temp;
							}else if(150.0 < random && random <= 160.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k];
								tissue[row-1][col][k] = temp;
							}else if(160.0 < random && random <= 170.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k];
								tissue[row+1][col][k] = temp;
							}else if(170.0 < random && random <= 180.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k];
								tissue[row-1][col-1][k] = temp;
							}else if(180.0 < random && random <= 190.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k];
								tissue[row][col-1][k] = temp;
							}else if(190.0 < random && random <= 200.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k];
								tissue[row+1][col-1][k] = temp;
							}else if(200.0 < random && random <= 210.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col-1][k-1];
								tissue[row-1][col-1][k-1] = temp;
							}else if(210.0 < random && random <= 220.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col-1][k-1];
								tissue[row][col-1][k-1] = temp;
							}else if(220.0 < random && random <= 230.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col-1][k-1];
								tissue[row+1][col-1][k-1] = temp;
							}else if(230.0 < random && random <= 240.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row-1][col][k-1];
								tissue[row-1][col][k-1] = temp;
							}else if(240.0 < random && random <= 250.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row][col][k-1];
								tissue[row][col][k-1] = temp;
							}else if(250.0 < random && random <= 260.0){
								char temp = tissue[row][col][k];
								tissue[row][col][k] = tissue[row+1][col][k-1];
								tissue[row+1][col][k-1] = temp;
							}
						}else if(row != 0 && row != tissue.length-1 && col == tissue[0].length-1 && k == 0){																															//#12 edge	
								if(0.0 < random && random <= 10.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][0][tissue[0][0].length-3];
									tissue[row-1][0][tissue[0][0].length-3] = temp;
								}else if(10.0 < random && random <= 20.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][0][tissue[0][0].length-3];
									tissue[row][0][tissue[0][0].length-3] = temp;
								}else if(20.0 < random && random <= 30.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][0][tissue[0][0].length-3];
									tissue[row+1][0][tissue[0][0].length-3] = temp;
								}else if(30.0 < random && random <= 40.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][0][tissue[0][0].length-2];
									tissue[row-1][0][tissue[0][0].length-2] = temp;
								}else if(40.0 < random && random <= 50.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][0][tissue[0][0].length-2];
									tissue[row][0][tissue[0][0].length-2] = temp;
								}else if(50.0 < random && random <= 60.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][0][tissue[0][0].length-2];
									tissue[row+1][0][tissue[0][0].length-2] = temp;
								}else if(60.0 < random && random <= 70.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][0][tissue[0][0].length-1];
									tissue[row-1][0][tissue[0][0].length-1] = temp;
								}else if(70.0 < random && random <= 80.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][0][tissue[0][0].length-1];
									tissue[row][0][tissue[0][0].length-1] = temp;
								}else if(80.0 < random && random <= 90.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][0][tissue[0][0].length-1];
									tissue[row+1][0][tissue[0][0].length-1] = temp;
								}else if(90.0 < random && random <= 100.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][1][tissue[0][0].length-1];
									tissue[row-1][1][tissue[0][0].length-1] = temp;
								}else if(100.0 < random && random <= 110.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][1][tissue[0][0].length-1];
									tissue[row][1][tissue[0][0].length-1] = temp;
								}else if(110.0 < random && random <= 120.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][1][tissue[0][0].length-1];
									tissue[row+1][1][tissue[0][0].length-1] = temp;
								}else if(120.0 < random && random <= 130.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][2][tissue[0][0].length-1];
									tissue[row-1][2][tissue[0][0].length-1] = temp;
								}else if(130.0 < random && random <= 140.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][2][tissue[0][0].length-1];
									tissue[row][2][tissue[0][0].length-1] = temp;
								}else if(140.0 < random && random <= 150.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][2][tissue[0][0].length-1];
									tissue[row+1][2][tissue[0][0].length-1] = temp;
								}else if(150.0 < random && random <= 160.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][col][k];
									tissue[row-1][col][k] = temp;
								}else if(160.0 < random && random <= 170.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][col][k];
									tissue[row+1][col][k] = temp;
								}else if(170.0 < random && random <= 180.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][col][k+1];
									tissue[row-1][col][k+1] = temp;
								}else if(180.0 < random && random <= 190.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][col][k+1];
									tissue[row][col][k+1] = temp;
								}else if(190.0 < random && random <= 200.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][col][k+1];
									tissue[row+1][col][k+1] = temp;
								}else if(200.0 < random && random <= 210.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][col-1][k+1];
									tissue[row-1][col-1][k+1] = temp;
								}else if(210.0 < random && random <= 220.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][col-1][k+1];
									tissue[row][col-1][k+1] = temp;
								}else if(220.0 < random && random <= 230.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][col-1][k+1];
									tissue[row+1][col-1][k+1] = temp;
								}else if(230.0 < random && random <= 240.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row-1][col-1][k];
									tissue[row-1][col-1][k] = temp;
								}else if(240.0 < random && random <= 250.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row][col-1][k];
									tissue[row][col-1][k] = temp;
								}else if(250.0 < random && random <= 260.0){
									char temp = tissue[row][col][k];
									tissue[row][col][k] = tissue[row+1][col-1][k];
									tissue[row+1][col-1][k] = temp;
								}
						}else if(row != 0 && row != tissue.length-1 && col != 0 && col != tissue[0].length-1 && k != 0 && k!= tissue[0][0].length-1){																		//center
						if(0.0 <= random && random <= 10.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k-1];
						    tissue[row-1][col - 1][k-1] = temp;
						}else if(10.0 < random && random <= 20.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k];
						    tissue[row-1][col - 1][k] = temp;
						}else if(20.0 < random && random <= 30.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col - 1][k+1];
						    tissue[row-1][col - 1][k+1] = temp;
						}else if(30.0 < random && random <= 40.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k-1];
						    tissue[row][col - 1][k-1] = temp;
						}else if(40.0 < random && random <= 50.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k];
						    tissue[row][col - 1][k] = temp;
						}else if(50.0 < random && random <= 60.0){
						   char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col - 1][k+1];
						    tissue[row][col - 1][k+1] = temp;
						}else if(60.0 < random && random <= 70.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k-1];
						    tissue[row+1][col - 1][k-1] = temp;
						}else if(70.0 < random && random <= 80.0){
						    char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k];
						    tissue[row+1][col - 1][k] = temp;
						}else if(80.0 < random && random <= 90.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col - 1][k+1];
						    tissue[row+1][col - 1][k+1] = temp;
						}else if(90.0 < random && random <= 100.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k-1];
						    tissue[row-1][col][k-1] = temp;
						}else if(100.0 < random && random <= 110.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k];
						    tissue[row-1][col][k] = temp;
						}else if(110.0 < random && random <= 120.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col][k+1];
						    tissue[row-1][col][k+1] = temp;
						}else if(120.0 < random && random <= 130.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k-1];
						    tissue[row][col][k-1] = temp;
						}else if(130.0 < random && random <= 140.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col][k+1];
						    tissue[row][col][k+1] = temp;
						}else if(140.0 < random && random <= 150.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k-1];
						    tissue[row+1][col][k-1] = temp;
						}else if(150.0 < random && random <= 160.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k];
						    tissue[row+1][col][k] = temp;
						}else if(160.0 < random && random <= 170.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col][k+1];
						    tissue[row+1][col][k+1] = temp;
						}else if(170.0 < random && random <= 180.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k-1];
						    tissue[row-1][col+1][k-1] = temp;
						}else if(180.0 < random && random <= 190.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k];
						    tissue[row-1][col+1][k] = temp;
						}else if(190.0 < random && random <= 200.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row-1][col+1][k+1];
						    tissue[row-1][col+1][k+1] = temp;
						}else if(200.0 < random && random <= 210.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k-1];
						    tissue[row][col+1][k-1] = temp;
						}else if(210.0 < random && random <= 220.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k];
						    tissue[row][col+1][k] = temp;
						}else if(220.0 < random && random <= 230.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row][col+1][k+1];
						    tissue[row][col+1][k+1] = temp;
						}else if(230.0 < random && random <= 240.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k-1];
						    tissue[row+1][col+1][k-1] = temp;
						}else if(240.0 < random && random <= 250.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k];
						    tissue[row+1][col+1][k] = temp;
						}else if(250.0 < random && random <= 260.0){
							char temp = tissue[row][col][k];
						    tissue[row][col][k] = tissue[row+1][col+1][k+1];
						    tissue[row+1][col+1][k+1] = temp;
						}
					    }
								
								
										}
								
							
        
				}	
				
				}
			
			}
		}
 
}
