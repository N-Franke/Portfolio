import java.util.ArrayList;

public class ProbabilityGenerator<T> {
		//alphabet array
		ArrayList<T> uniqueArray = new ArrayList<T>();
		//count array
		ArrayList<Integer> countArray = new ArrayList<Integer>(); 
			
	public void train(ArrayList input) {
		
		//for loop  to cycle through arrays
		for(int i =0; i< input.size(); i++) 
		{
			//index of unique array
			int ans = uniqueArray.indexOf(input.get(i));
			if (ans  == -1)
				//if index is empty, add the unique character to the array
				//add one to the end of the count array
			{
				uniqueArray.add((T) input.get(i));
				countArray.add(1);
			}
			else 
			{
				//else increase current index by one
				countArray.set(ans, countArray.get(ans)+1);
			}
		}
		
		//print out info to console
//		System.out.println("Input from Song" + input);
//		System.out.println("Unique Array" + uniqueArray);
//		System.out.println("Count Array" + countArray);
//	
	} 
	
	//this function generates one note randomly created based on a song
	public T generate() {
		//Part 1, Calculate total and normalize
		ArrayList<Float> normalize = new ArrayList<Float>();
		int sum =0;
		//this for loop counts the total
		for(int i=0; i < countArray.size(); i++) {
			sum = countArray.get(i) +  sum;
		}
		//this for loop normalizes each number in the array
		for (int i =0; i <countArray.size(); i++) {
			normalize.add((float) countArray.get(i)/sum);
			//System.out.println("Token " + uniqueArray.get(i) + " Probability: " +normalize.get(i));
		}
		
		//generate random melody
		T gen = null;
		int index =0;
		float total  = 0; //sum of all the normalize values
		float rIndex = (float)Math.random();  //random index number to generate new values from
		boolean found = false;
		//this while loop compares the random number
		//checks which note it should produce based on the number created 
		while (!found && index<countArray.size()-1) {
			total+= normalize.get(index);
			found = rIndex <= total;
			index++;
		}
		if(found == true) {
			gen = (uniqueArray.get(index-1));
		} else {
			gen = (uniqueArray.get(index));
		}
		
		System.out.println("rIndex" + rIndex);
		System.out.println(gen);
		return gen; 
	}
	
	//this function prints out a series of notes dependent on the input
	public ArrayList<T> generateMult(int number) {
		//Part 1
		ArrayList<Float> normalize = new ArrayList<Float>();
		int sum =0;
		//this loop adds for the total
		for(int i=0; i <countArray.size(); i++) {
			sum = countArray.get(i) +  sum;
		}
	 	//this loop creates the normalize array
		for (int i =0; i <countArray.size(); i++) {
			normalize.add((float) countArray.get(i)/sum);
			//System.out.println("Token " + uniqueArray.get(i) + " Probability: " +normalize.get(i));
		}
		
		//generate random melody
		ArrayList<T> gen = new ArrayList<T>();
		//for loop checks the random number and returns a note based
		//on the normalize array
		for (int j =0; j<number; j ++) {
		int index =0;
		boolean found = false;
		float total  = 0;
		float rIndex = (float)Math.random();
		while (!found && index<countArray.size()-1) 
		{
			total += normalize.get(index);
			found = rIndex <=total;
			index++;
			
		}
		if (found ==true) {
			gen.add(uniqueArray.get(index-1));
		}
		else {
			gen.add(uniqueArray.get(index));
		}
			
		}
		//System.out.println(gen);
		return gen; 
	}
	
	public void probabilityDistribution() {
		ArrayList<Float> normalize = new ArrayList<Float>();
		int sum =0;
		//this for loop counts the total
		for(int i=0; i <  countArray.size(); i++) {
			sum = countArray.get(i) +  sum;
		}
		//this for loop normalizes each number in the array
		for (int i =0; i <countArray.size(); i++) {
			normalize.add((float) countArray.get(i)/sum);
			System.out.println("Token " + uniqueArray.get(i) + " Probability: " +normalize.get(i));
		}
	}
	
	public void melodyGenerator(ArrayList<T> genList) {
		System.out.println(genList);
	}
	
}
