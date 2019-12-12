package testingTutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;

import org.junit.Assume;
import org.junit.jupiter.api.Assertions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
    

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void test1()

    {

        Lecturer lecturer1 = new Lecturer(11,"lvxuexiang","20");

        Lecturer lecturer2 = new Lecturer(11,"lvxuexiang","21");

//        assertTrue(lecturer1.equals(lecturer2));
        
//        assertTrue();
        Assume.assumeTrue(lecturer1.equals(lecturer2));
        assertEquals(lecturer1.hashCode(), lecturer2.hashCode());

    }

    public void test2() {

        Student student = new Student(11,"lvxuexiang","20");

        Student student2 = new Student(11, "lvxuexiang", "20");
//
//        assertEquals(student, student2);
//
//        assertTrue(student.equals(student2));
//        
        Assume.assumeTrue(student.equals(student2));
        
        assertEquals(student.hashCode(), student2.hashCode());

    }

    public void test3() {

    	final String x = new String("name");

        HashMap<String, String> map = new HashMap<String, String>(){

        	{

        		put(x, "test");

        	}

        };

        assertTrue(map.containsKey("name"));

   }

    public void test4() {

    	final String x = new String("name");
    	final String y = new String("name");

        IdentityHashMap<String, String> map2 = new IdentityHashMap<String, String>(){

        	{

        		put(x, "test");

        	}

        };

        assertTrue(map2.containsKey(y));

    }

    public void test5() {

    	Collection<String> strings = new ArrayList<String>();

    	Collection<String> strings1 = Collections.unmodifiableCollection(strings);

//    		strings1.add("lv");
    		Assertions.assertThrows(UnsupportedOperationException.class,()->{strings1.add("1");});
//    		assertTrue(strings1 instanceof Iterable);

    }

}


