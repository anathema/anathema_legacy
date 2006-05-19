package net.sf.anathema.charmtree.provider.svg;

import org.apache.batik.util.SVGConstants;

public interface ISVGCascadeXMLConstants {

  public static final String DOT = ","; //$NON-NLS-1$
  public static final String SPACE = " "; //$NON-NLS-1$

  public static final String ATTRIB_CX = "cx"; //$NON-NLS-1$
  public static final String ATTRIB_CY = "cy"; //$NON-NLS-1$
  public static final String ATTRIB_DX = "dx"; //$NON-NLS-1$
  public static final String ATTRIB_DY = "dy"; //$NON-NLS-1$
  public static final String ATTRIB_FILL = "fill"; //$NON-NLS-1$
  public static final String ATTRIB_FILL_OPACITY = "fill-opacity"; //$NON-NLS-1$
  public static final String ATTRIB_FONT_SIZE = "font-size"; //$NON-NLS-1$  
  public static final String ATTRIB_HEIGHT = "height"; //$NON-NLS-1$
  public static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  public static final String ATTRIB_OPACITY = "opacity"; //$NON-NLS-1$
  public static final String ATTRIB_POINTS = "points"; //$NON-NLS-1$
  public static final String ATTRIB_PRESERVE_ASPECT_RATIO = "preserveAspectRatio"; //$NON-NLS-1$
  public static final String ATTRIB_R = "r"; //$NON-NLS-1$
  public static final String ATTRIB_STROKE = "stroke"; //$NON-NLS-1$
  public static final String ATTRIB_STROKE_WIDTH = "stroke-width"; //$NON-NLS-1$
  public static final String ATTRIB_TEXT_ANCHOR = "text-anchor"; //$NON-NLS-1$
  public static final String ATTRIB_TRANSFORM = "transform"; //$NON-NLS-1$
  public static final String ATTRIB_VIEW_BOX = "viewBox"; //$NON-NLS-1$
  public static final String ATTRIB_WIDTH = "width"; //$NON-NLS-1$
  public static final String ATTRIB_X = "x"; //$NON-NLS-1$
  public static final String ATTRIB_Y = "y"; //$NON-NLS-1$

  public static final String TAG_CIRCLE = "circle"; //$NON-NLS-1$
  public static final String TAG_RECT = "rect"; //$NON-NLS-1$
  public static final String TAG_DEFS = "defs"; //$NON-NLS-1$
  public static final String TAG_G = "g"; //$NON-NLS-1$
  public static final String TAG_POLYGON = "polygon"; //$NON-NLS-1$
  public static final String TAG_POLYLINE = "polyline"; //$NON-NLS-1$
  public static final String TAG_SVG = "svg"; //$NON-NLS-1$
  public static final String TAG_SYMBOL = "symbol"; //$NON-NLS-1$
  public static final String TAG_TEXT = "text"; //$NON-NLS-1$
  public static final String TAG_TSPAN = "tspan"; //$NON-NLS-1$
  public static final String TAG_USE = "use"; //$NON-NLS-1$

  public static final String VALUE_15 = "15"; //$NON-NLS-1$
  public static final String VALUE_3 = "3"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_POINTS = "0,0 15,15 30,0 15,5"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_ID = "ArrowHead"; //$NON-NLS-1$
  public static final String VALUE_ARROWHEAD_REFERENCE = "#" + VALUE_ARROWHEAD_ID; //$NON-NLS-1$
  public static final String VALUE_FRAME_ID = "Frame"; //$NON-NLS-1$
  public static final String VALUE_FRAME_REFERENCE = "#" + VALUE_FRAME_ID; //$NON-NLS-1$
  public static final String VALUE_CASCADE_ID = "cascade"; //$NON-NLS-1$
  public static final String VALUE_COLOR_BLACK = "rgb(0,0,0)"; //$NON-NLS-1$
  public static final String VALUE_STROKE_MEDIUM_GRAY = "rgb(128,128,128)"; //$NON-NLS-1$
  public static final String VALUE_VIEWBOX_SIZE = "0 0 1400 625"; //$NON-NLS-1$
  public static final String VALUE_XMID_YMIN_MEET = SVGConstants.SVG_XMIDYMIN_VALUE + " meet"; //$NON-NLS-1$
  public static final String VALUE_SVG_VERSION = "1.2"; //$NON-NLS-1$
}