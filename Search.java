/**
 * Name: Patrick Espino
 * ID: 014254979
 * Lab: 1
 * 
 */

package lab1_328;

import java.util.Random;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Search {
	public static void main(String[] args)
	{
		//PART A
		System.out.println("Part A");
		System.out.println("Enter a positive integer");
		Scanner read = new Scanner(System.in);
		Random random = new Random();
		int n = read.nextInt();
		int[] arr = new int[n];
			for(int i = 0; i < n; i++)
			{
				arr[i] = random.nextInt(1000 + 1 + 1000) - 1000;
			}

		Arrays.sort(arr);

		long totalRunTimeB = 0;
		long duration1 = 0;
		int countB = 0;
		long duration2 = 0;
		long totalRunTimeL = 0;
		int countL = 0;
		int key = 0;
		int rand = 0;
		int limit = 500;
		int x = 10000000;
		int worse = 5000;
		
		while(countB < limit)
		{
			rand = (int) (Math.random()*n);
			key = arr[rand];
			long startTimeBinary = System.nanoTime();
			binSearch(arr,key);
			long endTimeBinary = System.nanoTime();
			duration1 = endTimeBinary - startTimeBinary;
			totalRunTimeB = totalRunTimeB + duration1;
			countB++;
		}
		long avgRunTimeB = (totalRunTimeB / limit);
		System.out.println("Average Run Time for BINARY: " + avgRunTimeB + " nanoseconds");
		
		while(countL < limit)
		{
			rand = (int) (Math.random()*n);
			key = arr[rand];
			long startTimeLinear = System.nanoTime();
			LinearSearch(arr,key);
			long endTimeLinear = System.nanoTime();
			duration2 = endTimeLinear - startTimeLinear;
			totalRunTimeL = totalRunTimeL + duration2;
			countL++;
		}
		long avgRunTimeL = (totalRunTimeL / limit);
		System.out.println("Average Run Time for LINEAR: " + avgRunTimeL + " nanoseconds");
		
		//PART B
		System.out.println("Part B");
		int[] arr2 = new int[100000];
		for(int i = 0; i < arr2.length; i++)
		{
			arr2[i] = random.nextInt(1000 + 1 + 1000) - 1000;
		}
		
		Arrays.sort(arr2);
		
		long startTimeB = System.nanoTime();
		binSearch(arr2,worse);
		long endTimeB = System.nanoTime();
		long endB = endTimeB - startTimeB;
		System.out.println("When n=10^5");
		System.out.println("Worst Case Binary: " + endB + " nanoseconds");
		
		long startTimeL = System.nanoTime();
		LinearSearch(arr2,worse);
		long endTimeL = System.nanoTime();
		long endL = endTimeL - startTimeL;
		System.out.println("Worst Case Linear: " + endL + " nanoseconds");
		
		int[] arr3 = new int[10000000];
		for(int i = 0; i < arr3.length; i++)
		{
			arr3[i] = random.nextInt(1000 + 1 + 1000) - 1000;
		}
		
		Arrays.sort(arr3);
		
		long startTimeB2 = System.nanoTime();
		binSearch(arr3,worse);
		long endTimeB2 = System.nanoTime();
		long endB2 = endTimeB2 - startTimeB2;
		System.out.println("When n=10^7");
		System.out.println("Worst Case Binary: " + endB2 + " nanoseconds");
		
		long startTimeL2 = System.nanoTime();
		LinearSearch(arr3,worse);
		long endTimeL2 = System.nanoTime();
		long endL2 = endTimeL2 - startTimeL2;
		System.out.println("Worst Case Linear: " + endL2 + " nanoseconds");
		
		//time it takes to run a single step between the averages of linear and binary
		double xbar = ((double) avgRunTimeL / n + (double)avgRunTimeB / (Math.log(n)/Math.log(2))) / 2;
		
		System.out.println("How long it takes my machine to run one single step(LINEAR): " + (double) avgRunTimeL / n);
		System.out.println("How long it takes my machine to run one single step(BINARY): " + (double) avgRunTimeB / Math.log(n)/Math.log(2));
		System.out.println("Estimated time for n=10^7(linear): " + (long) (xbar*x) );
		System.out.println("Estimated time for n=10^7(binary): " + (long) (xbar*(Math.log(x)/Math.log(2))));
	}
	
	public static boolean LinearSearch(int[] arr,int key)
	{
		int n = arr.length;
		for (int i=0; i < n; i++)
		{
			if (key == arr[i])
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean binSearch(int [] arr, int key)
	{
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end)
		{
			int mid = (start + end)/2;
			if(arr[mid] == key)
			{
				return true;
			}
			else if(arr[mid] < key)
			{
				end = mid - 1;
			}
			else if(arr[mid] > key)
			{
				start = mid + 1;
			}
		}
		return false;
	}
	
}
