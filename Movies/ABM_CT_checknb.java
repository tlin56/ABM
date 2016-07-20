import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;


public class ABM_CT_checknb{

	public static void main(String[] args) throws IOException{
	
		File file = new File(args[0]);
		int maxround = Integer.parseInt(args[1]);
        int stick_thresh = Integer.parseInt(args[2]);
//==========================================================Define cell===================================================================================
        
        char [][][] EnzymeOne = new char [100][100][100];
        char [][][] EnzymeOne_copy = new char [100][100][100];

        for(int i = 0; i < 100; i++){
        
            for(int j = 0; j < 100; j++){

                for(int k = 0; k < 100; k++){
            
                EnzymeOne[i][j][k] = 'O';
                EnzymeOne_copy[i][j][k] = 'O';
                
                }
            }
            
        }
        
        int spot_count_one = 0;

        while(spot_count_one < 5000){
        
        	int i = (int)(Math.random()*100);
        	int j = (int)(Math.random()*100);
            int k = (int)(Math.random()*100);
        	if(EnzymeOne[i][j][k] == 'O'){
        	
        		EnzymeOne[i][j][k] = 'X';
        		spot_count_one++;
        	
        	}
        
		}
		
		EnzymeOne_copy[50][50][50] = 'X';
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try{
			
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
		for(int round = 0; round < maxround; round++){
			
			int I = 0;
			int count_S = 0;
			int count_E = 0;
			int count_ES = 0;
			moveboard(EnzymeOne);
			for(int row = 1; row < 99; row++){
				for(int col = 1; col < 99; col++){
                    for(int k = 1; k < 99; k++){
					double prob_stick = Math.random();
                    double prob_one = Math.random();
					
				   	if(EnzymeOne[row][col][k] == 'X'){
						
						if(checknb(EnzymeOne_copy,row,col,k) == 1){
							if(EnzymeOne_copy[row-1][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col-1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';
			}
		}else if(EnzymeOne_copy[row-1][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col+1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row+1][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col-1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row+1][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col+1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row-1][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col-1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row-1][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col+1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col-1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col+1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col - 1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k-1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k+1) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
						}
						
						if(checknb(EnzymeOne_copy,row,col,k) == 2){
							if(EnzymeOne_copy[row-1][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col-1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';
			}
		}else if(EnzymeOne_copy[row-1][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col+1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row+1][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col-1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row+1][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col+1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row-1][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col-1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
        else if(EnzymeOne_copy[row-1][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col+1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col-1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col+1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col - 1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k) <=3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k-1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k-1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row+1][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row+1,col,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row-1][col][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row-1,col,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col+1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col+1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
		else if(EnzymeOne_copy[row][col-1][k+1] == 'X'){
			if(checknb(EnzymeOne_copy,row,col-1,k+1) <3 && prob_stick*100 < stick_thresh){
				EnzymeOne_copy[row][col][k] = EnzymeOne[row][col][k];
				EnzymeOne[row][col][k] = 'O';}
		}
						}
						
					}

					}
				}
			}
		}
			int count = 0;

		pw.println(stick_thresh);

		int nb0 = 0;
		int nb1 = 0;
		int nb2 = 0;
		int nb3 = 0;
		int nb4 = 0;
		int nb5 = 0;
		int nb6 = 0;
		
		for(int i = 0; i < 100; i++){
        
            for(int j = 0; j < 100; j++){

                for(int k = 0; k < 100; k++){
					if(EnzymeOne_copy[i][j][k] == 'X'){
					if(checknb(EnzymeOne_copy,i,j,k) == 0){
						nb0++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 1){
						nb1++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 2){
						nb2++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 3){
						nb3++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 4){
						nb4++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 5){
						nb5++;
					}
					if(checknb(EnzymeOne_copy,i,j,k) == 6){
						nb6++;
					}
				}
                
                }
            }
            
        }
				
				pw.print("Enzyme with 0 NB:		");
				pw.println(nb0);
				pw.print("Enzyme with 1 NB:		");
				pw.println(nb1);
				pw.print("Enzyme with 2 NB:		");
				pw.println(nb2);
				pw.print("Enzyme with 3 NB:		");
				pw.println(nb3);
				pw.print("Enzyme with 4 NB:		");
				pw.println(nb4);
				pw.print("Enzyme with 5 NB:		");
				pw.println(nb5);
				pw.print("Enzyme with 6 NB:		");
				pw.println(nb6);
				pw.flush();
	
	}finally{
		
		try{
			
			pw.close();
			bw.close();
			fw.close();
			
		}catch(IOException io){}
		
	}
		
	}
	
	
 	public static void reset(char[][][] IM, char[][][] Substrates, char[][][] IM2){
	
		for(int i = 0; i < 500; i++){
		
		    for(int j = 0; j < 500; j++){
            for(int k = 0; k < 500; k++){
		    
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
	
	public static int checknb(char[][][] EnzymeOne_copy, int row, int col, int k){
		int count_nb = 0;
		if(EnzymeOne_copy[row-1][col-1][k-1] == 'X'){
			count_nb++;
		}
        if(EnzymeOne_copy[row-1][col+1][k-1] == 'X'){
			count_nb++;
		}
        if(EnzymeOne_copy[row+1][col-1][k-1] == 'X'){
			count_nb++;
		}
        if(EnzymeOne_copy[row+1][col+1][k-1] == 'X'){
			count_nb++;
		}
        if(EnzymeOne_copy[row-1][col-1][k+1] == 'X'){
			count_nb++;
		}
        if(EnzymeOne_copy[row-1][col+1][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col-1][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col+1][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col][k-1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row-1][col][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col - 1][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col+1][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col][k-1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row-1][col][k-1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col+1][k-1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col-1][k-1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row-1][col][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col+1][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col-1][k] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row+1][col][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row-1][col][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col+1][k+1] == 'X'){
			count_nb++;
		}
		if(EnzymeOne_copy[row][col-1][k+1] == 'X'){
			count_nb++;
		}
		
		return count_nb;
		
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
