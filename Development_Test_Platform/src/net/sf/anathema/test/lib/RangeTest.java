package net.sf.anathema.test.lib;

import junit.framework.TestCase;
import net.sf.anathema.lib.data.Range;


public class RangeTest extends TestCase {

	public RangeTest(String arg1) {
		super(arg1);
	}

	public void testBasics() {
		Range range = new Range(2, 7);
		assertEquals("lowerBound is 2", range.getLowerBound(), 2); //$NON-NLS-1$
		assertEquals("upperBound is 7", range.getUpperBound(), 7); //$NON-NLS-1$
		assertEquals("width is 6", range.getWidth(), 6); //$NON-NLS-1$
	}
	
	public void testContainsInteger() {
		Range range = new Range(2, 7);
		assertTrue("2 is within", range.contains(2)); //$NON-NLS-1$
		assertTrue("7 is within", range.contains(7)); //$NON-NLS-1$
		assertTrue("1 is not within", !range.contains(1)); //$NON-NLS-1$
		assertTrue("8 is not within", !range.contains(8)); //$NON-NLS-1$
	}
	
	public void testContainsRange() {
		Range range = new Range(2,7);
		Range inner = new Range(3,4);
		Range outer = new Range(1,4);
		assertTrue("Inner-Range: ", range.contains(inner)); //$NON-NLS-1$
		assertTrue("Outer-Range: ", !range.contains(outer)); //$NON-NLS-1$
	}

	public void testEquals() {
		assertEquals(new Range(1, 1), new Range(1, 1));
	}

	public void testEqualsWithNonRange() {
		assertTrue(!(new Range(1, 1).equals(new Object())));
	}

	public void testOtherEquals() {
		assertEquals(new Range(2, 5), new Range(2, 5));
	}

	public void testOtherToString() {
		assertEquals("[2,3]", new Range(2, 3).toString()); //$NON-NLS-1$
	}

	public void testToString() {
		assertEquals("[1,2]", new Range(1, 2).toString()); //$NON-NLS-1$
	}

}