import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Code {

	public static int counter;
	public static int mergeComparisions;
	public static int mergeMoves;
	static int[] numbersArray = new int[1024]; //Hard code size of array for QuickSort
	static int[] numbersArray2 = new int[1024]; //Reverse order of array for QuickSort
	static int[] numbersArray3 = new int[1024]; //Array for Merge Sort
	static String nameList[] = {"Zach", "Adam", "Bob","Thomas","Charley"}; 

//**************************************************************************************************************************
//Main Function
	public static void main(String[] args) throws FileNotFoundException {
		
			Code code = new Code();
			code.readInNumbersFromFile("1000Numbers.txt");    //read numbers from file method
			
			
			System.out.println("Zach Carroll - CPSC 320 - QuickSort & MergeSort Project.\n");
			//Part 1
			printArraySizes(numbersArray);			
			System.out.println("Quicksort ");
			/*int[] test = {7,5,3,4,2,6,1,8}; //best case - 24 compares
			int[] test1 = {8,7,6,5,4,3,2,1}; //2nd worst case - 31 compares
			int[] test2 = {1,2,3,4,5,6,7,8}; //worst case - 35 compares
			code.doQuickSort(test2);*/
			code.doQuickSort(numbersArray);
			System.out.println("Quicksort on Sorted Array");
			code.doQuickSort(numbersArray);
			System.out.println("Reverse Order of Array Quicksort");
			//code.reverseOrderArray(numbersArray2);
			//code.doQuickSort(numbersArray2);
			code.reverseOrderArray(numbersArray2);
			code.doQuickSort(numbersArray2);
			
			//Part 2
			/*int[] test = {7,5,3,4,2,6,1,8}; //best worst avg- 24 moves
			int[] test1 = {8,7,6,5,4,3,2,1}; //best worst avg- 24 moves
			int[] test2 = {1,2,3,4,5,6,7,8}; //best worst avg - 24 moves
			code.doMergeSort(test2);
			# Of Compares - C(n) = n lg n
			So 1,024 * 10 = 10,240 Compares for n = 1,024*/
			System.out.println("Mergesort ");
			code.doMergeSort(numbersArray3);
			System.out.println("Mergesort on Sorted Array");
			code.doMergeSort(numbersArray3);
			System.out.println("Reverse Order of Sorted Array Mergesort");
			code.reverseOrderArray(numbersArray3);
			code.doMergeSort(numbersArray3);
						
			
			//Part 3
			//Create modified methods for Quicksort and Partition - use interface Comparable(using method compareto) as a parementer, instead of integers.
			System.out.println("Alphabetical Sort using Quicksort & Partition");
			code.doQuickSort2(nameList);
	}
//**************************************************************************************************************************	
//Print Array size of N, size of N^2 and size of N Log N
	static void printArraySizes(int arr[]) 
    { 
		int len = arr.length;
		int inputSize = 0;
		for(int i =0; i<len; i++) {
			inputSize++;			
		}
		System.out.println("Input Size of n: " + NumberFormat.getNumberInstance(Locale.US).format(inputSize) + " \t- 1,024 Numbers in Text File"); //use Number Format to add commas
		System.out.println("Value for n^2: "+ NumberFormat.getNumberInstance(Locale.US).format((int)Math.pow(inputSize, 2))); //use Number Format to add commas
		System.out.println("n log n : 10,240\n" );
    } 
//**************************************************************************************************************************
//Read In File into Array Function
	public void readInNumbersFromFile(String filename) throws FileNotFoundException {
		//Read Numbers in from text file
		Scanner readNumbers = new Scanner(new File(filename)); //Scanner to read numbers from text file
		Scanner readNumbers2 = new Scanner(new File(filename)); //Scanner to read numbers from text file
		Scanner readNumbers3 = new Scanner(new File(filename)); //Scanner to read numbers from text file

			int i=0;
			while(readNumbers.hasNextInt() && i < 1024 ) {
				numbersArray[i++] = readNumbers.nextInt();			
			}
			
			int z=0;
			while(readNumbers2.hasNextInt() && z < 1024) {
				numbersArray2[z++] = readNumbers2.nextInt();			
			}
			
			int y=0;
			while(readNumbers3.hasNextInt() && y < 1024) {
				numbersArray3[y] = readNumbers3.nextInt();	
				y++;
			}
			
			
		readNumbers.close(); // Close Scanner
		readNumbers2.close(); // Close Scanner
		readNumbers3.close(); // Close Scanner
		
	}
//**************************************************************************************************************************
//Reverse Order of Array
	public void reverseOrderArray(int[] array) {
		int temp;
		for (int i = 0; i < array.length/2; i++) {
            temp = array[i];
            
            array[i] = array[array.length-1-i];
            
            array[array.length-1-i] = temp;
        }
        //System.out.println("Array Reversed: "+Arrays.toString(array));
	}

//**************************************************************************************************************************
//Quick Sort
//Worst Case O(n^2)
//Avg Case O(nlogn)
	//Do QuickSort
	public void doQuickSort(int[]array) {
		counter = 0;
		System.out.println(Arrays.toString(array));	
		quicksorti(array, 0, array.length - 1);
		System.out.println("Total Number of Compares: "+ NumberFormat.getNumberInstance(Locale.US).format(counter));
		System.out.println(Arrays.toString(array)+ "\n\n");	

	}
	public void quicksorti(int [] data, int min, int max) {
		int pivot = 0;
		if (min < max) {
			pivot = partitioni(data,min,max);
			
			quicksorti(data,pivot+1,max);
			quicksorti(data,min,pivot-1);
		}
	}
	private void swapi(int [] data, int index1, int index2) {
		int temp = data[index1];
		//swaps++;
		data[index1] = data[index2];
		//swaps++;
		data[index2] = temp;
		//swaps++;
	}
	private int partitioni(int[]data,int min, int max) {
		//select pivot.
		int pivot = data[min];
		int left = min;
		int right = max;
		
		//everything to the left of pivot is less than pivot
		//everything to the right of pivot is greater than pivot
		
		//1   2   3   <   5(Pivot) <    7    8    9 
		
		while(left < right) {
			//move left to right
			while(data[left]<= pivot && left < right) {
				left++;	
				counter++;
			}
			
			//move right to left
			while(data[right]>pivot) {
				right--;
				counter++;
			}
			
			if (left<right) {
				swapi(data,left,right);				
			}
		}
		swapi(data,min,right);
		//return pivot position
		return right;	
	}
//**************************************************************************************************************************	
//
//Use Quicksort and Partition to sort String array.
//Implement Strings inplace of int values
//Use compare to function	
	public void doQuickSort2(String[]array) {
		System.out.println(Arrays.toString(array));	
		quicksorti2(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array)+ "\n\n");	

	}
	public void quicksorti2(String [] data, int min, int max) {
		int pivot = 0;
		if (min < max) {
			pivot = partitioni2(data,min,max);
			
			quicksorti2(data,pivot+1,max);
			quicksorti2(data,min,pivot-1);
		}
	}
	
	private void swapi2(String[] data, int index1, int index2) {
		String temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	private int partitioni2(String[]data,int min, int max) {
		//select pivot.
		String pivot = data[min];
		int left = min;
		int right = max;
		
		//everything to the left of pivot is less than pivot
		//everything to the right of pivot is greater than pivot
		
		//1   2   3   <   5(Pivot) <    7    8    9 
		
		while(left < right) {
			//move left to right
			while(data[min].compareTo(pivot)<= 0 && left < right) {
				left++;	
				counter++;
			}
			
			//move right to left
			while(data[min].compareTo(pivot)>0) {
				right--;
				counter++;
			}
			
			if (data[min].compareTo(pivot)<right) {
				swapi2(data,left,right);				
			}
		}
		swapi2(data,min,right);
		//return pivot position
		return right;	
	}	

//**************************************************************************************************************************	
//Merge Sort
	public void doMergeSort(int[] array) {
		mergeMoves = 0;
		mergeComparisions = 0;
		System.out.println(Arrays.toString(array));	
		mergeSorti(array, 0,array.length-1);		
		System.out.println("Total Number of Compares: "+ NumberFormat.getNumberInstance(Locale.US).format(mergeComparisions));
		//System.out.println("Total Number of Moves: "+ NumberFormat.getNumberInstance(Locale.US).format(mergeMoves));
		System.out.println("Total Number of Moves: "+ NumberFormat.getNumberInstance(Locale.US).format(mergeMoves*2));
		System.out.println(Arrays.toString(array)+ "\n\n");	
	}
	public void mergeSorti(int[]data, int min, int max ) {
		if(min < max) {
			int mid = (min + max) / 2; //split point
			mergeSorti(data,min,mid);
			//mergeMoves++;
			mergeSorti(data,mid+1,max);
			//mergeMoves++;
			mergei(data,min,mid,max); //merge does actual sorting
			//mergeMoves++;
		}
	}
	public void mergei(int[]data, int first, int mid, int last) {
		int [] temp = new int[data.length];
		int first1 = first; //copy indeces for each subarray
		int last1 = mid;
		int first2 = mid + 1;
		int last2 = last;
		int index = first;
		//go through, element be element, adding smallest to temp array
		while(first1 <= last1 && first2<= last2) {
			//put smallest of subarrays into the next array location
			mergeComparisions++;
			if(data[first1]< data[first2] ) {
				temp[index] = data[first1];
				mergeMoves++;					
				first1++;				
			} else {
				temp[index] = data[first2];
				mergeMoves++;				
				first2++;
				
			}
			index++;
		}
		// if we finished one subarray, finish the remainder of the other
		while(first1<=last1) {
			temp[index]=data[first1];
			mergeMoves++;
			first1++;
			index++;
		}
		while(first2<=last2) {
			temp[index]=data[first2];
			mergeMoves++;
			first2++;
			index++;
		}
		//copy temp over to original array
		for(index=first;index<=last;index++) {
			data[index]=temp[index];
		}
	}
}
