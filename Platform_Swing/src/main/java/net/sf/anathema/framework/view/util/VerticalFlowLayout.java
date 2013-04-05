package net.sf.anathema.framework.view.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

public class VerticalFlowLayout extends FlowLayout {

int hgap;
int vgap;
boolean hfill;
boolean vfill;


/**
*  Description of the Field
*/
public final static int TOP = 0;
public final static int MIDDLE = 1;
public final static int BOTTOM = 2;


/**
*  Construct a new VerticalFlowLayout with a middle alignemnt, and the
*  fill to edge flag set.
*/
public VerticalFlowLayout() {
	this(TOP, 5, 5, true, false);
}


/**
*  Construct a new VerticalFlowLayout with a middle alignemnt.
*
* @param  hfill  Description of Parameter
* @param  vfill  Description of Parameter
*/
public VerticalFlowLayout(boolean hfill, boolean vfill) {
	this(TOP, 5, 5, hfill, vfill);
}


/**
*  Construct a new VerticalFlowLayout with a middle alignemnt.
*
* @param  align  the alignment value
*/
public VerticalFlowLayout(int align) {
	this(align, 5, 5, true, false);
}


/**
*  Construct a new VerticalFlowLayout.
*
* @param  align  the alignment value
* @param  hfill  Description of Parameter
* @param  vfill  Description of Parameter
*/
public VerticalFlowLayout(int align, boolean hfill, boolean vfill) {
	this(align, 5, 5, hfill, vfill);
}


/**
*  Construct a new VerticalFlowLayout.
*
* @param  align  the alignment value
* @param  hgap   the horizontal gap variable
* @param  vgap   the vertical gap variable
* @param  hfill  Description of Parameter
* @param  vfill  Description of Parameter
*/
public VerticalFlowLayout(int align, int hgap, int vgap, boolean hfill,
		boolean vfill) {
	setAlignment(align);
	this.hgap = hgap;
	this.vgap = vgap;
	this.hfill = hfill;
	this.vfill = vfill;
}


/**
*  Sets the horizontal gap between components.
*
* @param  hgap  The new Hgap value
*/
public void setHgap(int hgap) {
	super.setHgap(hgap);
	this.hgap = hgap;
}


/**
*  Sets the vertical gap between components.
*
* @param  vgap  The new Vgap value
*/
public void setVgap(int vgap) {
	super.setVgap(vgap);
	this.vgap = vgap;
}


/**
*  Sets the VerticalFill attribute of the VerticalLayout object
*
* @param  vfill  The new VerticalFill value
*/
public void setVerticalFill(boolean vfill) {
	this.vfill = vfill;
}


/**
*  Sets the HorizontalFill attribute of the VerticalLayout object
*
* @param  hfill  The new HorizontalFill value
*/
public void setHorizontalFill(boolean hfill) {
	this.hfill = hfill;
}


/**
*  Gets the horizontal gap between components.
*
* @paramreturn    the horizontal gap between components.
* @paramsince     JDK1.1
* @paramsee       java.awt.FlowLayout#setHgap
*/
public int getHgap() {
	return hgap;
}


/**
*  Gets the vertical gap between components.
*
* @paramreturn    The Vgap value
*/
public int getVgap() {
	return vgap;
}


/**
*  Gets the VerticalFill attribute of the VerticalLayout object
*
* @paramreturn    The VerticalFill value
*/
public boolean getVerticalFill() {
	return vfill;
}


/**
*  Gets the HorizontalFill attribute of the VerticalLayout object
*
* @paramreturn    The HorizontalFill value
*/
public boolean getHorizontalFill() {
	return hfill;
}


/**
*  Returns the preferred dimensions given the components in the target
*  container.
*
* @param  target  the swing to lay out
* @paramreturn         Description of the Returned Value
*/
public Dimension preferredLayoutSize(Container target) {
	Dimension tarsiz = new Dimension(0, 0);

	for (int i = 0; i < target.getComponentCount(); i++) {
		Component m = target.getComponent(i);
		if (m.isVisible()) {
			Dimension d = m.getPreferredSize();
			tarsiz.width =
			    Math.max(tarsiz.width, d.width);
			if (i > 0) {
				tarsiz.height += vgap;
			}
			tarsiz.height += d.height;
		}
	}
	Insets insets = target.getInsets();
	tarsiz.width += insets.left + insets.right + hgap * 2;
	tarsiz.height += insets.top + insets.bottom + vgap * 2;
	return tarsiz;
}


/**
*  Returns the minimum size needed to layout the target container
*
* @param  target  the swing to lay out
* @paramreturn         Description of the Returned Value
*/
public Dimension minimumLayoutSize(Container target) {
	Dimension tarsiz = new Dimension(0, 0);

	for (int i = 0; i < target.getComponentCount(); i++) {
		Component m = target.getComponent(i);
		if (m.isVisible()) {
			Dimension d = m.getMinimumSize();
			tarsiz.width =
			    Math.max(tarsiz.width, d.width);
			if (i > 0) {
				tarsiz.height += vgap;
			}
			tarsiz.height += d.height;
		}
	}
	Insets insets = target.getInsets();
	tarsiz.width += insets.left + insets.right + hgap * 2;
	tarsiz.height += insets.top + insets.bottom + vgap * 2;
	return tarsiz;
}


/**
*  Lays out the container.
*
* @param  target  the container to lay out.
*/
public void layoutContainer(Container target) {
	Insets insets = target.getInsets();
	int maxheight =
	    target.getSize().height - (insets.top + insets.bottom +
				       vgap * 2);
	int maxwidth =
	    target.getSize().width - (insets.left + insets.right +
				      hgap * 2);
	int numcomp = target.getComponentCount();
	int x = insets.left + hgap;
	int y = 0;
	int colw = 0;
	int start = 0;

	for (int i = 0; i < numcomp; i++) {
		Component m = target.getComponent(i);
		if (m.isVisible()) {
			Dimension d = m.getPreferredSize();
			// fit last swing to remaining height
			if ((this.vfill) && (i == (numcomp - 1))) {
				d.height =
				    Math.max((maxheight - y),
					     m.getPreferredSize().
					     height);
			}
			// fit componenent size to container width
			if (this.hfill) {
				m.setSize(maxwidth, d.height);
				d.width = maxwidth;
			} else {
				m.setSize(d.width, d.height);
			}

			if (y + d.height > maxheight) {
				placethem(target, x,
					  insets.top + vgap, colw,
					  maxheight - y, start, i);
				y = d.height;
				x += hgap + colw;
				colw = d.width;
				start = i;
			} else {
				if (y > 0) {
					y += vgap;
				}
				y += d.height;
				colw = Math.max(colw, d.width);
			}
		}
	}
	placethem(target, x, insets.top + vgap, colw,
		  maxheight - y, start, numcomp);
}


/**
*  places the components defined by first to last within the target
* container using the bounds box defined
*
* @param  target  the container
* @param  x       the x coordinate of the area
* @param  y       the y coordinate of the area
* @param  width   the width of the area
* @param  height  the height of the area
* @param  first   the first swing of the container to place
* @param  last    the last swing of the container to place
*/
private void placethem(Container target, int x, int y, int width, int
		       height, int first, int last) {
	int align = getAlignment();
	//if ( align == this.TOP )
	//  y = 0;
	Insets insets = target.getInsets();
	if (align == this.MIDDLE) {
		y += height / 2;
	}
	if (align == this.BOTTOM) {
		y += height;
	}

	for (int i = first; i < last; i++) {
		Component m = target.getComponent(i);
		Dimension md = m.getSize();
		if (m.isVisible()) {
			int px = x + (width - md.width) / 2;
			m.setLocation(px, y);
			y += vgap + md.height;
		}
	}
}
}