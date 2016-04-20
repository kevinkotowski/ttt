package com.eighthlight.kkotowski.ttt;

import junit.framework.TestCase;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class HelloTest extends TestCase {
    public void testTest() throws Exception {
        assertNull( null );
    }

    public void testGetName() throws Exception {
        Hello hello = new Hello();
        assertEquals( hello.getName(), "" );
//        assertNull( hello.getName().isEmpty() );

        hello.setName("Xippy");
    }

    public void testGetMessage() throws Exception {
//        assertEquals( hello.getName(), "Xippy" );
    }

    public void testSetName() throws Exception {

    }

}