package com.google.common.collect;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
 
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
public class AbstractIteratorTest2 {
   
    //declare variables
    Iterator<Integer> iter;
    static ArrayList<Integer> list;
    static ListIterator<Integer> it;
 
    
    //the annotation @BeforeClass indicates that this method will be executed one time before the tests
    //this method initializes the arrayList and adds elements to it
    @BeforeClass
    public static void setUpBefore() {
        list = new ArrayList<Integer>();
        list.add(15);
        //list.add(12);
        //list.add(17);
        //list.add(42);
 
    }
 
    //the annotation Before is used to run this method before each test case
    //this method initalizes the AbstractIterator object, iter
    //and it implements the computeNext() method using a listIterator. If the arrayList has a next element, computeNext() will return the this element. If it doesn't computeNext() will call endOfData, which returns null 
    @Before
    public void setUp() {
        it = list.listIterator();
       
        iter = new AbstractIterator<Integer>() {
 
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
    public void positiveTest01() {
        assertTrue(iter.hasNext());//asserts that the method hasNext() will return true - the arrayList has one element
    }
   
    @Test
    public void positiveTest02() {
        assertTrue(iter.hasNext());
        assertEquals(15, (int) iter.next()); //asserts that the next element returned by the iterator is 15
        assertFalse(iter.hasNext()); //asserts that hasNext() will not return true after the element first element was computed, because the ArrayList doesn't have any elements lefts
    }
   
    @Test
    public void negativeTest01() {
        assertTrue(iter.hasNext());
        assertEquals(15, (int) iter.next());
        assertFalse(iter.hasNext());
       
        try {
            iter.next(); //tries to access the next element, after the iterator has reached endOfData()
            fail("no exception thrown"); //ensures that an expected exception should have been thrown
        } catch (NoSuchElementException expected) {
        }
    }  
 
}