package com.google.common.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.BeforeClass;
import org.junit.Test;

public class AbstractIteratorTest2 {
	
	//declare variables 
	Iterator<Integer> iter;
	static ArrayList<Integer> list; 
	static ListIterator<Integer> it; 

	//initialize arrayList and add elements to it
	//the assertion @BeforeClass indicates that this method will be executed one time before the tests
	@BeforeClass
	public static void setUpBefore() {
		list = new ArrayList<Integer>();
		list.add(15);
		//list.add(12);
		//list.add(17);
		//list.add(42);
		it = list.listIterator();

	}

	public void initIterator() {

		//initialize the Abstract iterator
		iter = new AbstractIterator<Integer>() {
			
			//override the method computeNext() using the ListIterator to iterate through the ArrayList
			@Override
			public Integer computeNext() {

				if (it.hasNext()) {
					return it.next();
				} else {
					return endOfData();
				}
			}
		};
	}

	@Test
	public void testIter() {
		initIterator();
		assertTrue(iter.hasNext()); //asserts that the method hasNext() will return true - the arrayList has one element  
		assertEquals(15, (int) iter.next()); //asserts that the next element returned by the iterator is 15
		assertFalse(iter.hasNext()); //asserts that hasNext() will not return true after the element first element was computed, because the ArrayList doesn't have any elements lefts
		
		try {
			iter.next(); //tries to access the next element, after the iterator has reached endOfData()
			fail("no exception thrown"); //ensures that an expected exception should have been thrown
		} catch (NoSuchElementException expected) {
		}
	}

}
