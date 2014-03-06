
public class DefineArray {
	private long[] array;
	private int cellNumber;
	
	public DefineArray(int size) {
		this.array = new long[size];
		this.cellNumber = 0;
	}
	
	// +++ ARRAY +++
	
	public int getCellNumber() {
		return this.cellNumber;
	}
	
	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	public long getElement(int index) {
		return this.array[index];
	}
	
	public void setElement(long value) { // if we add an element the size is +1
		this.array[getCellNumber()] = value;
		setCellNumber(getCellNumber() + 1);
	}
	
	public void displayArray() {
		StringBuilder sb = new StringBuilder();
		
		System.out.println("The elements of array are:");
		
		for (int index = 0; index < getCellNumber(); index++) {
			//System.out.print(getElement(index) + " ");
			sb.append(getElement(index) + " ");
		}
		System.out.println(sb.toString());
		System.out.println();
	}
	
	public void insertElement(long value) { // might use binary search if sorted
		setElement(value);
	}
	
	public boolean deleteElement(long value) { //deleting using linearSearch
		int index = linearSearch(value);
		
		if ( index == getCellNumber())
			return false;
		else {
			for (int i = index; i < getCellNumber(); i++) {
				this.array[i] = getElement(i+1);
			}
			setCellNumber(getCellNumber()-1);
			return true;
		}
	}
	
	public void swapElement(int i, int j) {
		long temp = getElement(i);
		this.array[i] = getElement(j);
		this.array[j] = temp;
	}
	
	// +++ SEARCH +++
	
	public int linearSearch(long value) {
		int index;
		for (index = 0; index < getCellNumber(); index++) {
			if (getElement(index) == value)
				break;
		}
		if (index == getCellNumber()) // if no element -> return CellNumber
			return getCellNumber();
		else
			return index;
	}
	
	public int binarySearch(long value) {
		int lowIndex = 0;
		int highIndex = getCellNumber()-1;
		int cursor;
		System.out.println("!binarySearch only works on sorted lists!");
		
		cursor = (lowIndex + highIndex) / 2;
		while (lowIndex <= highIndex && getElement(cursor) != value) {
				if (getElement(cursor) < value) // if greater -> new interval
					lowIndex = cursor +1;
				else if (getElement(cursor) > value) // if less -> new interval
					highIndex = cursor -1;
				cursor = (lowIndex + highIndex) / 2;
		}
		if (lowIndex <= highIndex)
			return cursor;
		else  // if there's no such element
			return getCellNumber();
	}
	
	// +++ SORTING +++
	
	public void bubbleSort() { // O(N^2)
		for (int j = getCellNumber(); j > 0; j--) {
			for (int i = 0; i < j-1; i++) {
				if (getElement(i) > getElement(i+1))
					swapElement(i, i+1);
			}
		}
	}
	
	public void selectionSort() { // select smallest, swap with first, O(N) swaps but O(N^2)
		int i, j;
		int minPos;
		
		for (i = 0; i < getCellNumber()-1; i++) {
			minPos = i;
			for (j = i; j < getCellNumber(); j++) {
				if (getElement(j) < getElement(minPos))
					minPos = j;
			}
			swapElement(i, minPos);
		}
	}
	
	public void insertionSort() { // move, good for partially sorted
		int i, j;
		long value;
		
		for (i = 1; i < getCellNumber(); i++) {
			value = getElement(i);
			for (j = i; j > 0 && getElement(j-1) >= value; j--) {
				this.array[j] = getElement(j-1);
			}
			this.array[j] = value;
		}
	}
	
	public void shellSort() { // insertion sort, N-sort, widely spaced elements, O(N*(logN)^2)
		// while (h <= cellNo/3)
		// h=h*3+1;
		int in, out;
		long tmp;
		int h = 1;                     
		
		while(h <= getCellNumber() / 3)
			h = h * 3 + 1;               

		while(h > 0) {
			for(out = h; out < getCellNumber(); out++) {
				tmp = array[out];
				in = out;

				while(in > h-1 && array[in - h] >= tmp) {
					array[in] = array[in - h];
					in = in - h;
				}
			array[in] = tmp;
			}  
		h = (h - 1) / 3;             
		}  
	} 
	
	public int partitionIt(int left, int right) { // partition: O(N)
		
		int pivot = (int)array[left];
	
		while (true) {	
			while (array[left] < pivot)
				left++;
			while (array[right] > pivot)
				right--;
			if (left >= right)
				return right;
			else 
				swapElement(left, right);
		}
	}
		
	public void quickSort(int left, int right) { //quicksort: O(N*logN)

			if (left < right) {
				int pivot = partitionIt(left, right);
				
				if (pivot > 1)
					quickSort(left, pivot-1);
				
				if (pivot+1 < right)
					quickSort(pivot+1, right);
			}
	}	
}
