package org.cis1200;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

/** Tests for FileLineIterator */
public class FileLineIteratorTest {

    /*
     * Here's a test to help you out, but you still need to write your own.
     */

    @Test
    public void testHasNextAndNext() {

        // Note we don't need to create a new file here in order to test out our
        // FileLineIterator if we do not want to. We can just create a
        // StringReader to make testing easy!
        String words = "0, The end should come here.\n"
                + "1, This comes from data with no duplicate words!";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);
        assertTrue(li.hasNext());
        assertEquals("0, The end should come here.", li.next());
        assertTrue(li.hasNext());
        assertEquals("1, This comes from data with no duplicate words!", li.next());
        assertFalse(li.hasNext());
    }

    /* **** ****** **** WRITE YOUR TESTS BELOW THIS LINE **** ****** **** */

    @Test
    public void testHasNextAndNexttwo() {

        String words = "0, The end should come here.\n"
                + "1, This comes from data with no duplicate words!\n"
                + "2, This comes from data with duplicate words!\n"
                + "3, This is CIS 1200 NOT CIS 120!\n";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);
        assertTrue(li.hasNext());
        assertEquals("0, The end should come here.", li.next());
        assertTrue(li.hasNext());
        assertEquals("1, This comes from data with no duplicate words!", li.next());
        assertTrue(li.hasNext());
        assertEquals("2, This comes from data with duplicate words!", li.next());
        assertTrue(li.hasNext());
        assertEquals("3, This is CIS 1200 NOT CIS 120!", li.next());
        assertFalse(li.hasNext());
        assertFalse(li.hasNext());
    }

    @Test
    public void testHasNextAndNextonspaces() {

        String words = " \n"
                + "\n"
                + "\n"
                + "5\n";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);
        assertTrue(li.hasNext());
        assertEquals(" ", li.next());
        assertTrue(li.hasNext());
        assertEquals("", li.next());
        assertTrue(li.hasNext());
        assertEquals("", li.next());
        assertTrue(li.hasNext());
        assertEquals("5", li.next());
        assertFalse(li.hasNext());
        assertFalse(li.hasNext());
    }

    @Test
    public void testthrows() {
        String words = "";
        StringReader sr = new StringReader(words);
        BufferedReader br = new BufferedReader(sr);
        FileLineIterator li = new FileLineIterator(br);
        assertFalse(li.hasNext());
        assertThrows(IllegalArgumentException.class, () -> {
            FileLineIterator.fileToReader("what");
        });
    }

    @Test
    public void testConstructorWithNullReader() {
        assertThrows(IllegalArgumentException.class, () -> {
            String myNull = null;
            new FileLineIterator(myNull);
        });
    }

    @Test
    public void testHasNextWithEmptyFile() {
        String input = "";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        FileLineIterator iter = new FileLineIterator(reader);

        assertFalse(iter.hasNext());
    }

    @Test
    public void testMultipleHasNextCalls() {
        String input = "line1\nline2";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        FileLineIterator iter = new FileLineIterator(reader);

        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testCloseOnEndOfFile() throws Exception {
        String input = "line";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        FileLineIterator iter = new FileLineIterator(reader);

        iter.next();
        assertFalse(iter.hasNext());
        assertNull(reader.readLine());
    }
}
