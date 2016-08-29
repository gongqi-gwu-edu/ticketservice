package com.qigong.ticketservice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TicketServiceWebApplicationTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TicketServiceWebApplicationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TicketServiceWebApplicationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
