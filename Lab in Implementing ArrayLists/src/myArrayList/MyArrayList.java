package myArrayList;

import java.lang.Iterable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * re-implementation of an ArrayList
 * 
 * @author walker, Amanda Hinchman, Greg Garcia
 */
public class MyArrayList<E> implements Iterable<E> {

	private E myArray[];
	private int numStored; // the number of items actually stored in myArray

	public MyArrayList() {
		// initially, the internal array has room for 10 items,
		// but no items have actually been inserted
		myArray = (E[]) new Object[10];
		numStored = 0;
	}

	/*
	 * add(E) preconditions: input must be a valid object postconditions:
	 * inserts a a given element into an array, but checks to see if there's
	 * enough room
	 */
	public void add(E e) {
		// check if myArray must be expanded
		if (numStored == myArray.length) {
			/* the following code follows approach A in the lab */
			E newArray[] = (E[]) new Object[myArray.length + 1];
			for (int i = 0; i < numStored; i++)
				newArray[i] = myArray[i];
			myArray = newArray;
		}

		// insert the given element into myArray
		myArray[numStored] = e;
		numStored++;
	}// add(int, E)

	/*
	 * add(E) preconditions: input must be a valid object postconditions:
	 * inserts a a given element into an array, but checks to see if there's
	 * enough room, but expands the element by 10 if there isn't enough room
	 */
	public void addPartB(E e) {
		// check if myArray must be expanded
		if (numStored == myArray.length) {
			/* the following code follows approach A in the lab */
			E newArray[] = (E[]) new Object[myArray.length + 10];
			for (int i = 0; i < numStored; i++)
				newArray[i] = myArray[i];
			myArray = newArray;
		}

		// insert the given element into myArray
		myArray[numStored] = e;
		numStored++;
	}// addPartB(int, E)

	/*
	 * add(E) preconditions: input must be a valid object postconditions:
	 * inserts a a given element into an array, but checks to see if there's
	 * enough room, but expands the element by twice the size if there isn't
	 * enough room
	 */
	public void addPartC(E e) {
		// check if myArray must be expanded
		if (numStored == myArray.length) {
			/* the following code follows approach A in the lab */
			E newArray[] = (E[]) new Object[myArray.length * 2];
			for (int i = 0; i < numStored; i++)
				newArray[i] = myArray[i];
			myArray = newArray;
		}

		// insert the given element into myArray
		myArray[numStored] = e;
		numStored++;
	}// addPartC(int, E)

	public void add(int index, E e) {
		// check if myArray must be expanded
		if (numStored == myArray.length) {
			/* the following code follows approach A in the lab */
			E newArray[] = (E[]) new Object[myArray.length + 1];
			boolean placed = false;
			for (int i = 0; i < numStored; i++) {
				newArray[i] = myArray[i];
			}
			myArray = newArray;
		}

		// insert the given element into myArray
		myArray[numStored] = e;
		numStored++;
	}// add(int, E)

	E get(int index) {
		return myArray[index];
	}

	public void print() {
		System.out.println("Listing of array elements");
		int i;
		/*
		 * only cycle through array elements with stored data
		 */
		for (i = 0; i < numStored; i++) {
			System.out.println("\t" + myArray[i]);
		}
		System.out.println("End of listing");

	}

	// Iterator material starts here
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}

	private class MyArrayListIterator implements Iterator<E> {
		// Taken from MyArray2.java example
		private int count;

		//set the counter for the iterator
		/*
		 * @preconditions none
		 * @postconditions the method gets the count
		 */
		public MyArrayListIterator() {
			count = 0;
		}
		
		//checks if there's a next available value
		/*
		 * @preconditions none
		 * @postconditions the method compares the count with the numStored
		 */
		public boolean hasNext() {
			return count < numStored;
		}

		//iterates to the next element
		/*
		 * @preconditions none
		 * @postconditions increments the iteration if there's room in the
		 *		 array
		 */
		public E next() {
			if (count == myArray.length) {
				throw new NoSuchElementException();
			}
			return (E) myArray[count++]; // ++ fix added according to Forum
		}
		
		/*
		 * remove()
		 * @preconditions none
		 * @postconditions size of the array decrements as an element is 
		 * 	removed
		 */
		public void remove() {
			if (count < 0)
				throw new IllegalStateException();

			try {
				while (hasNext()) {
					next(); // keep going through the array
				}
				myArray[count] = null; //set last element to null
				count = -1;// decrement the size of the array
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}//remove()

		// method added per Forum's discussion
		public void reset() {
			count = 0;
		}
	}

	public static void main(String args[]) {
		MyArrayList<Integer> intArr = new MyArrayList<Integer>();
		MyArrayList<String> strArr = new MyArrayList<String>();

		/*
		 * test of constructor
		 */
		System.out.println("Initialized array lists");
		System.out.print("Integer:  ");
		intArr.print();
		System.out.print("String:  ");
		strArr.print();

		/*
		 * add some items to array list
		 */
		for (int i = 1; i <= 22; i++)
			intArr.add(i);

		/*
		 * check contents of array list
		 */
		strArr.add("a");
		strArr.add("bc");
		strArr.add("def");
		strArr.add("ghij");
		strArr.add("klmno");

		System.out.println("Array lists with 22 and 5 items, respectively");
		System.out.print("Integer:  ");
		intArr.print();
		System.out.print("String:  ");
		strArr.print();

		/*
		 * check iterator construction and use
		 */

		System.out.println("print integer data via iterator");
		for (Integer intItem : intArr) {
			System.out.println("\t" + intItem);
		}

		System.out.println("print String data via iterator");
		for (String strItem : strArr) {
			System.out.println("\t" + strItem);
		}
	}
}