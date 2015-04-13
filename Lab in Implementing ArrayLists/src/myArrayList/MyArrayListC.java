package myArrayList;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * re-implementation of an ArrayList  
 * @author walker
 */
public class MyArrayListC <E>  implements Iterable<E>{
        
   private E myArray [];
    private int numStored;  // the number of items actually stored in myArray
 
    public MyArrayListC ()
        {
        // initially, the internal array has room for 10 items,
        // but no items have actually been inserted
        myArray = (E[]) new Object [10];
        numStored = 0;  
        }
    
    /*
     * add(E)
     * preconditions: input must be a valid object
     * postconditions: inserts a a given element into an array, but checks
     *     to see if there's enough room
     */
    public void add(E e)
    {
       // check if myArray must be expanded
       if (numStored == myArray.length)
          {
              /* the following code follows approach C in the lab */
             E newArray [] = (E[]) new Object [myArray.length * 2];
             for (int i = 0; i < numStored; i++)
                 newArray[i] = myArray[i];
             myArray = newArray; 
          }

    // insert the given element into myArray
    myArray[numStored] = e;
    numStored++;
    }// addPartC(int, E)
    
    /*
     * add(int, E)
     * preconditions: index is not out of range, e is a valid value
     * postconditions: the given element is specified at a certain
     *     position
     */
    public void add (int index, E e)
    {
    	// check if index is in the range of the current list
    	if(index > numStored)
    		add(e);
        // check if myArray must be expanded
    	else if (numStored == myArray.length)
        {
            /* the following code follows approach C in the lab */
           E newArray [] = (E[]) new Object [myArray.length * 2];
           for (int i = 0; i < numStored; i++)
               newArray[i] = myArray[i];
           myArray = newArray; 
        }
    	for(int i = numStored; i > index; i--)
    		myArray[i] = myArray[i - 1];
    	myArray[index] = e;
    	numStored++;
    }//add(int, E)
    
    E get (int index)
    {
        return myArray[index];
    }
    
    public void print ()
    {
        System.out.println ("Listing of array elements");
        int i;
        /*
         * only cycle through array elements with stored data 
         */
        for (i = 0; i < numStored; i++)
        {
                System.out.println ("\t" + myArray[i]);
        }
        System.out.println ("End of listing");
        
    }
    
        // Iterator material starts here
        public Iterator<E> iterator() {
            return new MyArrayListIterator ();
        }
         
        private class MyArrayListIterator implements Iterator<E>
        {
            // Taken from MyArray2.java example
        	private int count;
    		
    		public MyArrayListIterator () {
    			count=0;
    		}

    		public boolean hasNext() {
    			return count < numStored;
    		}
    		 
    		public E next() {
    			if(count == myArray.length){
    				throw new NoSuchElementException();
    			}
    			return (E) myArray[count++]; // ++ fix added according to Forum
    		}	

    		public void remove() {
    			throw new UnsupportedOperationException();
    		}
    		
    		// method added per Forum's discussion
    		public void reset() {
    			count = 0;
    		}
        }

    public static void main (String args [])
    {
        MyArrayListC <Integer> intArr = new MyArrayListC <Integer>();
        MyArrayListC <String> strArr = new MyArrayListC <String>();
                
        /*
         * test of constructor
         */
        System.out.println ("Initialized array lists");
        System.out.print("Integer:  ");
        intArr.print();
        System.out.print("String:  ");
        strArr.print();
        
        /*
         * add some items to array list
         */
        for (int i = 1; i <= 22; i++)
                intArr.add(i);
        intArr.add(5, 999999);

        /*
         * check contents of array list
         */
        strArr.add("a");
        strArr.add("bc");
        strArr.add("def");
        strArr.add("ghij");
        strArr.add("THISisC");
        strArr.add(2, "******");
        
        System.out.println ("Array lists with 22 and 5 items, respectively");
        System.out.print("Integer:  ");
        intArr.print();
        System.out.print("String:  ");
        strArr.print();
        
        /*
         * check iterator construction and use
         */
   
        System.out.println ("print integer data via iterator");
        for (Integer intItem: intArr)
        {
                System.out.println ("\t" + intItem);
        }
        
        System.out.println ("print String data via iterator");
        for (String strItem: strArr)
        {
                System.out.println ("\t" + strItem);
        }
    }   
}