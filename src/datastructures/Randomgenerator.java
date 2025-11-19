package datastructures;

import java.util.Random;

public class Randomgenerator {

     private Random random;

	    public Randomgenerator(long seed) { // Using a seed ensures the random generator produces the same sequence every run.
	        this.random = new Random(seed); // Start random generator 
	    }

	    public int[] generate(int number, int max) { // number: the number of elements to generate.	 max: the values will be between 0 and max.   	
	        int[] array = new int[number];           // Create an array.
	        for (int i = 0; i < number; i++) {         
	            array[i] = random.nextInt(max + 1);  // assign random values using for loop
	        }
	        return array; //return the generated array
	    }

}
