package com.randomnoun.common;

/* (c) 2013 randomnoun. All Rights Reserved. This work is licensed under a 
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution 3.0 Unported License</a>.
 */

/** Throwaway class used in ExceptionUtilsTest.
 *
 * @see java.lang.Throwable#getStackTrace()
 * 
 * @author knoxg
 * @blog http://www.randomnoun.com/wp/2012/12/17/marginally-better-stack-traces/
 * @version $Id$
 * 
 */
public class Junk {
    /** A revision marker to be used in exception stack traces. */
    public static final String _revision = "$Id$";
	
    static void a() throws HighLevelException {
        try {
            b();
        } catch(MidLevelException e) {
            throw new HighLevelException(e);
        }
    }
     
    static void b() throws MidLevelException {
        c();
    }
     
    static void c() throws MidLevelException {
        try {
            d();
        } catch(LowLevelException e) {
            throw new MidLevelException(e);
        }
    }
     
    static void d() throws LowLevelException {
       e();
    }
     
    static void e() throws LowLevelException {
        throw new LowLevelException();
    }
    
    public static class InnerClassB1 {
    	// we normally wouldn't revision inner classes
        // public static String _revision = "Test revision B1";

        public static void f() throws LowLevelException {
            InnerClassB2.g();
        }

        public static class InnerClassB2 {
        	// we normally wouldn't revision inner classes
            // public static String _revision = "Test revision B2";

            public static void g() throws LowLevelException {
                throw new LowLevelException();
            }
        }
    }
}

/** Utility exception class */
class HighLevelException extends Exception {
    HighLevelException(Throwable cause) { super(cause); }
}

/** Utility exception class */
class MidLevelException extends Exception {
    MidLevelException(Throwable cause)  { super("<middle>", cause); }
}

/** Utility exception class */
class LowLevelException extends Exception {
}