package net.sf.anathema.lib.data;

import java.awt.Point;

public class RangedArea {
	
	private Range horizontalRange;
	private Range verticalRange;
	
	public RangedArea(Range horizontalRange, Range verticalRange) {
		this.horizontalRange = horizontalRange;
		this.verticalRange = verticalRange;
	}

	@Override
  public boolean equals(Object object) {
		if (!(object instanceof RangedArea)) {
			return false;
		}
		RangedArea area = (RangedArea) object;
		return horizontalRange.equals(area.getHorizontalRange()) && verticalRange.equals(area.getVerticalRange());
	}

	public Range getHorizontalRange() {
		return horizontalRange;
	}
	
	public Range getVerticalRange() {
		return verticalRange;
	}
	
	public int getWidth() {
		return horizontalRange.getWidth();
	}
	
	public int getHeight() {
		return verticalRange.getWidth();
	}
	
	public boolean contains(Point point) {
		return horizontalRange.contains(point.x) && verticalRange.contains(point.y);
	}
	
	public boolean contains(RangedArea area) {
		return verticalRange.contains(area.getVerticalRange()) && horizontalRange.contains(area.getHorizontalRange());
	}

	@Override
  public String toString() {
		return "RangedArea{" + getHorizontalRange() + ", " + getVerticalRange() + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
