import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/**
 * Test class for class Lists. JUnit4 version.
 *
 * @author  Uno Holmer
 * @version 2019-04-05
 */
public class JunitListTest {
    private final int noOfCases = 10;
    private String[] cases;         // Test case strings
    private final ListNode EMPTY;
    private static Random random = new Random();
    
    public JunitListTest() {
        cases = new String[noOfCases];
        for ( int size = 0; size < cases.length; size++ )
            cases[size] = randCase(size);
        EMPTY = Lists.toList("");
    }
        
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    private String randCase(int size) {
        StringBuilder b = new StringBuilder(size);
        for ( int i = 0; i < size; i++ )
            b.append("abcdefghijklmnopqrstuvwxyz".charAt(random.nextInt(26)));
        return b.toString();
    }
    
    private String randInjectUpper(String s) {
        StringBuilder b = new StringBuilder(s);
        int noOfUppers = random.nextInt(Math.max(s.length(),1));
        for ( int i = 0; i < noOfUppers; i++ ) {
            int pos = random.nextInt(b.length());
            b.insert(pos,"ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(26)));
        }  
        return b.toString();
    }
    
    // Methods for determining sharing properties.
    
    // Determines if l contains the node refered to by x.
    private boolean containsNode(ListNode l,ListNode x) {
        ListNode p = l;
        while ( p != null ) { 
            if ( p == x )
                return true; 
            p = p.next;
        }
        return false;
    }   
    
    // Determines if l1 and l2 have any shared nodes in common.
    private boolean hasSharedNodes(ListNode l1,ListNode l2) {
        ListNode p2 = l2;
        while ( p2 != null ) { 
            if ( containsNode(l1,p2) )
                return true;
            p2 = p2.next;
        }
        return false;
    }
    
    // Determines if l1 contains all the nodes in l2.
    private boolean hasAllNodes(ListNode l1,ListNode l2) {
        ListNode p2 = l2;
        while ( p2 != null ) { 
            if ( ! containsNode(l1,p2) )
                return false;
            p2 = p2.next;
        }
        return true;
    }
    
    // Returns true if l1 and l2 roots two truly separate structures,
    // false ow.
    public static boolean isDistinct(ListNode l1,ListNode l2) {
        return l1 == null && l2 == null ||
               (l1 != l2 && isDistinct(l1.next,l2.next));
    }
    
    //------------------- JUnit Test cases ----------------------
    
    // Notice: The comments below are not meant as rigorous specifications.
    // Their purpose are merely to clarify the general idea of the tests.
    
    @Test
    public void testEquals()
    {
        assertTrue(Lists.equals(EMPTY,EMPTY));
        assertTrue(Lists.equals(Lists.mkEmpty(),Lists.mkEmpty()));
        for ( String s : cases ) {
            if ( s.length() > 0 ) {
                ListNode l = Lists.toList(s);
                assertTrue(Lists.equals(l,l));
                assertTrue(Lists.equals(Lists.toList(s),Lists.toList(s)));
                assertFalse(Lists.equals(EMPTY,l));
                assertFalse(Lists.equals(l,EMPTY));
                ListNode lcpy = Lists.copy(l);
                Lists.addLast(lcpy,'X');
                assertFalse(Lists.equals(lcpy,l));
                assertFalse(Lists.equals(l,lcpy));
            }
        }
    }
    
    @Test(expected = ListsException.class)
    public void testEqualsNullLeftWithException() {
        Lists.equals(null,EMPTY);
    } 
    
    @Test(expected = ListsException.class)
    public void testEqualsNullRightWithException() {
        Lists.equals(EMPTY,null);
    }
       
    @Test
    public void testCopy()
    {
        for ( String s : cases ) {
            ListNode l = Lists.toList(s);
            ListNode lcpy = Lists.copy(l);
            assertTrue(Lists.equals(l,lcpy));
            assertTrue(Lists.equals(lcpy,l));
            assertFalse(hasSharedNodes(l,lcpy));
        }
    }
    
    @Test(expected = ListsException.class)
    public void testCopyWithException() {
         Lists.copy(null);
    } 
    
    @Test
    public void testRemoveAll()
    {
        // Lists.removeAll  = --
        // String removeAll = - (defined below)
        // (forall s in cases)(forall c in s)((toList(s))--c = toList(s-c))
        for ( String s : cases ) {
            for ( int i = 0; i < s.length(); i++ ) {
                char c = s.charAt(i);
                ListNode lhs = Lists.removeAll(Lists.toList(s),c);
                ListNode rhs = Lists.toList(stringRemoveAll(s,c));
                assertTrue(Lists.equals(lhs,rhs));
            } 
        }
        assertTrue(Lists.equals(EMPTY,Lists.removeAll(Lists.mkEmpty(),'X')));
    }
   
    @Test(expected = ListsException.class)
    public void testRemoveAllWithException() {
         Lists.removeAll(null,'x');
    }    
    
    private String stringRemoveAll(String s,char c) {
        StringBuilder b = new StringBuilder(s.length());
        for ( int i = 0; i < s.length(); i++ ) {
            char curChar =  s.charAt(i);
            if ( curChar != c )
                 b.append(curChar);
        }
        return b.toString();
    }
    
    @Test
    public void testToString()
    {
        for ( String s : cases )
            assertTrue(s.equals(Lists.toString(Lists.toList(s))));
    }
    
    @Test(expected = ListsException.class)
    public void testToStringWithException() {
         Lists.toString(null);
    }    
    
    @Test
    public void testContains()
    {
        for ( String s : cases ) {
            ListNode l = Lists.toList(s);
            for ( char c : s.toCharArray() )
                assertTrue(Lists.contains(l,c)); 
            assertFalse(Lists.contains(l,'X')); 
        }
        assertFalse(Lists.contains(EMPTY,'X'));
    }
    
    @Test(expected = ListsException.class)
    public void testContainsWithException() {
         Lists.contains(null,'x');
    }
    
    @Test
    public void testCopyUpperCase()
    {
        // Inject upper case letters randomly in the test strings.
        // Assert equal results when extracting the upper case chars from
        // the corresponding list, as wheen extracting them from the 
        // injected string itself.
        for ( String s : cases ) {
            String uppersAndLowers = randInjectUpper(s);
            // Extract the upper case characters
            StringBuilder uppers = new StringBuilder();
            for ( int i = 0; i < uppersAndLowers.length(); i++ ) {
                final char c = uppersAndLowers.charAt(i);
                if ( Character.isUpperCase(c) )
                    uppers.append(c);
            }
            ListNode temp = Lists.toList(uppersAndLowers);
            ListNode lhs = Lists.copyUpperCase(temp);
            assertFalse(hasSharedNodes(temp,lhs));
            ListNode rhs = Lists.toList(uppers.toString());
            assertTrue(Lists.equals(lhs,rhs));
        }
    }
    
    @Test(expected = ListsException.class)
    public void testCopyUpperCaseWithException() {
         Lists.copyUpperCase(null);
    }
    
    @Test
    public void testAddFirst()
    {
        // addFirst = :
        // (forall s in cases)(x:toList(s) = toList(x+s))
        for ( String s : cases )
            assertTrue(Lists.equals(Lists.addFirst(Lists.toList(s),'X'),
                       Lists.toList("X"+s)));
    }
    
    @Test(expected = ListsException.class)
    public void testAddFirstWithException() {
         Lists.addFirst(null,'x');
    }
    
    @Test
    public void testAddLast()
    {
        // addLast = +
        // (forall s in cases)(toList(s)+x = toList(s+x))
        for ( String s : cases )
            assertTrue(Lists.equals(Lists.addLast(Lists.toList(s),'X'),
                                    Lists.toList(s+"X")));
    }
    
    @Test(expected = ListsException.class)
    public void testAddLastWithException() {
         Lists.addLast(null,'x');
    }
  
    @Test
    public void testConcat()
    {
        // (forall s1,s2 in cases)(concat(toList(s1),toList(s2)) = toList(s1+s2))
        for ( String s1 : cases )
            for ( String s2 : cases ) {
                ListNode l1 = Lists.toList(s1);
                ListNode l2 = Lists.toList(s2);   
                ListNode l2nodes = l2.next;  // save ptr to content for alias test
                ListNode lhs = Lists.concat(l1,l2);
                assertTrue(Lists.equals(lhs,l1)); 
                assertTrue(Lists.equals(lhs,Lists.toList(s1+s2)));
                assertTrue(hasAllNodes(l1,l2nodes));
                assertTrue(Lists.isEmpty(l2));
            }
    }
    
    @Test(expected = ListsException.class)
    public void testConcatNullLeftWithException() {
        Lists.concat(null,EMPTY);
    }
    
    @Test(expected = ListsException.class)
    public void testConcatNullRightWithException() {
        Lists.concat(EMPTY,null);
    }
    
    @Test
    public void testAddAll()
    {
        // (forall s1,s2 in cases)(addAll(toList(s1),toList(s2)) = toList(s1+s2))
        for ( String s1 : cases )
            for ( String s2 : cases ) {
                ListNode l1 = Lists.toList(s1);
                ListNode l2 = Lists.toList(s2);
                ListNode l2nodes = l2.next;
                ListNode lhs = Lists.addAll(l1,l2);
                assertTrue(Lists.equals(lhs,l1)); 
                assertTrue(Lists.equals(lhs,Lists.toList(s1+s2))); 
                assertFalse(hasSharedNodes(lhs,l2));
                assertTrue(hasAllNodes(l2,l2nodes));
            }
    } 
    
    @Test(expected = ListsException.class)
    public void testAddAllNullLeftWithException() {
        Lists.addAll(null,EMPTY);
    }
    
    @Test(expected = ListsException.class)
    public void testAddAllNullRightWithException() {
        Lists.addAll(EMPTY,null);
    }
             
    @Test
    public void testReverse()
    {
        // addAll    = ++
        // addFirst  = :
        // addLast   = +
        // (forall s in cases)(reverse(x:toList(s)) = (reverse(toList(s)))+x)
        for ( String s : cases ) {
            ListNode l = Lists.addFirst(Lists.toList(s),'X'); 
            ListNode lhs = Lists.reverse(l);
            assertFalse(hasSharedNodes(lhs,l));
            l = Lists.toList(s); 
            ListNode l2 = Lists.reverse(l);
            assertFalse(hasSharedNodes(l,l2));
            assertTrue(Lists.equals(lhs,Lists.addLast(l2,'X')));
        }
    }
    
    @Test(expected = ListsException.class)
    public void testReverseWithException() {
         Lists.reverse(null);
    }
}



     


    























