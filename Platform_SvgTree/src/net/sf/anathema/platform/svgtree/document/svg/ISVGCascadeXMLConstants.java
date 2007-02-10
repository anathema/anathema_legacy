package net.sf.anathema.platform.svgtree.document.svg;

import org.apache.batik.util.SVGConstants;

public interface ISVGCascadeXMLConstants {

  public static final String DOT = ","; //$NON-NLS-1$
  public static final String SPACE = " "; //$NON-NLS-1$

  // TODO entfernen
  @Deprecated
  public static final String ATTRIB_IS_CHARM = "isCharm"; //$NON-NLS-1$
  public static final String ATTRIB_IS_ARROW = "isArrow"; //$NON-NLS-1$

  public static final String VALUE_15 = "15"; //$NON-NLS-1$
  public static final String VALUE_3 = "3"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_POINTS = "0,0 15,15 30,0 15,5"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_ID = "ArrowHead"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_REFERENCE = "#" + VALUE_ARROWHEAD_ID; //$NON-NLS-1$
  public static final String VALUE_FRAME_ID = "Frame"; //$NON-NLS-1$
  public static final String VALUE_FRAME_REFERENCE = "#" + VALUE_FRAME_ID; //$NON-NLS-1$
  public static final String VALUE_CASCADE_ID = "cascade"; //$NON-NLS-1$
  public static final String VALUE_SVG_VERSION = "1.2"; //$NON-NLS-1$
  public static final String VALUE_VIEWBOX_SIZE = "0 0 1400 625"; //$NON-NLS-1$
  public static final String VALUE_XMID_YMIN_MEET = SVGConstants.SVG_XMIDYMIN_VALUE + " meet"; //$NON-NLS-1$

  public static final String VALUE_COLOR_SVG_BLACK = "rgb(0,0,0)"; //$NON-NLS-1$
  public static final String VALUE_COLOR_LIGHT_MEDIUM_GRAY = "rgb(120,120,120)"; //$NON-NLS-1$
  public static final String VALUE_COLOR_SVG_GRAY = "rgb(128,128,128)"; //$NON-NLS-1$
  public static final String VALUE_COLOR_SVG_GAINSBORO = "rgb(220, 220, 220)";//$NON-NLS-1$
  public static final String VALUE_COLOR_SVG_WHITE = "rgb(255,255,255)"; //$NON-NLS-1$
  public static final String VALUE_COLOR_SVG_NAVY = "rgb(0,0,128)"; //$NON-NLS-1$
  public static final String VALUE_COLOR_SVG_SILVER = "rgb(192,192,192)"; //$NON-NLS-1$

}