//import java.util.ArrayList;
//
//public class MarkovGenerator<T> extends ProbabilityGenerator {
//	//declaring variables
//	//index
//	int lastIndex = -1; //start at -1 because the markov chain is beginning
//	//alphabet array
//	ArrayList<T> uniqueArray = new ArrayList<T>();
//	//count array
//	ArrayList<Integer> countArray = new ArrayList<Integer>(); 
//	//transition Table
//	ArrayList<ArrayList<Integer>> transitionTable = new ArrayList<ArrayList<Integer>>();
//	//transitionTable = new ArrayList();
//	
//	
//	//declaring functions
//	
//	public void train(ArrayList input) {
//	
//		for (int i=0; i<input.size(); i++) {
//		int tokenIndex = uniqueArray.indexOf(input.get(i));
//		//if index is less than 1, add to unique array
//		if(lastIndex == -1) {
//			//vertical expansion
//			tokenIndex = uniqueArray.size();  //size of alphabet 
//			ArrayList<Integer> row  = new ArrayList<Integer>(tokenIndex);  //new array  size of alphabet array
//			transitionTable.add(row);	//add to transitionTable
//			
//			//horizontal expansion  
//			row.add(0);
//			uniqueArray.add((T) input.get(i));
//			
//		}
//		//add counts to transitionTable
//		if(lastIndex > -1) {
//			ArrayList<Integer> row = transitionTable.get(lastIndex); //correct row
//			int access = row.get(tokenIndex);	//to find the correct column
//			row.set(tokenIndex, access + 1);	//to add to that column
//			}
//		lastIndex = tokenIndex;
//		}
//		for(int i=0; i<input.size(); i++) {
//			ArrayList<Integer> row = transitionTable.get(i);
//			System.out.println("Row " + row);
//			for(int j=0; j < transitionTable.size(); j++) {
//				Integer myElement = row.get(i);
//				System.out.print(" "  + myElement  + " ");
//			}
//		}
//		
//	}
//	
//}
