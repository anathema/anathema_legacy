package net.sf.anathema.test.lib;

import java.awt.Point;

import junit.framework.TestCase;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.data.RangedArea;


public class RangedAreaTest extends TestCase {
	
	public RangedAreaTest(String name) {
		super(name);
	}

	public void testRanges() {
		RangedArea area = new RangedArea(new Range(1,1), new Range(2,2));
		assertEquals("verticalRange is {2,2}", new Range(2,2), area.getVerticalRange()); //$NON-NLS-1$
		assertEquals("horizontalRange is {11}", new Range(1,1), area.getHorizontalRange()); //$NON-NLS-1$
	}
	
	public void testWidthAndHeight() {
		RangedArea area = new RangedArea(new Range(3,4), new Range(3,7));
		assertEquals(new Range(3,4).getWidth(), area.getWidth());
		assertEquals(new Range(3,7).getWidth(), area.getHeight());
	}
	
	public void testPointContainsInnerPoint() {
		RangedArea area = new RangedArea(new Range(1,4), new Range(3,7));
		assertTrue(area.contains(new Point(2,4)));
	}
	
	public void testPointContainsOuterPoint() {
		RangedArea area = new RangedArea(new Range(1,4), new Range(3,7));
		assertTrue(!area.contains(new Point(0,7)));
	}
	
	public void testAreaContainsInnerArea() {
		RangedArea area = new RangedArea(new Range(1,4), new Range(3,7));
		RangedArea inner = new RangedArea(new Range(2,4), new Range(3,7));
		assertTrue(area.contains(inner));		
	}

	public void testAreaContainsOuterArea() {
		RangedArea area = new RangedArea(new Range(1,4), new Range(3,7));
		RangedArea outer = new RangedArea(new Range(0,4), new Range(3,7));
		assertTrue(!area.contains(outer));		
	}

	public void testEquals() {
		assertEquals(new RangedArea(new Range(1,1), new Range(2,2)), new RangedArea(new Range(1,1), new Range(2,2)));
	}

	public void testAnotherEquals() {
		assertEquals(new RangedArea(new Range(2,3), new Range(3,4)), new RangedArea(new Range(2,3), new Range(3,4)));
	}
	
	public void testEqualsWithNull() {
		assertTrue(!new RangedArea(new Range(1,1), new Range(2,2)).equals(null));
	}

	public void testEqualsWithObject() {
		assertTrue(!(new RangedArea(new Range(1,1), new Range(2,2)).equals(new Object())));
	}

	public void testToString() {
		String expected = "RangedArea{" + new Range(1,1) + ", " + new Range(2,2) + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		assertEquals(expected, new RangedArea(new Range(1,1), new Range(2,2)).toString());
	}

	public void testToAnotherString() {
		String expected = "RangedArea{" + new Range(2,3) + ", " + new Range(0,12) + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		assertEquals(expected, new RangedArea(new Range(2,3), new Range(0,12)).toString());
	}

}
