import java.util.ArrayList;

/*
 * Simple demonstration of Mergesort. 
 * Sorts in ascending order.
 */
public class MergeSortExample {

    private int[] numbers;

    public MergeSortExample(int[] toSort) {
		numbers = toSort;
    }

    // Add the item to the array.
    public void addToArray(int[] array, int toAdd) {
    	for(int i = 0; i < array.length; i++) {
    		if(array[i] == 0) {
    			array[i] = toAdd;
    			break;
    		}
    	}
    }

    //Recursive method to keep splitting the array into left and right sides, then merge it in order.
    public int[] mergeSort(int[] nums) {
    	int length = nums.length;

    	if(length <= 1) {
    		return nums;
    	}

    	ArrayList<Integer> leftList = new ArrayList<Integer>();
    	ArrayList<Integer> rightList = new ArrayList<Integer>();

    	// int[] left = new int[length / 2];
    	// int[] right = new int[length / 2];

    	for(int i = 0; i < length; i++) {
    		if(i < length / 2) {
    			//addToArray(left, nums[i]);
    			leftList.add(nums[i]);
    		} else {
    			//addToArray(right, nums[i]);
    			rightList.add(nums[i]);
    		}
    	}

    	int[] left = leftList.stream().mapToInt(Integer::intValue).toArray();
    	int[] right = rightList.stream().mapToInt(Integer::intValue).toArray();


    	left = mergeSort(left);
    	right = mergeSort(right);

    	return merge(left, right);
    }

    /**
     * Returns the array, minus the first element.
     */
    public int[] arraySansFirst(int[] toRemove) {
    	int length = toRemove.length;
    	int[] toReturn = new int[length - 1];

    	for(int i = 1; i < length; i++ ) {
    		toReturn[i - 1] = toRemove[i];
    	}

    	return toReturn;
    }

    //Merge the left and right sub-arrays.
    public int[] merge(int[] left, int[] right) {
    	int[] sorted = new int[numbers.length];

    	while(left.length != 0 && right.length != 0) {
    		if(left[0] <= right[0]) {
    			addToArray(sorted, left[0]);
    			left = arraySansFirst(left);
    		} else {
    			addToArray(sorted, right[0]);
    			right = arraySansFirst(right);
    		}
    	}

    	while(left.length > 0) {
    		addToArray(sorted, left[0]);
    		left = arraySansFirst(left);
    	}

    	while(right.length > 0) {
    		addToArray(sorted, right[0]);
    		right = arraySansFirst(right);
    	}

    	return sorted;
    }

    //Used to print the given array.
    public void print(int[] toPrint) {
		for(int i = 0; i < toPrint.length; i++) {
	    	System.out.print(toPrint[i] + " ");
		}
    }


    public static void main(String[] args) {
	int[] toSort = {1, 10, 8, 3, 9, 50, 24, 2, 55, 60, 9};
	MergeSortExample sorter = new MergeSortExample(toSort);

	toSort = sorter.mergeSort(toSort);
	sorter.print(toSort);

    }
}
