import java.util.Arrays;

/*
 * Lucas Burkowski
 * https://github.com/LucasBurkowski/COSC-311/tree/master/COSC%20311%20HMWRK%201-5
 * COSC 311
 * HW 01/05
 * WINTER 2017
 */
public class PrefixSums {
	public static void main(String[] args){
		int[] testArray = {0, 2, 4, 6, 1};
		System.out.println(Arrays.toString(Prefix(testArray)));
	}
	
	public static int[] Prefix(int[] array){
		for(int i = 1; i < array.length; i++){
			array[i] = array[i] + array[i - 1];
		}
		return array;
	}
}
