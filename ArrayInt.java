package telran.util;

import java.util.Arrays;

public class ArrayInt {

//searches for index of a given number in a given array
public static int search(int ar[], int number) {
	for(int i = 0; i < ar.length; i++) {
		if (ar[i] == number) {
			return i;
		}
	}
	return -1;
}
	
//sorting of numbers
public static void sort(int[] ar) {
		boolean flSort = false;
		int length = ar.length;
		do {
			flSort = true;
			length--;
			for (int i = 0; i < length; i++) {
				if (ar[i] > ar[i + 1]) {
					int tmp = ar[i];
					ar[i] = ar[i + 1];
					ar[i + 1] = tmp;
					flSort = false;
				}
			}
			
			
		}while (!flSort);
	}

//searches for index of a given number in a given sorted array
public static int binarySearch(int[] ar, int number) {
		int left = 0;
		int right = ar.length - 1;
		int middle = (left + right) / 2;
		while (left <= right && ar[middle] != number) {
			if (number < ar[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		int res = left > right ? -(left + 1) : middle;
		if (res >= 0) {
			//searching for first index of the equaled values
			while(res >= 0 && ar[res] == number) {
				res--;
			}
			res++;
		}
		return res;
	}

//Count Number in Sorted Array
public static int countSorted(int ar[], int number) {
		int ind = binarySearch(ar, number);
		int count = 0;
		if (ind >= 0) {
			while(ind < ar.length && ar[ind] == number) {
				count++;
				ind++;
			}
		}
		return count;
	}
	
//Insert Number by Index in Array
public static int [] insert (int ar[], int index, int number) {
		int res[] = ar;
		if (index >= 0 && index <= ar.length) {
			res = new int[ar.length + 1];
			System.arraycopy(ar, 0, res, 0, index);
			System.arraycopy(ar, index, res, index + 1, ar.length - index);
			res[index] = number;
		}
		return res;
	}
	
//Remove index 
public static int[] remove(int[] ar, int index) {
		int res[] = ar;
		if (index >= 0 && index < ar.length) {
			res = new int[ar.length - 1];
			System.arraycopy(ar,0,res,0,index);
			System.arraycopy(ar, index + 1, res, index, res.length - index);
		}
		return res;
	}

//Insert Number in Sorted Array	
public static int[] insertSorted(int[] ar, int number) {
		int index = binarySearch(ar, number);
		if (index < 0) { 
			index = -index - 1;
		}
		int res[] = insert(ar, index, number);
		return res;
	}
	
//array containing numbers of first and second arrays with no repetitions 
public static int[] union (int ar1[], int ar2[]) {
	int i1=ar1.length;
	int i2=ar2.length;
//	System.out.println(Arrays.toString(ar1));
//	System.out.println(Arrays.toString(ar2));
	for(int i=0; i<i1; i++) {
		int num = 0;
		num = ar1[i];
//		System.out.printf("num=%d \n", num);
		int inull = search(ar2,num);
//		System.out.printf("inull=%d \n", inull);
		if (inull == -1) {
			//ar2=ar2; 
//		System.out.println(Arrays.toString(ar2));
		}
		else if (ar2[inull] == num ) {
			ar2 = remove(ar2, inull);
//			 System.out.println(Arrays.toString(ar2));
			}
		}
	int tmp	[] = new int[ar1.length + ar2.length];
	i2=ar2.length;
	System.arraycopy(ar1, 0, tmp, 0, i1);
	System.arraycopy(ar2, 0, tmp, i1, i2);
//	System.out.println(Arrays.toString(tmp));
	return tmp;
	}
	
//array containing common numbers between first and second arrays with no repetitions
public static int[] intersection (int ar1[], int ar2[]) {
		
		int i1=ar1.length;
				
		for(int i=0; i<i1; i++) {
			int num = 0;
			num = ar1[i];
			//System.out.printf("num=%d \n", num);
			int inull = search(ar2,num);
			//System.out.printf("inull=%d \n", inull);
			if (inull == -1) {
				ar1 = remove(ar1, i);
				i--;
				i1--;
			//System.out.println(Arrays.toString(ar1));
			}
			else if (ar2[inull] == num ) {
				//ar1 = ar1;
			//System.out.println(Arrays.toString(ar1));
				}
			}

		return ar1;
}
		
//array containing numbers of first array that are not repeated in the second
public static int[] difference (int ar1[], int ar2[]) {
		
		//если число первого массива присутствует во втором,, удалить. Если нет - оставить.
		
		int i1=ar1.length;
		int res [] = ar1;
		i1=res.length;
		System.out.println(Arrays.toString(ar1));
		System.out.println(Arrays.toString(ar2));
		
		for(int i=0; i<i1; i++) {
			int num = 0;
			num = res[i];
			System.out.printf("num=%d \n", num);
			int inull = search(ar2,num);
			System.out.printf("inull=%d \n", inull);
			if (inull == -1) {
				//res = res;
				//i--;
				//i1--;
			System.out.println(Arrays.toString(res));
			}
			else if (ar2[inull] == num ) {
				res = remove(res, i);
				i--;
				i1--;
			System.out.println(Arrays.toString(res));
		}
		}return res;
		
	}

//Standart method array containing numbers of first and second arrays 
public static int[] StUnion (int ar1[], int ar2[]) {
	int res[] = Arrays.copyOf(ar1, ar1.length + ar2.length);
	int resInd = ar1.length;
	Arrays.sort(ar1);
	for (int i = 0; i < ar2.length; i++) {
		if (Arrays.binarySearch(ar1, ar2[i]) < 0) {
			res[resInd++] = ar2[i];
		}
	}
	return Arrays.copyOf(res, resInd);
}

//Atandart method array containing common numbers between first and second arrays with no repetitions 
public static int[] StIntersection (int ar1[], int ar2[]) {
	int res[] = new int[Math.min(ar1.length, ar2.length)];
	int resInd = 0;
	for (int i = 0; i < ar1.length; i++) {
		if (search(ar2, ar1[i]) >= 0) {
			res[resInd++] = ar1[i];
		}
	}
	return Arrays.copyOf(res, resInd);
}

//Standart method array containing numbers of first array that are not repeated in the second
public static int[] StDifference (int ar1[], int ar2[]) {
	int[] res = new int[ar1.length];
	int resInd = 0;
	for (int i = 0; i < ar1.length; i++) {
		if (search(ar2, ar1[i]) < 0) {
			res[resInd++] = ar1[i];
		}
		
	}
	return Arrays.copyOf(res, resInd);
}


}

