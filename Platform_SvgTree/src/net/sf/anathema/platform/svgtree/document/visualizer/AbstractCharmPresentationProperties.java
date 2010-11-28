package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

public abstract class AbstractCharmPresentationProperties implements ITreePresentationProperties {

	  public Dimension getVerticalLineDimension() {
	    return new Dimension(getNodeDimension().height, getGapDimension().width);
	  }
}
